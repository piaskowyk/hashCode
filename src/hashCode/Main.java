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
		int allRows, allColumns, allCars, allRides, bonus, steps, points =0;
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj nazwe pliku: ");
		String nazwaPliku = input.next();
		Scanner wczytaj = new Scanner(new File (nazwaPliku));
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
							if(Pnt.GetDistanceToPoint(c.position, r.startPoint)-1 == r.startTime-currentTime)
							{
								c.freeTime=r.startTime+r.distance;
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
								if(Pnt.GetDistanceToPoint(c.position, r.startPoint)-1 > r.startTime-currentTime)
								{
									c.freeTime=r.startTime+r.distance;
									rides.remove(r);
									c.rides.push(r.number);
									points+=r.distance+bonus;
									break;
								}
								
							}
							if(c.freeTime<=currentTime)
							{
								c.freeTime=Pnt.GetDistanceToPoint(c.position, rides.get(0).startPoint)+rides.get(0).distance;
								rides.remove(rides.get(0));
								points+=rides.get(0).distance+bonus;
								c.rides.push(rides.get(0).number);
							}
						}
						else if(Pnt.GetDistanceToPoint(c.position, best.startPoint)-1 > best.startTime-currentTime &&
								Pnt.GetDistanceToPoint(c.position, best.startPoint)+best.distance>best.endTime-currentTime )
						{
							c.excluded.add(best.number);
							allCarsBusy = false;
						}
						
						
					}
						
				}
			}

		}
		System.out.println(points);
		wypisz(cars);
	}

	private static void wypisz(Car[] cars) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File("output"));
		int i=0;
		for (Car c : cars)
		{
			writer.print(i);
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
