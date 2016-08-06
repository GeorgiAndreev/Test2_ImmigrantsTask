package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PoliceEmployeeException;
import immigrantsTask.immigrants.IIllegalImmigrant;
import immigrantsTask.immigrants.Immigrant;

public class SpecPoliceOfficer extends PoliceEmployee implements IPoliceEmployee{

	public SpecPoliceOfficer(String name, Town grad, Country darjawa) throws PoliceEmployeeException {
		super(name, grad, darjawa);
	}

	@Override
	public void arrestImmigrant(Immigrant immigrant) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void examineImmigrant(Immigrant immigrant) throws Exception {
		if (!(immigrant instanceof IIllegalImmigrant)) {
			System.out.println("Spec police officers examine only radical and extremist immigrants.");
			return;
		}
		
	}

}
