import math

def Mod2Pi(angle):
    """convert an angle above 360 degrees to one less than 360."""
    b = angle / (2*math.pi)
    abs_floor_b = math.floor(b) if b>=0 else math.ceil(b)
    a = (2* math.pi * (b - abs_floor_b))
    if (a<0):
        a = (2*math.pi + a) 
    new_angle = a
    return new_angle

def getJulianDay(date, time):
    (yr, mo, dy) = date
    (hr, mn) = time
    d = dy + ((hr + (mn/60))/24)
    if(mo > 2):
        y = yr
        m = mo
    else:
        y = yr - 1
        m = mo + 12
    
    if date > (1582, 10, 15):
        A = int(y/100) # Fractions are dropped. They are NOT rounded.
        B = 2 - A + int(A/4) 
    else:
        B = 0
    julianDay = int(365.25 * y) + int(30.6001 * (m + 1)) + d + 1720994.5 + B
    return julianDay

def getTrueAnomaly(meanAnomaly, eccentricity):
    """calculate the trueAnomaly given meanAnomaly and eccentricity (in radians)"""
    E = meanAnomaly + eccentricity * math.sin(meanAnomaly) * (1 + eccentricity * math.cos(meanAnomaly))
    E1 = E
    E = E1 - (E1 - eccentricity * math.sin(E1) - meanAnomaly) / (1 - eccentricity * math.cos(E1))
    while(math.fabs(E - E1) > (1.0E-12)):
        E1 = E
        E = E1 - (E1 - eccentricity * math.sin(E1) - meanAnomaly) / (1 - eccentricity * math.cos(E1))
    anomaly = 2 * math.atan(math.sqrt((1+eccentricity) / (1-eccentricity)) * math.tan(0.5 * E))
    if(anomaly < 0):
        anomaly = anomaly + (2 * math.pi)
    return anomaly

def getPosition(planet):
    """get orbital position of a planet"""
    position = (planet.semimajorAxis * (1 - math.pow(planet.eccentricity, 2)))\
            / (1 + planet.eccentricity * math.cos(planet.trueAnomaly))
    return position

#step 2: calculate elements for planetary orbit of Earth
class Earth:
    def __init__(self,cy):
        self.meanLongitude = Mod2Pi(math.radians(100.46435 + 129597740.63 * cy / 3600))
        self.semimajorAxis = 1.00000011 - 0.00000005 * cy
        self.eccentricity = 0.01671022 - 0.00003804 * cy
        
        self.inclination = math.radians( 0.00005 - 46.94 * cy / 3600)
        self.perihilion = math.radians(102.94719 + 1198.28 * cy / 3600)
        self.ascendingNodeLongtitude = math.radians(-11.26064 - 18228.25 * cy / 3600)
        
        self.meanAnomaly = Mod2Pi(self.meanLongitude - self.eccentricity)
        self.trueAnomaly = getTrueAnomaly(self.meanAnomaly, self.eccentricity)
        
        # step 3: calculate position of Earth in orbit
        self.position = getPosition(self)

        # step 4: calculate heliocentric coordinates of Earth
        self.xh = self.position * math.cos(self.trueAnomaly + self.perihilion)
        self.yh = self.position * math.sin(self.trueAnomaly + self.perihilion)
        self.zh = 0.0

#step 1: calculate elements for planetary orbit of selected planet
class Mercury:
    def __init__(self,cy, earth):
        self.name = 'Mercury'
        self.meanLongitude = Mod2Pi(math.radians(252.25084 + 538101628.29 * cy / 3600))
        
        self.semimajorAxis = 0.38709893 + 0.00000066 * cy
        self.eccentricity = 0.20563069 + 0.00002527 * cy
        self.perihilion = math.radians(77.45645 + 573.57 * cy / 3600)
        
        self.perihilionDistance = self.semimajorAxis * (1 - self.eccentricity)

        self.inclination = math.radians( 7.00487 - 23.51 * cy / 3600)
        
        self.ascendingNodeLongtitude = math.radians(48.33167 - 446.30 * cy / 3600)

        # step 5: calculate position of planet in orbit
        self.meanAnomaly = Mod2Pi(self.meanLongitude - self.eccentricity)
        self.trueAnomaly = getTrueAnomaly(self.meanAnomaly, self.eccentricity)
        self.position = getPosition(self)

        # step 6: calculate heliocentric coordinates of planet
        self.xh, self.yh, self.zh = self.getHelioCoordinates()
        # step 7: convert to geocentric rectangular coordinates
        self.xg, self.yg, self.zg = self.getGeoCoordinates(earth)
        # step 8: Rotate around X axis from ecliptic to equatorial coordinates
        self.xeq, self.yeq, self.zeq = self.getEquCoordinates()
        # Step 9: calculate α (right ascension)
        self.rightAscension = self.getRightAscension()
        # Step 9: calculate δ (declination)
        self.declination = self.getDeclination()

    def getHelioCoordinates(self):
        xh = self.position * (math.cos(self.ascendingNodeLongtitude) * \
            math.cos(self.trueAnomaly + self.perihilion - self.ascendingNodeLongtitude) - \
                math.sin(self.ascendingNodeLongtitude) * \
                    math.sin(self.trueAnomaly + self.perihilion - self.ascendingNodeLongtitude) * \
                        math.cos(self.inclination))
    
        yh = self.position * (math.sin(self.ascendingNodeLongtitude) * \
            math.cos(self.trueAnomaly + self.perihilion - self.ascendingNodeLongtitude) + \
                math.cos(self.ascendingNodeLongtitude) * \
                    math.sin(self.trueAnomaly + self.perihilion - self.ascendingNodeLongtitude) * \
                        math.cos(self.inclination))
        zh = 0.0
        return (xh, yh, zh)

    def getGeoCoordinates(self, earth):
        xg = self.xh - earth.xh
        yg = self.yh - earth.yh
        zg = self.zh - earth.zh
        return (xg, yg, zg)

    def getEquCoordinates(self):
        ecl = math.radians(23.439281)
        xeq = self.xg
        yeq = self.yg * math.cos(ecl) - self.zg * math.sin(ecl)
        zeq = self.yg * math.sin(ecl) + self.zg * math.cos(ecl)
        return (xeq, yeq, zeq)

    def getRightAscension(self):
        ra = math.degrees(Mod2Pi(math.atan2(self.yeq, self.xeq)))
        hours = int(ra / 15.0)
        mins = int(((ra / 15.0) - hours) * 60.0)
        secs = int(((((ra / 15.0) - hours) * 60.0) - mins) * 60.0)
        ra = (hours, mins, secs)
        return ra

    def getDeclination(self):
        dec = math.degrees(math.atan(self.zeq / math.sqrt(math.pow(self.xeq,2) + math.pow(self.yeq,2))))
        self.dist = math.sqrt(math.pow(self.xeq,2) + math.pow(self.yeq,2) + math.pow(self.zeq,2)) # Distance in AUs
        deg = int(dec)
        mins = int((dec - deg) * 60.0)
        secs = int((((dec - deg) * 60.0) - mins) * 60.0)
        dec = (deg, mins, secs)
        return dec


#myDate = (1990,4,19)
myDate = (2008,1,5)
myTime = (8,0)
myJD = getJulianDay(myDate, myTime)
print(myJD)

# myTime = (10, 49)
# cy = getJulianDay(myDate, myTime) / 36525
# earth = Earth(cy)
# mercury = Mercury(cy, earth)
# print(mercury.name)
# print('RA -  hours, minutes, seconds:   ',mercury.rightAscension)
# print('Dec - degrees, minutes, seconds: ',mercury.declination)