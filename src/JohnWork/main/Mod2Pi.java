package JohnWork.main;

public class Mod2Pi {
	
	public static double Mod2Pi(double angle) {
		double b, abs_floor_b, a;
		
		b = angle / (2*Math.PI);
		abs_floor_b = Math.floor(b);
		
		if (b >= 0)
			abs_floor_b = Math.ceil(b);
		
		a = (2 * Math.PI * (b - abs_floor_b));
		if (a < 0)
			a = (2 * Math.PI + a);
		
		double newAngle = a;
		
		return newAngle;
	}
}
