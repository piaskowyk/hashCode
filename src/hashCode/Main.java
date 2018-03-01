package hashCode;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		int allRows, allColumns, allVehicles, allRides, bonus, steps;
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj nazwe pliku: ");
		String nazwaPliku = input.next();
		Scanner wczytaj = new Scanner(new File (nazwaPliku));
		allRows = wczytaj.nextInt();
		allColumns = wczytaj.nextInt();
		allVehicles= wczytaj.nextInt();
		allRides = wczytaj.nextInt();
		Ride[] rides = new Ride[allRides];
		Car[] cars = new Car[allVehicles];
		for(int i=0; i< allVehicles; i++)
		{
			cars[i]=new Car(0, 0);
		}
		bonus = wczytaj.nextInt();
		steps = wczytaj.nextInt();
		wczytaj.nextLine();
		for(int i=0; i<allRides; i++)
		{
			Point start = new Point(wczytaj.nextInt(), wczytaj.nextInt());
			Point end = new Point(wczytaj.nextInt(), wczytaj.nextInt());
			int startTime = wczytaj.nextInt();
			int endTime = wczytaj.nextInt();
			rides[i] = new Ride(start, end, startTime, endTime);
			wczytaj.nextLine();
		}
		wczytaj.close();
		input.close();
		
		
		
		
	}

}
