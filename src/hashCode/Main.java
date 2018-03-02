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
				if(rides.isEmpty()) break;
				for(Car actualCar: cars)
				{
					if(actualCar.freeTime<=currentTime)
					{
						double maxRate = 0.0, tempRate;
						Ride maxRide=null;
						Vector<Ride> toRemove = new Vector<Ride>();
						for(Ride actualRide: rides)
						{
							if(actualRide.endTime<currentTime)
							{
								toRemove.add(actualRide);
								continue;
							}
							tempRate = actualCar.rate(actualRide, currentTime, bonus, steps);
							if(tempRate!=0.0 && (maxRide==null || tempRate>maxRate || (tempRate==maxRate && actualRide.endTime-actualRide.distance<maxRide.endTime-maxRide.distance))) 
							{
								maxRate = tempRate;
								maxRide = actualRide;
							}
						}
						for(Ride remove : toRemove)
						{
							rides.remove(remove);
						}
						if(maxRide!=null)
						{
							points+=actualCar.getPoints(maxRide, currentTime, bonus);
							actualCar.rides.push(maxRide.number);
							actualCar.position=maxRide.endPoint;
							actualCar.freeTime=currentTime+actualCar.getTimeToDoRide(maxRide, currentTime);
							rides.remove(maxRide);
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
		for (Car c : cars)
		{
			writer.print(c.rides.size());
			for(Integer number : c.rides)
			{
				writer.print(" " + number);
			}
			writer.println();
		}
		writer.close();
	}
	
}
