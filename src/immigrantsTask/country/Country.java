package immigrantsTask.country;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import immigrantsTask.exceptions.CountryException;
import immigrantsTask.exceptions.TownException;
import immigrantsTask.helpClasses.NumberOfInhabitantsInTownsComparator;
import immigrantsTask.helpClasses.Validation;

public class Country {
	
	private String name;
	private TreeSet<Town> towns = new TreeSet<Town>();
	
	public Country(String name) throws CountryException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new CountryException("Invalid name.");
		}
		
	}
	
	public void addTown(Town town) throws CountryException, TownException{
		if (Validation.validateObjectIsNotNull(town)) {
			this.towns.add(town);
			town.setCountry(this);
		} else {
			throw new CountryException("Invalid town.");
		}
	}
	
	public void removeTown(Town town) throws CountryException, TownException{
		if (Validation.validateObjectIsNotNull(town)) {
			this.towns.remove(town);
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
