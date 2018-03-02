package hashCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


public class Main
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		String[] NazwyPlikow = {"a","b","c","d","e"};
		for(String nazwaPliku : NazwyPlikow)
		{
			int allRows, allColumns, allCars, allRides, bonus, steps, points =0;
			Scanner input = new Scanner(System.in);
			Scanner wczytaj = new Scanner(new File (nazwaPliku+".in"));
			allRows = wczytaj.nextInt();
			allColumns = wczytaj.nextInt();
			allCars= wczytaj.nextInt();
			allRides = wczytaj.nextInt();
			Vector<Ride> rides = new Vector<Ride>();
			Car[] cars = new Car[allCars];
			for(int i=0; i< allCars; i++)
			{
				cars[i]=new Car(0, 0);
			}
			bonus = wczytaj.nextInt();
			steps = wczytaj.nextInt();
			wczytaj.nextLine();
			for(int i=0; i<allRides; i++)
			{
				Pnt start = new Pnt(wczytaj.nextInt(), wczytaj.nextInt());
				Pnt end = new Pnt(wczytaj.nextInt(), wczytaj.nextInt());
				int startTime = wczytaj.nextInt();
				int endTime = wczytaj.nextInt();
				rides.add(new Ride(start, end, startTime, endTime, i));
				wczytaj.nextLine();
			}
			wczytaj.close();
			input.close();
		
			for(int currentTime=0; currentTime<steps; currentTime++)
			{
				boolean allCarsBusy = false;
				while(!allCarsBusy)
				{
					allCarsBusy = true;
					for(Car c : cars)
					{
						boolean tmp = false;
						if(c.freeTime <= currentTime)
						{
							for(Ride r: rides)
							{
								if(Pnt.GetDistanceToPoint(c.position, r.startPoint) == r.startTime-currentTime &&
										Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance<=r.endTime-currentTime)
								{
									c.freeTime=Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance;
									if(r.startTime+r.distance>c.freeTime) c.freeTime=r.startTime+r.distance;
									rides.remove(r);
									c.rides.push(r.number);
									points+=r.distance+bonus;
									tmp=true;
									break;
								}
							}
							if(tmp) continue;
							for(Ride r: rides)
							{
								if(Pnt.GetDistanceToPoint(c.position, r.startPoint)+1 == r.startTime-currentTime  &&
										Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance<=r.endTime-currentTime)
								{
									c.freeTime=Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance;
									if(r.startTime+r.distance>c.freeTime) c.freeTime=r.startTime+r.distance;
									rides.remove(r);
									c.rides.push(r.number);
									points+=r.distance+bonus;
									tmp=true;
									break;
								}
							}
							if(tmp) continue;
							for(Ride r: rides)
							{
								if(Pnt.GetDistanceToPoint(c.position, r.startPoint)+2 == r.startTime-currentTime &&
										Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance<=r.endTime-currentTime)
								{
									c.freeTime=Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance;
									if(r.startTime+r.distance>c.freeTime) c.freeTime=r.startTime+r.distance;
									rides.remove(r);
									c.rides.push(r.number);
									points+=r.distance+bonus;
									tmp=true;
									break;
								}
							}
							if(tmp) continue;
							Ride best = c.getDistanceToClosestRide(rides);
							if(best == null)
							{
								for(Ride r: rides)
								{
									if(Pnt.GetDistanceToPoint(c.position, r.startPoint)-1 > r.startTime-currentTime && Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance<=r.endTime-currentTime)
									{
										c.freeTime=Pnt.GetDistanceToPoint(c.position, r.startPoint)+r.distance;
										if(r.startTime+r.distance>c.freeTime) c.freeTime=r.startTime+r.distance;
										points+=r.distance;
										if(Pnt.GetDistanceToPoint(c.position, r.startPoint)-1 == r.startTime-currentTime) points+=bonus;
										rides.remove(r);
										c.rides.push(r.number);
										break;
									}
									
								}
								if(c.freeTime<=currentTime && rides.size()>0)
								{
									int tmp1=0;
									Ride tmp2;
									do
									{
										tmp2 = rides.get(tmp1);
										tmp1++;
									}
									while(rides.size()>tmp1 && Pnt.GetDistanceToPoint(c.position, tmp2.startPoint)+tmp2.distance>tmp2.endTime-currentTime
											&& Pnt.GetDistanceToPoint(c.position, tmp2.startPoint)>= tmp2.startTime-currentTime );
									if(tmp1<rides.size())
									{
										c.freeTime=Pnt.GetDistanceToPoint(c.position, tmp2.startPoint)+tmp2.distance;
										if(tmp2.startTime+tmp2.distance>c.freeTime) c.freeTime=tmp2.startTime+tmp2.distance;
										points+=tmp2.distance;
										if(Pnt.GetDistanceToPoint(c.position, tmp2.startPoint)-1 == tmp2.startTime-currentTime) points+=bonus;
										c.rides.push(tmp2.number);
										rides.remove(tmp2);
									}
								}
							}
							else if(Pnt.GetDistanceToPoint(c.position, best.startPoint)-1 > best.startTime-currentTime &&
									Pnt.GetDistanceToPoint(c.position, best.startPoint)+best.distance<=best.endTime-currentTime )
							{
								c.excluded.add(best.number);
								allCarsBusy = false;
							}
							
							
						}
							
					}
				}

			}
			System.out.println(points);
			wypisz(cars, nazwaPliku);
		}
		
	}

	private static void wypisz(Car[] cars, String nazwaPliku) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File(nazwaPliku+".out"));
		int i=0;
		for (Car c : cars)
		{
			writer.print(c.rides.size());
			for(Integer number : c.rides)
			{
				writer.print(" " + number);
			}
			i++;
			writer.println();
		}
		writer.close();
	}
	
}
