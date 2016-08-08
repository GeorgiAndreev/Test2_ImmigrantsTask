package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PoliceEmployeeException;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.immigrants.NormalImmigrant;

public class PoliceOfficer extends PoliceEmployee implements IPoliceEmployee{

	public PoliceOfficer(String name, Town grad, Country darjawa) throws PoliceEmployeeException {
		super(name, grad, darjawa);
	}

	@Override
	public void catchImmigrant(Immigrant immigrant) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkImmigrant(Immigrant immigrant) throws Exception {
		if (immigrant instanceof NormalImmigrant) {
			return true;
		}
		return false;
		
	}

}
