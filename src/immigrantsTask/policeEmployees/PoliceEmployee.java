package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PoliceEmployeeException;
import immigrantsTask.helpClasses.Validation;

public abstract class PoliceEmployee {
	
	private String name;
	private Town town;
	private Country country;
	
	public PoliceEmployee(String name, Town town, Country country) throws PoliceEmployeeException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new PoliceEmployeeException("Invalid name.");
		}
		if (Validation.validateObject(town)) {
			this.town = town;
		} else {
			throw new PoliceEmployeeException("Invalid town.");
		}
		if (Validation.validateObject(country)) {
			this.country = country;
		} else {
			throw new PoliceEmployeeException("Invalid country.");
		}
	}
	
	

}
