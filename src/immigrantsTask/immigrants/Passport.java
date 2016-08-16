package immigrantsTask.immigrants;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PassportException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;

public class Passport {
	
	private String name;
	private int age;
	private Town homeTown;
	private Country homeCountry;
	
	public Passport(String name) throws PassportException {
		if (Validation.validateString(name)) {
			this.name = name;
			this.age = Generation.generateInteger(0, 100);
		} else {
			throw new PassportException("Invalid name.");
		}
	}
	
}
