package immigrantsTask.country;

import java.util.ArrayList;

import immigrantsTask.exceptions.CountryException;
import immigrantsTask.helpClasses.Validation;

public class Country {
	
	private String name;
	private ArrayList<Town> gradove = new ArrayList();
	
	public Country(String name) throws CountryException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new CountryException("Invalid name");
		}
		
	}
	
	public void addGrad(Town grad) throws CountryException{
		if (Validation.validateObject(grad)) {
			this.gradove.add(grad);
		} else {
			throw new CountryException("Invalid grad");
		}
	}

}
