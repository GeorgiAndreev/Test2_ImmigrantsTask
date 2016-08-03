package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PoliceEmployeeException;

public class PoliceOfficer extends PoliceEmployee {

	public PoliceOfficer(String name, Town grad, Country darjawa) throws PoliceEmployeeException {
		super(name, grad, darjawa);
	}

}
