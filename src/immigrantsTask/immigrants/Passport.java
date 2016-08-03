package immigrantsTask.immigrants;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.helpClasses.Validation;

public class Passport {
	
	private String name;
	private int age;
	private Town gradPoRojdenie;
	private Country darjavaPoRojdenie;
	
	public Passport(String name) {
		if (Validation.validateString(name)) {
			this.name = name;
		}
	}
	
	

}
