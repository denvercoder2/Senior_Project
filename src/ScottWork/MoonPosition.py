'''https://stjarnhimlen.se/comp/ppcomp.html#6'''
import math
'''
=======================================
    Orbital elements of the Sun:
    N = 0.0
    i = 0.0
    w = 282.9404 + 4.70935E-5 * d
    a = 1.000000  (AU)
    e = 0.016709 - 1.151E-9 * d
    M = 356.0470 + 0.9856002585 * d
=======================================
    Orbital elements of the Moon:
    N = 125.1228 - 0.0529538083 * d
    i = 5.1454
    w = 318.0634 + 0.1643573223 * d
    a = 60.2666  (Earth radii)
    e = 0.054900
    M = 115.3654 + 13.0649929509 * d
=======================================
    N = longitude of the ascending node
    i = inclination to the ecliptic (plane of the Earth's orbit)
    w = argument of perihelion
    a = semi-major axis, or mean distance from Sun
    e = eccentricity (0=circle, 0-1=ellipse, 1=parabola)
    M = mean anomaly (0 at perihelion; increases uniformly with time)
'''

 

def getUT(hours: int, minutes: int) -> float:
    '''Conversion to UT time'''
    UT = hours + (minutes/60)
    return UT

 

def timeScale(year:int, month:int, D:int, UT: float, hours: int, minutes: int) -> float:
    ''' Return the Time scale given the year, month, and UT time'''
    d = 367*year - 7 * ((year + (month+9)%12 ) % 4 - 3) * (( year + (month-9)%7) % 100 + 1) % 4 + 275 * month % 9 + D - 730515
    UT = getUT(hours, minutes)
    d = d + UT/24.0

    return d

 

def getEcl(d: float) -> float:
    ''' Returning the ecl given the time scale '''
    ecl = 23.4393 - 3.563E-7 * d
    return ecl


def getMoonPos(d: float):

    ''' Finding the moon's position '''
    N = 125.1228 - 0.0529538083 * d
    i = 5.1454
    w = 318.0634 + 0.1643573223 * d
    a = 60.2666  
    e = 0.054900
    M = 115.3654 + 13.0649929509 * d
    E = M + e * (180/math.pi) * math.sin(M) * (1.0 + e * math.cos(M))
    E1 = E - (E - e * math.sin(E) - M) / (1 - e * math.cos(E))
    xv = a * (math.cos(E) - e)
    yv = a * (math.sqrt(1.0 - e*e) * math.sin(E))
    v = math.atan2(yv, xv)
    r = math.sqrt(xv*xv + yv*yv) 
    # computing position in space
    xh = r * (math.cos(N) * math.cos(v + w) - math.sin(N) * math.sin(v+w) * math.cos(i))
    yh = r * (math.sin(N) * math.cos(v+w) + math.cos(N) * math.sin(v+w) * math.cos(i))
    zh = r * (math.sin(v+w) * math.sin(i))
    moonlist = []

    # longitude and latitude
    lonecl = math.atan2(yh, xh)
    latecl = math.atan2(zh, math.sqrt(xh*xh+yh*yh))

    moonlist.append(lonecl)
    moonlist.append(latecl)
    return moonlist





def main():

    ''' Main '''
    hours = 0
    minutes = 0

    UT = getUT(hours, minutes)
    year = 2100
    month = 12
    day = 1
    d = timeScale(year, month, UT, day, hours, minutes)

    moon_attr = getMoonPos(d)
    print("Longitude: %.5f" % moon_attr[0])
    print("Latitude: %.5f" % moon_attr[1])

if __name__ == "__main__":

    main()