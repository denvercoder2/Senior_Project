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

# Not only equatorial coordinates can be converted between spherical and rectangular.
# These conversions can also be applied to ecliptic and horizontal coordinates.
# Just exchange RA,Decl with long,lat (ecliptic) or azimuth,altitude (horizontal).

oblecl = math.radians(23.4406)
#math.radians(3.563E-7 * d)


def rectangularCoordinates(plot):
    """spherical -> rectangular."""
    ra, dec, r = plot
    ra, dec, r = math.radians(ra), math.radians(dec), 1
    x = r * math.cos(ra) * math.cos(dec)
    y = r * math.sin(ra) * math.cos(dec)
    z = r * math.sin(dec)
    return round(x, 4), round(y, 4), round(z, 4)

# r * cos(v) = a * (cos(E) - e)
# r * sin(v) = a * sqrt(1 - e*e) * sin(E)


def sphericalCoordinates(plot):
    """rectangular -> spherical."""
    x, y, z = plot
    x, y, z = math.radians(x), math.radians(y), math.radians(z)
    ra = math.atan2(y, x)
    dec = math.atan2(z, math.sqrt(x*x + y*y))
    r = math.sqrt(x*x + y*y + z*z)
    ra, dec, r = math.degrees(ra), math.degrees(dec), math.degrees(r)
    return round(ra, 4), round(dec, 4), round(r, 4)


def equatorialCoordinates(plot):
    """ecliptic -> equatorial"""
    xeclip, yeclip, zeclip = plot
    xeclip, yeclip, zeclip = math.radians(
        xeclip), math.radians(yeclip), math.radians(zeclip)
    xequat = xeclip
    yequat = yeclip * math.cos(oblecl) - zeclip * math.sin(oblecl)
    zequat = yeclip * math.sin(oblecl) + zeclip * math.cos(oblecl)
    xequat, yequat, zequat = math.degrees(
        xequat), math.degrees(yequat), math.degrees(zequat)
    return round(xequat, 4), round(yequat, 4), round(zequat, 4)


def eclipticCoordinates(plot):
    """equatorial -> ecliptic"""
    xequat, yequat, zequat = plot
    xequat, yequat, zequat = math.radians(
        xequat), math.radians(yequat), math.radians(zequat)
    xeclip = xequat
    yeclip = yequat * math.cos(-oblecl) - zequat * math.sin(-oblecl)
    zeclip = yequat * math.sin(-oblecl) + zequat * math.cos(-oblecl)
    xeclip, yeclip, zeclip = math.degrees(
        xeclip), math.degrees(yeclip), math.degrees(zeclip)
    return round(xeclip, 4), round(yeclip, 4), round(zeclip, 4)


def rev(x):
    return x - math.floor(x/360.0)*360.0


class Sun():
    def __init__(self, d):
        self.w = 282.9404 + 4.70935E-5 * d  # (longitude of perihelion)
        self.a = 1.000000  # (mean distance, a.u.)
        self.e = 0.016709 - 1.151E-9 * d  # (eccentricity)
        self.M = 356.0470 + 0.9856002585 * d  # (mean anomaly)
        if (self.M < 0) or (self.M > 360):
            self.M = rev(self.M)
        self.L = self.w + self.M                # mean longitude
        if (self.L < 0) or (self.L > 360):
            self.L = rev(self.L)
        self.oblecl = 23.4393 - 3.563E-7 * d
        self.E = self.M + (180/math.pi) * self.e * math.sin(math.radians(self.M)) * \
            (1 + self.e * math.cos(math.radians(self.M)))

    def computeLocation(self):
        x = math.cos(math.radians(self.E)) - self.e
        y = math.sin(math.radians(self.E)) * math.sqrt(1 - pow(self.e, 2))

        r = math.sqrt(x*x + y*y)
        v = math.degrees(math.atan2(y, x))
        lon = v + self.w
        lon = rev(lon)

        x = r * math.cos(math.radians(lon))
        y = r * math.sin(math.radians(lon))
        z = 0.0
        temp = (x, y, z)
        temp = equatorialCoordinates(temp)
        temp = sphericalCoordinates(temp)

        ra, dec, r = temp
        hr = int(ra/15)
        mn = int(((ra/15)-hr)*60)
        sc = int(((((ra/15)-hr)*60)-mn)*60)
        ra = hr, mn, sc

        deg = int(dec)
        mn = int((dec - deg) * 60.0)
        sc = int((((dec - deg) * 60.0) - mn) * 60.0)
        dec = deg, mn, sc

        print('ra:  ', ra)
        print('dec: ', dec)
        print('r:   ', r)

    def show(self):
        return round(self.w, 4), round(self.a, 4), round(self.e, 4),\
            round(self.M, 4), round(self.L, 4), round(self.E, 4)


