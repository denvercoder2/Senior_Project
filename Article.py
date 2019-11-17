import math
import decimal
# Primary Elements
# a  Mean distance, or semi-major axis
# e  Eccentricity
# T  Time at perihelion
# i  Inclination, i.e. the "tilt" of the orbit relative to the ecliptic.
#    The inclination varies from 0 to 180 degrees.
# N  Longitude of Ascending Node.
# w  The angle from the Ascending node to the Perihelion, along the orbit.

# q  Perihelion distance  = a * (1 - e)

# spherical ecliptic coordinates (long, lat)
# spherical equatorial coordinates (RA, Decl)


def getUT(place, time, dst):
    '''get Universal Time'''
    lat, lon = place
    hr, mn, sc = time
    if dst:
        hr -= 1
    offset = int(lon/15)  # offset between local timezone and Greenwich
    time = hr, mn, sc
    ut = getDH(time) - offset  # universal time
    return ut


def getGCD(date, ut):
    '''get Greenwich Calendar Date'''
    yr, mon, day = date
    gcday = day + (ut/24)
    gcdate = yr, mon, gcday
    return gcdate


def getJD(gcdate):
    '''get Julian Date'''
    yr, mon, day = gcdate
    if(mon > 2):
        y = yr
        m = mon
    else:
        y = yr - 1
        m = mon + 12

    if gcdate > (1582, 10, 15):
        A = int(y/100)  # Fractions are dropped. They are NOT rounded.
        B = 2 - A + int(A/4)
    else:
        B = 0

    C = int((365.25*y)-0.75) if (y < 0) else int(365.25 * y)
    D = int(30.6001 * (m + 1))

    jd = B + C + D + day + 1720994.5
    return jd


def getCD(jd):
    '''get Calendar Date'''
    jd += 0.5
    I = int(jd)
    F = jd - int(jd)

    if I > 2299160:
        A = int((I-1867216.25)/36524.25)
        B = I + A - int(A/4) + 1
    else:
        B = I

    C = B + 1524
    D = int((C-122.1)/365.25)
    E = int(365.25*D)
    G = int((C-E)/30.6001)
    day = C - E + F - int(30.6001*G)
    mon = G-1 if (G < 13.5) else G-13
    yr = D-4716 if (mon > 2.5) else D-4715

    date = yr, mon, day
    return date


def LCTtoUT(place, date, time, dst):
    ut = getUT(place, time, dst)
    gcdate = getGCD(date, ut)
    jd = getJD(gcdate)
    gcdate = getCD(jd)

    y, m, d = gcdate
    ut = 24*(d-int(d))
    gcdate = y, m, int(d)

    return gcdate, ut


def UTtoGST(gcdate, ut):
    jd = getJD(gcdate)
    S = jd - 2451545
    T = S/36525
    T0 = 6.697374558 + (2400.051336 * T) + (0.000025862*T*T)
    A = ut * 1.002737909
    T0 += A
    while (T0 > 24 or T0 < 0):
        if T0 > 24:
            T0 -= 24
        if T0 < 0:
            T0 += 24
    gst = T0
    return gst


def getDH(hms):
    '''get decimal hours'''
    hr, mn, sc = hms
    sc /= 60
    mn = (mn+sc)/60
    hr += mn
    return hr


def getHMS(dh):
    '''get hours, minutes, and seconds from decimal hours'''
    hr = dh
    mn = 60*(hr-int(hr))
    sc = 60*(mn - int(mn))
    hms = int(hr), int(mn), round(sc, 2)
    return hms


def getDMS(dd):
    'get degrees, minutes, seconds from decimal degrees'
    mn = (dd-int(dd))*60
    sc = (mn-int(mn))*60
    dms = (int(dd), int(mn), round(sc, 2))
    return dms


def GSTtoLST(place, gst):
    lat, lon = place
    offset = lon/15
    lst = gst+offset
    return lst

# H = LST - right_ascension
# LT -> UT -> GST -> LST


def getLST(place, lcdate, lctime, dst):
    udate, utime = LCTtoUT(place, lcdate, lctime, dst)
    gst = UTtoGST(udate, utime)
    lst = GSTtoLST(place, gst)
    return lst


