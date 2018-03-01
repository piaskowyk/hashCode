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
		int how = rides.size();
		int k = 0;
		Ride min = null;
		
		while(min == null && k<how) {//znajdz pierwsz¹ niewykluczon¹ przesy³kê
			if(!isExcluded(rides.get(k), excluded)) {
				min = rides.get(k);
			}
			k++;
		}
		
		if(min == null) {// jeœli wszystkie przesy³ki s¹ wykluczone return null
			return null;
		}
		
		int minDist = min.distance;
		int tmp = 0;
		Pnt pointR = null;
		Pnt pointC = null;
		for(int i=0; i<how; i++) {

			if(isExcluded(rides.get(i), excluded)) continue;
			
			
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
	
	public boolean isExcluded(Ride item, Vector<Integer> excluded) {
		boolean ok = true;
		for(Integer x :  excluded) {
			if(x == item.id) {
				ok = false;
				break;
			}
		}
		return ok;
	}
}