def foo():
    sol_ra, sol_dec, sol_dis = 90, 0, 1
    # starting w/ecliptic: longitude is 90, lattitude is 0, r is 1
    sol = (sol_ra, sol_dec, sol_dis)
    # start with spherical coordinates
    new = rectangularCoordinates(sol)
    print(new)
    new = equatorialCoordinates(new)
    print(new)
    new = sphericalCoordinates(new)
    print(new)


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
    # hr = 24*(d-int(d))
    # mn = 60*(hr - int(hr))
    # sc = 60*(mn - int(mn))

    # ut = int(hr), int(mn), int(sc)
    ut = 24*(d-int(d))
    gcdate = y, m, int(d)

    return gcdate, ut


def UTtoGST(gcdate, ut):
    jd = getJD(gcdate)
    S = jd - 2451545
    T = S/36525
    T0 = 6.697374558 + (2400.051336 * T) + (0.000025862*T*T)
    # ut = getDH(ut)
    A = ut * 1.002737909
    T0 += A
    while (T0 > 24 or T0 < 0):
        if T0 > 24:
            T0 -= 24
        if T0 < 0:
            T0 += 24
    # gst = getHMS(T0)
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
    '''get hours, minutes, and seconds'''
    hr = dh
    mn = 60*(hr-int(hr))
    sc = 60*(mn - int(mn))
    hms = int(hr), int(mn), round(sc, 2)
    return hms


def GSTtoLST(place, gst):
    lat, lon = place
    # dh = getDH(gst)
    offset = lon/15
    # lst = getHMS(dh+offset)
    lst = gst+offset
    return lst

# H = LST - right_ascension
# LT -> UT -> GST -> LST


def testLCT():
    place = (0, 60)
    date = (2013, 7, 1)
    time = (3, 37, 0)
    myDate, myTime = LCTtoUT(place, date, time, True)
    print(myDate)
    print(myTime)


def testGST():
    ut = (14, 36, 51.67)
    gcdate = (1980, 4, 22)
    gst = UTtoGST(gcdate, ut)
    print(gst)


def testLST():
    place = (0, -64)
    gst = (4, 40, 5.23)
    myLST = GSTtoLST(place, gst)
    print(myLST)


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
    a = (math.sin(dec)*math.sin(lat))+(math.cos(dec)*math.cos(lat)*math.cos(dh))
    a = math.asin(a)
    return a

def getAzimuth(lat, dh, alt, dec):
    A = (math.sin(dec)-(math.sin(lat)*math.sin(alt)))/(math.cos(lat)*math.cos(alt))
    A = math.acos(A)
    H = math.sin(dh)
    if H > 0:
        A = 2*(math.pi) - A
    return A

# testLCT()
def test():
    ra = (2, 31.1, 0)
    ra = math.radians(getDH(ra))
    dec = math.radians(89.15)

    lat = math.radians(34.73)
    lon = -86.59
    place = (lat, lon)

    time = (21, 2, 0)
    date = (2019, 10, 7)

    lst = getLST(place, date, time, False)
    dh = RAtoH(ra, lst)
    a = getAltitude(lat, dh, dec)
    print(math.degrees(a))
    A = getAzimuth(lat, dh, a, dec)
    print(math.degrees(A))

def test1():
    ra = (18, 32, 21)
    ra = getDH(ra)
    dec = (23, 13, 10)
    dec = getDH(dec)

    lat = 52
    lon = -64
    place = (lat, lon)

    time = (14, 36, 51.67)
    date = (1980, 4, 22)

    lst = getLST(place, date, time, False)
    dh = RAtoH(ra, lst)
    a = getAltitude(lat, dh, dec)
    print(a)
    A = getAzimuth(lat, dh, a, dec)
    print(A)

test()