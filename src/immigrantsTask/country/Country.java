package immigrantsTask.country;

import java.util.ArrayList;

import immigrantsTask.exceptions.CountryException;
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
		if (Validation.validateObject(town)) {
			this.towns.add(town);
		} else {
			throw new CountryException("Invalid town.");
		}
	}

}
