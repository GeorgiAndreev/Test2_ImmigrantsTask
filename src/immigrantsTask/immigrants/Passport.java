package immigrantsTask.immigrants;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.helpClasses.Validation;

public class Passport {
	
	String name;
	int age;
	Town gradPoRojdenie;
	Country darjavaPoRojdenie;
	
	public Passport(String name) {
		if (Validation.validateString(name)) {
			this.name = name;
		}
	}
	
	

}
