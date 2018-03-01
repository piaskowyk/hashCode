package hashCode;

import java.awt.Point;

public class Car {
	public Pnt position = null; 
	public int freeTime = 0;
	public boolean free;
	
	public Car(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public Ride getDistanceToClosestRide(Ride[] rides) {
		Ride min = rides[0];
		int minDist = min.getDistance();
		int tmp = 0;
		int how = rides.length;
		Pnt pointR = null;
		Pnt pointC = null;
		for(int i=0; i<how; i++) {
			pointC.x = this.position.x;
			pointC.y = this.position.y;
			
			pointR.x = rides[i].startPoint.x;
			pointR.y = rides[i].startPoint.y;
			
			tmp = Pnt.GetDistanceToPoint(pointC, pointR);
			if(tmp < minDist) {
				minDist = tmp;
				min = rides[i];
			}
		}
		return min;
	}
}
