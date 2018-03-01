package hashCode;

import java.util.Stack;
import java.util.Vector;

public class Car {
	public Pnt position = new Pnt(); 
	public int freeTime = 0;
	//public boolean free;
	public Stack <Integer> rides = new Stack <Integer>();
	public Vector<Integer> excluded = new Vector<Integer>();
	
	public Car(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public int rate(Ride ride, int currentTime, int bonus)
	{
		int dojazd = Math.max(Pnt.GetDistanceToPoint(this.position, ride.startPoint), ride.startTime-currentTime);
		int rating = getPoints(ride, currentTime, bonus);
		rating/=(dojazd+ride.distance);
		return rating;
	}
	
	public int getPoints(Ride ride, int currentTime, int bonus)
	{
		int points = ride.distance;
		if(Pnt.GetDistanceToPoint(this.position, ride.startPoint) >= ride.startTime-currentTime) points+=bonus;
		return points;
	}
	
	public Ride getDistanceToClosestRide(Vector<Ride> rides) {
		int how = rides.size();
		int k = 0;
		Ride min = null;
		
		while(min == null && k<how) {//znajdz pierwsz¹ niewykluczon¹ przesy³kê
			if(!isExcluded(rides.get(k))) {
				min = rides.get(k);
			}
			k++;
		}
		
		if(min == null) {// jeœli wszystkie przesy³ki s¹ wykluczone return null
			return null;
		}
		
		int minDist = min.distance;
		int tmp = 0;
		Pnt pointR = new Pnt();
		Pnt pointC = new Pnt();
		for(int i=0; i<how; i++) {

			if(isExcluded(rides.get(i))) continue;
			
			
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
	
	public boolean isExcluded(Ride item) {
		boolean ok = true;
		for(Integer x :  excluded) {
			if(x == item.number) {
				ok = false;
				break;
			}
		}
		return ok;
	}
	
	public Ride minTimeToEnd(Vector<Ride> rides, int currentTime) {
		int how = rides.size();
		int k = 0;
		int minTime = 0;
		int tmp;
		Ride min = null;
		
		while(min == null && k<how) {//znajdz pierwsz¹ niewykluczon¹ przesy³kê
			if(!isExcluded(rides.get(k)) && Pnt.GetDistanceToPoint(rides.get(k).startPoint, this.position) + rides.get(k).distance
					<=
				rides.get(k).endTime - currentTime) {
				min = rides.get(k);
			}
			k++;
		}
		
		if(min == null) {// jeœli wszystkie przesy³ki s¹ wykluczone return null
			return null;
		}
		
		minTime = min.endTime;
		
		for(int i=0; i<how; i++) {
			if(isExcluded(rides.get(i))) continue;
			
			tmp = rides.get(i).endTime;
			if(tmp < minTime 
					&& Pnt.GetDistanceToPoint(rides.get(i).startPoint, this.position) + rides.get(i).distance
						<=
					rides.get(i).endTime - currentTime
					) {
				minTime = tmp;
				min = rides.get(i);
			}
		}
		
		return min;
		
	}
	
}
