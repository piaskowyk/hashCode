package hashCode;

import java.awt.Point;

public class Ride {
	public int startTime;
	public int endTime;
	public Point startPoint;
	public Point endPoint;
	
	public int getDistance() {
		return Math.abs(startPoint.x - endPoint.x) + Math.abs(startPoint.y-endPoint.y);
	}
	
	public Ride(Point start, Point end, int startTime, int endTime) {
		this.startPoint = start;
		this.endPoint = end;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
}
