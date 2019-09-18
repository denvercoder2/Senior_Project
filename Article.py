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

def rectangularCoordinates(plot):
    """spherical -> rectangular. assume radians"""
    h,v,r = plot
    x = r * round(math.cos(h) * math.cos(v),4)
    y = r * round(math.sin(h) * math.cos(v),4)
    z = r * round(math.sin(v),2)
    return x,y,z

def sphericalCoordinates(plot):
    """rectangular -> spherical. assume radians"""
    x,y,z = plot
    r = round(math.sqrt( x*x + y*y + z*z ),4)
    h = round(math.degrees(math.atan2( y, x )),4)
    v = round(math.degrees(math.asin( z / r )),4)
    return h,v,r

oblecl = math.radians(23.4) # degrees

def equatorialCoordinates(plot):
    """ecliptic -> equatorial"""
    xeclip, yeclip, zeclip = plot
    xequat = xeclip
    yequat = round(yeclip * math.cos(oblecl) - zeclip * math.sin(oblecl),4)
    zequat = round(yeclip * math.sin(oblecl) + zeclip * math.cos(oblecl),4)
    return xequat, yequat, zequat

def eclipticCoordinates(plot):
    """equatorial -> ecliptic"""
    xequat, yequat, zequat = plot
    xeclip = xequat
    yeclip = yequat * math.cos(-oblecl) - zequat * math.sin(-oblecl)
    zeclip = yequat * math.sin(-oblecl) + zequat * math.cos(-oblecl)
    return xeclip, yeclip, zeclip

def julianDay(date):
    y,m,D = date
    d = 367*y - 7 * ( y + (m+9)//12 ) // 4 - 3 * ( ( y + (m-9)//7 ) // 100 + 1 ) // 4 + 275*m//9 + D - 730515
    return d

def foo(plot):
    sol_ra = math.radians(90)
    sol_dec = math.radians(0)
    sol_dis = 1
    sol = (sol_ra, sol_dec, sol_dis) # starting w/ecliptic: longitude is 90, lattitude is 0, r is 1
    # start with spherical coordinates
    new = rectangularCoordinates(plot)
    print(new)
    new = equatorialCoordinates(new)
    print(new)
    new = sphericalCoordinates(new)
    print(new)

myDate = (1990,4,19)
print(julianDay(myDate))