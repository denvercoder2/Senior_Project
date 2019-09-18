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
    ra,dec,r = plot
    ra,dec,r = math.radians(ra),math.radians(dec),1
    x = r * math.cos(ra) * math.cos(dec)
    y = r * math.sin(ra) * math.cos(dec)
    z = r * math.sin(dec)
    return round(x,4),round(y,4),round(z,4)

#r * cos(v) = a * (cos(E) - e)
#r * sin(v) = a * sqrt(1 - e*e) * sin(E)

def sphericalCoordinates(plot):
    """rectangular -> spherical."""
    x,y,z = plot
    x,y,z = math.radians(x),math.radians(y),math.radians(z)
    ra = math.atan2( y, x )
    dec = math.atan2(z, math.sqrt( x*x + y*y ))
    r = math.sqrt( x*x + y*y + z*z )
    ra,dec,r = math.degrees(ra), math.degrees(dec), math.degrees(r)
    return round(ra,4), round(dec,4), round(r,4)

def equatorialCoordinates(plot):
    """ecliptic -> equatorial"""
    xeclip, yeclip, zeclip = plot
    xeclip, yeclip, zeclip = math.radians(xeclip),math.radians(yeclip),math.radians(zeclip)
    xequat = xeclip
    yequat = yeclip * math.cos(oblecl) - zeclip * math.sin(oblecl)
    zequat = yeclip * math.sin(oblecl) + zeclip * math.cos(oblecl)
    xequat, yequat, zequat = math.degrees(xequat),math.degrees(yequat),math.degrees(zequat)
    return round(xequat,4), round(yequat,4), round(zequat,4)

def eclipticCoordinates(plot):
    """equatorial -> ecliptic"""
    xequat, yequat, zequat = plot
    xequat, yequat, zequat = math.radians(xequat),math.radians(yequat),math.radians(zequat)
    xeclip = xequat
    yeclip = yequat * math.cos(-oblecl) - zequat * math.sin(-oblecl)
    zeclip = yequat * math.sin(-oblecl) + zequat * math.cos(-oblecl)
    xeclip, yeclip, zeclip = math.degrees(xeclip),math.degrees(yeclip),math.degrees(zeclip)
    return round(xeclip,4), round(yeclip,4), round(zeclip,4)

def julianDay(date):
    y,m,D = date
    d = 367*y - 7 * ( y + (m+9)//12 ) // 4 - 3 * ( ( y + (m-9)//7 ) // 100 + 1 ) // 4 + 275*m//9 + D - 730515
    return d

def rev(x):
    return  x - math.floor(x/360.0)*360.0

class Sun():
    def __init__(self,d):
        self.w = 282.9404 + 4.70935E-5 * d      #(longitude of perihelion)
        self.a = 1.000000                       #(mean distance, a.u.)
        self.e = 0.016709 - 1.151E-9 * d        #(eccentricity)
        self.M = 356.0470 + 0.9856002585 * d    #(mean anomaly)
        if (self.M < 0) or (self.M > 360):
            self.M = rev(self.M)
        self.L = self.w + self.M                # mean longitude
        if (self.L < 0) or (self.L > 360):
            self.L = rev(self.L)
        self.oblecl = 23.4393 - 3.563E-7 * d
        self.E = self.M + (180/math.pi) * self.e * math.sin(math.radians(self.M)) * \
            (1 + self.e * math.cos(math.radians(self.M)))

    def show(self):
        return round(self.w), self.a, self.e, self.M, self.L, self.E

def foo():
    sol_ra, sol_dec, sol_dis = 90, 0, 1
    sol = (sol_ra, sol_dec, sol_dis) # starting w/ecliptic: longitude is 90, lattitude is 0, r is 1
    # start with spherical coordinates
    new = rectangularCoordinates(sol)
    print(new)
    new = equatorialCoordinates(new)
    print(new)
    new = sphericalCoordinates(new)
    print(new)

myDate = (1990,4,19)
myJD = julianDay(myDate)
sol = Sun(myJD)
# print(sol.show())
# foo()

x = math.cos(math.radians(sol.E)) - sol.e
y = math.sin(math.radians(sol.E)) * math.sqrt(1 - pow(sol.e,2))
temp = x,y,0
print(sphericalCoordinates(temp))