package hashCode;

import java.awt.Point;

public class Car {
	public int x = 0;
	public int y = 0;
	public int freeTour = 0;
	public boolean free;
	
	public Car(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ride getDistanceToClosestRide(Ride[] rides) {
		Ride min = rides[0];
		int minDist = min.getDistance();
		int tmp = 0;
		int how = rides.length;
		Point pointR = null;
		Point pointC = null;
		for(int i=0; i<how; i++) {
			pointC.x = this.x;
			pointC.y = this.y;
			
			pointR.x = rides[i].startPoint.x;
			pointR.y = rides[i].startPoint.y;
			
			tmp = rides[i].getDistance();
			if(tmp < minDist) {
				minDist = tmp;
				min = rides[i];
			}
		}
		return min;
	}
}
