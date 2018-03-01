package hashCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wczytaj {

	public static void main(String[] args) throws FileNotFoundException 
	{
		int allRows, allColumns, vehicles, rides, bonus, steps;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj nazwe pliku: ");
		String nazwaPliku = input.next();
		Scanner wczytaj = new Scanner(new File (nazwaPliku));
		allRows = wczytaj.nextInt();
		allColumns = wczytaj.nextInt();
		vehicles = wczytaj.nextInt();
		rides = wczytaj.nextInt();
		bonus = wczytaj.nextInt();
		steps = wczytaj.nextInt();
		wczytaj.nextLine();
		for(int i=0; i<allRows; i++)
		{
			String linia = wczytaj.nextLine();
			for(int j=0; j<allColumns; j++)
			{
				
			}
		}
		
		
	}

}
