package immigrantsTask.country;

import java.util.ArrayList;

import immigrantsTask.exceptions.DarjavaException;
import immigrantsTask.helpClasses.Validation;

public class Country {
	
	private String name;
	private ArrayList<Town> gradove = new ArrayList();
	
	public Country(String name) throws DarjavaException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new DarjavaException("Invalid name");
		}
		
	}
	
	public void addGrad(Town grad) throws DarjavaException{
		if (Validation.validateObject(grad)) {
			this.gradove.add(grad);
		} else {
			throw new DarjavaException("Invalid grad");
		}
	}

}