def RAtoH(ra, lst):
    hr = lst - ra
    if (hr < 0):
        hr += 24
    return hr


def getAltitude(lat, dh, dec):
    a = (math.sin(dec)*math.sin(lat)) + \
        (math.cos(dec)*math.cos(lat)*math.cos(dh))
    a = math.asin(a)
    return a


def getAzimuth(lat, dh, alt, dec):
    A = (math.sin(dec)-(math.sin(lat)*math.sin(alt))) / \
        (math.cos(lat)*math.cos(alt))
    A = math.acos(A)
    H = math.sin(dh)
    if H > 0:
        A = 2*(math.pi) - A
    return A


def boundRange(num):
    while (1 == 1):
        if num < 0:
            num += 360
        elif num > 360:
            num -= 360
        else:
            break
    return num


def getMeanObliq(gcdate):
    jd = getJD(gcdate)
    mjd = jd - 2451545.0  # epoch 2000 January 1.5
    t = mjd/36525
    de = 46.815*t-.0006*t*t-0.00181*pow(t, 3)
    de = de / 3600
    e = math.radians(23.439292 - de)
    return e


def equatorialCoordinates(ecLon, ecLat, gcdate):
    """ecliptic -> equatorial"""
    e = getMeanObliq(gcdate)
    dec = math.degrees(math.asin(math.sin(ecLat)*math.cos(e) +
                                 math.cos(ecLat)*math.sin(e)*math.sin(ecLon)))
    y = math.sin(ecLon)*math.cos(e)-math.tan(ecLat)*math.sin(e)
    ys = -1 if (y < 0) else 1
    x = math.cos(ecLon)
    xs = -1 if (x < 0) else 1
    if ys == 1:
        upper = 90 if xs == 1 else 180
    else:
        upper = 360 if xs == 1 else 270
    lower = upper - 90

    ra = math.degrees(math.atan(y/x))
    while not (lower < ra < upper):
        if ra > upper:
            ra -= 90
        else:
            ra += 90
    ra /= 15
    return ra, dec


def getQuadrant(x, y):
    ys = -1 if (y < 0) else 1
    xs = -1 if (x < 0) else 1
    if ys == 1:
        upper = 90 if xs == 1 else 180
    else:
        upper = 360 if xs == 1 else 270
    lower = upper - 90

    temp = math.degrees(math.atan(y/x))
    while not (lower < temp < upper):
        if temp > upper:
            temp -= 90
        else:
            temp += 90
    return temp


def getECLon(jd):
    epoch = 2455196.5
    D = jd - epoch
    t = (epoch-2415020.0)/36525
    L = 279.6966778 + (36000.76892*t) + (0.0003025*t*t)  # mean longitude
    L = boundRange(L)
    w = 281.2208444 + (1.719175*t) + (0.000452778*t*t)  # argument of perigee
    w = boundRange(w)
    e = 0.01675104 - (0.0000418*t) - (0.000000126*t*t)
    N = (360/365.242191)*D
    N = boundRange(N)
    M = N+L-w
    if M < 0:
        M += 360
    E = (360/math.pi)*e*math.sin(math.radians(M))
    l = N+E+L
    while (l > 360):
        l -= 360
    return l


def testEC():
    date = (2009, 7, 6)
    ecLon = (139, 41, 10)
    ecLon = getDH(ecLon)
    ecLon = math.radians(ecLon)
    ecLat = (4, 52, 31)
    ecLat = getDH(ecLat)
    ecLat = math.radians(ecLat)
    ra, dec = equatorialCoordinates(ecLon, ecLat, date)
    print("RA:  ", getHMS(ra))
    print("Dec: ", getDMS(dec))


def getSun(ilon, ilat, ihr, imn, isc, iyr, imon, iday, idst):
    place = (ilat, ilon)
    time = (ihr, imn, isc)
    date = (iyr, imon, iday)

    ut = getUT(place, time, idst)
    gcdate = getGCD(date, ut)

    jd = getJD(gcdate)

    ecLon = getECLon(jd)
    ecLon = math.radians(ecLon)
    ecLat = 0  # ecliptic latitude is zero for the sun
    ra, dec = equatorialCoordinates(ecLon, ecLat, gcdate)
    return getHMS(ra), getDMS(dec)


