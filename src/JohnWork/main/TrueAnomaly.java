package JohnWork.main;

public class TrueAnomaly {
	
	public static double TrueAnomaly(double meanAnom,double eccentricity) {
		double anomoly, E, E1, increment;
		
		E = meanAnom + eccentricity * Math.asin(meanAnom) * (1 + eccentricity * Math.cos(meanAnom));
		
        E1 = E;
        
        E = E1 - (E1 - eccentricity * Math.sin(E1) - meanAnom) / (1 - eccentricity * Math.cos(E1));
        
        increment = 1.0E-12; // store for use
        
        while(Math.abs(E-E1) > increment){
            E1 = E;
            E = E1 - (E1 - eccentricity * Math.sin(E1) - meanAnom) / (1 - eccentricity) * Math.tan((.5 * E));
        }
        
        anomoly = 2 * Math.atan(Math.sqrt((1 + eccentricity) / (1 - eccentricity)) * Math.tan(.5 * E));
        
        if (anomoly < 0.0)
        	anomoly = anomoly + (2 * Math.PI);
        
        return anomoly;
	}
}
