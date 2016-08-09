package immigrantsTask.country;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import immigrantsTask.comparators.TownsByNumberOfInhabitantsComparator;
import immigrantsTask.exceptions.CountryException;
import immigrantsTask.exceptions.PoliceEmployeeException;
import immigrantsTask.exceptions.TownException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.policeEmployees.PoliceOfficer;
import immigrantsTask.policeEmployees.SpecPoliceOfficer;

public class Country {
	
	private static final int DEFAULT_NUMBER_OF_POLICE_OFFICERS_IN_EVERY_TOWN = 10000;
	private static final int DEFAULT_NUMBER_OF_SPEC_POLICE_OFFICERS_IN_EVERY_TOWN = 2000;
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
	
	public Town getRandomTown() {
		int townIndex = Generation.generateInteger(0, this.towns.size() - 1);
		int count = 0;
		for (Iterator<Town> iterator = towns.iterator(); iterator.hasNext();) {
			Town town = (Town) iterator.next();
			if (townIndex == count) {
				return town;
			}
			count++;
		}
		return null;
	}
	
	public void addPoliceEmployeesToAllTowns() throws TownException, PoliceEmployeeException {
		for (Iterator<Town> iterator = towns.iterator(); iterator.hasNext();) {
			Town town = (Town) iterator.next();
			//long numberOfPoliceOfficers = ((town.getNumberOfInhabitants() / 10) * 3);
			int numberOfPoliceOfficers = DEFAULT_NUMBER_OF_POLICE_OFFICERS_IN_EVERY_TOWN;
			for (int index = 0; index < numberOfPoliceOfficers; index++) {
				town.addPoliceEmployee(new PoliceOfficer(Generation.generateMaleOrFemaleName(), town, this));
			}
			//long numberOfSpecPoliceOfficers = (town.getNumberOfInhabitants() / 10);
			int numberOfSpecPoliceOfficers = DEFAULT_NUMBER_OF_SPEC_POLICE_OFFICERS_IN_EVERY_TOWN;
			for (int index = 0; index < numberOfSpecPoliceOfficers; index++) {
				town.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateMaleOrFemaleName(), town, this));
			}
		}
	}
	
	public void showTownsSortedByNumberOfInhabitants(){
		ArrayList<Town> townsSorted = new ArrayList<Town>(this.towns.size());
		townsSorted.addAll(this.towns);
		townsSorted.sort(new TownsByNumberOfInhabitantsComparator());
		System.out.println("\nTowns in country " + this.name + " sorted by number of inhabitants:");
		int count = 1;
		for (Iterator<Town> iterator = townsSorted.iterator(); iterator.hasNext();) {
			Town town = (Town) iterator.next();
			System.out.println((count++) + ": " + town.getName() + ": " + town.getNumberOfInhabitants() + " inhabitants");
		}
	}

}
