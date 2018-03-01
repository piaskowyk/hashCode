package hashCode;

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
		Point point;
		for(int i=0; i<how; i++) {
			tmp = rides[i].getDistance();
			if(tmp < minDist) {
				minDist = tmp;
				min = rides[i];
			}
		}
		return min;
	}
}
