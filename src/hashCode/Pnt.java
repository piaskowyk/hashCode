package hashCode;

import java.awt.Point;

public class Pnt extends Point {
	
	public static int GetDistanceToPoint(Pnt p1, Pnt p2) {
		return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
	}
	
	public Pnt()
	{
		super();
	}
	
	public Pnt(int a, int b)
	{
		super(a,b);
	}

}
