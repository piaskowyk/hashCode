package hashCode;

import java.util.Stack;
import java.util.Vector;

public class Car {
	public Pnt position = null; 
	public int freeTime = 0;
	//public boolean free;
	public Stack <Integer> rides = new Stack <Integer>();
	
	public Car(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public Ride getDistanceToClosestRide(Vector<Ride> rides, Vector<Integer> excluded) {
		Ride min = rides.get(0);
		int minDist = min.distance;
		int tmp = 0;
		int how = rides.length;
		Pnt pointR = null;
		Pnt pointC = null;
		for(int i=0; i<how; i++) {
			
			for(Integer x :  excluded) {
				if(x == rides.get(i).id) continue;
			}
			
			
			pointC.x = this.position.x;
			pointC.y = this.position.y;
			
			pointR.x = rides.get(i).startPoint.x;
			pointR.y = rides.get(i).startPoint.y;
			
			tmp = Pnt.GetDistanceToPoint(pointC, pointR);
			if(tmp < minDist) {
				minDist = tmp;
				min = rides.get(i);
			}
		}
		return min;
	}
}
