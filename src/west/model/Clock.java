package west.model;

public class Clock {
	private static long startTime = 0L;
	
	public static void reset(){startTime = System.nanoTime();}
	public static double getTimeInSec(){
		double time = System.nanoTime() - startTime;
		time *= 1.0e-9;
		return time;
	}
}
