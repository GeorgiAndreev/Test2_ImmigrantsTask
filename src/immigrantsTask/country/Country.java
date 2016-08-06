package immigrantsTask.country;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.CountryException;
import immigrantsTask.helpClasses.NumberOfInhabitantsInTownsComparator;
import immigrantsTask.helpClasses.Validation;

public class Country {
	
	private String name;
	private ArrayList<Town> towns = new ArrayList();
	
	public Country(String name) throws CountryException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new CountryException("Invalid name.");
		}
		
	}
	
	public void addTown(Town town) throws CountryException{
		if (Validation.validateObjectIsNotNull(town)) {
			this.towns.add(town);
		} else {
			throw new CountryException("Invalid town.");
		}
	}
	
	public void showTownsSortedByNumberOfInhabitants(){
		ArrayList<Town> townsSorted = new ArrayList<Town>(this.towns.size());
		townsSorted.addAll(this.towns);
		townsSorted.sort(new NumberOfInhabitantsInTownsComparator());
		System.out.println("\nTowns in country " + this.name + " sorted by number of inhabitants:");
		int count = 1;
		for (Iterator iterator = townsSorted.iterator(); iterator.hasNext();) {
			Town town = (Town) iterator.next();
			System.out.println((count++) + ": " + town.getName() + ": " + town.getNumberOfInhabitants() + " inhabitants");
		}
	}

}
