package JohnWork.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SkyMap_Formulae_J {
	static SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) throws ParseException {
		double Mod2Pi_Angle;
		Mod2Pi_Angle = Mod2Pi.Mod2Pi(365);
		
		System.out.println(Mod2Pi_Angle);
		
		
		double JD;
		Date checkAgainstDate = newDateFormat.parse("2008-01-05");
		JD = GetJulianDay.GetJulianDay(checkAgainstDate, 20, 14);
		
		System.out.println(JD);
		
		//double trueAnomaly = TrueAnomaly.TrueAnomaly(meanAnom, eccentricity)
	}

}