def getLR(istring, D):
    s = istring.split(':')
    T = float(s[0])
    L = float(s[1])
    w = float(s[2])
    e = float(s[3])
    a = float(s[4])

    M = math.radians(((360 / 365.242191) * (D / T)) + L - w)
    M = round(M, 4)
    v = M + (e * math.sin(M))
    temp = boundRange(math.degrees(v))
    l = math.radians(boundRange(math.degrees(v) + w))
    r = (a * (1-e*e)) / (1+e*math.cos(v))
    return l, r


def getPlanet(ijd, iplanet):
    # M  mean anomaly
    # v  true anomaly
    # T  orbital period of the planet (in tropical years)
    # L  mean longitude of the planet
    # w  longitude of the perihelion
    # e  eccentricity of orbit

    if iplanet.lower() == 'jupiter':
        planet = '11.857911:337.917132:14.6633:0.048907:5.20278:1.3035:100.595:True'
    earth = '0.999996:99.556772:103.2055:0.016671:0.999985'

    epoch_date = (2010, 1, 0)
    epoch = getJD(epoch_date)
    D = ijd - epoch

    lE, rE = getLR(earth, D)
    l, r = getLR(planet, D)
    i = math.radians(float(planet[5]))
    N = math.radians(float(planet[6]))

    hLat = math.asin(math.sin(l-N)*math.sin(i))
    y = math.sin(l-N)*math.cos(i)
    x = math.cos(l-N)
    pl = math.radians(getQuadrant(x, y)) + N
    pr = (r*math.cos(hLat))

    if outer:
        ecLon = math.atan((rE * math.sin(pl - lE)) /
                          (pr - rE * math.cos(pl - lE))) + pl
    else:
        ecLon = 180 + lE + \
            math.atan((pr * math.sin(lE - pl)) / (rE - rE * math.cos(pl - lE)))
    ecLat = math.atan(
        (pr*math.tan(hLat)*math.sin(ecLon-pl))/(rE*math.sin(pl-lE)))

    ra, dec = equatorialCoordinates(ecLon, ecLat, gcd)
    return ra, dec

def test():
    # line = 'ra:ra:ra:dec:dec:dec:lat:lon:hr:mn:sc:yr:mon:day:dst'
    # static = '18:32:21:23:13:10:52:-64:14:36:51.67:1980:4:22:False'
    polaris = '2:31:49:89:15:50.78:34:-87:17:37:0:2019:11:17:True'
    solveLocation(polaris)


def getStar(ra, dec):
    ra = getDH(ra)
    dec = getDH(dec)
    return ra, dec

def solveLocation(istring):
    # line = 'ra:ra:ra:dec:dec:dec:lat:lon:hr:mn:sc:yr:mon:day:dst'
    s = istring.split(':')
    ra = (int(s[0]), int(s[1]), float(s[2]))
    dec = (int(s[3]), int(s[4]), float(s[5]))
    lat = float(s[6])
    lon = float(s[7])
    hr = int(s[8])
    mn = int(s[9])
    sc = float(s[10])
    yr = int(s[11])
    mon = int(s[12])
    day = int(s[13])
    dst = True if (s[14] == 'True') else False
    star = False

    place = (lat, lon)
    time = (hr, mn, sc)
    date = (yr, mon, day)

    lst = getLST(place, date, time, dst)

    if star:
        ra, dec = getStar(ra, dec)
    # elif planet:
    #     getPlanet()
    # else:
    #     getSun()
    

    dh = math.radians(RAtoH(ra, lst)*15)
    lat = math.radians(lat)
    dec = math.radians(dec)

    a = getAltitude(lat, dh, dec)
    print("alt: ", getDMS(math.degrees(a)))
    A = getAzimuth(lat, dh, a, dec)
    print("azm: ", getDMS(math.degrees(A)))

test()