package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PoliceEmployeeException;

public class SpecPoliceOfficer extends PoliceEmployee {

	public SpecPoliceOfficer(String name, Town grad, Country darjawa) throws PoliceEmployeeException {
		super(name, grad, darjawa);
	}

}
