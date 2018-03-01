package hashCode;

import java.awt.Point;

public class Ride {
	public int startTime;
	public int endTime;
	public Pnt startPoint;
	public Pnt endPoint;
	
	public int getDistance() 
	{
		return Math.abs(startPoint.x - endPoint.x) + Math.abs(startPoint.y-endPoint.y);
	}
	
	public Ride(Pnt start, Pnt end, int startTime, int endTime) {
		this.startPoint = start;
		this.endPoint = end;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
}
