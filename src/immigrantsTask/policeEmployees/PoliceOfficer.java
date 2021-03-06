package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PoliceEmployeeException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.EkstremistImmigrant;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.immigrants.NormalImmigrant;
import immigrantsTask.immigrants.RadicalImmigrant;

public class PoliceOfficer extends PoliceEmployee implements IPoliceEmployee {

	public PoliceOfficer(String name, Town grad, Country darjawa) throws PoliceEmployeeException {
		super(name, grad, darjawa);
	}

	@Override
	public void catchImmigrant(Immigrant immigrant) throws Exception {
		if (Validation.validateObjectIsNotNull(immigrant)) {
			System.out.println("Police officer caught immigrant.");
		} else {
			throw new PoliceEmployeeException("Invalid immigrant for catching.");
		}
	}

	@Override
	public boolean checkImmigrant(Immigrant immigrant) throws Exception {
		if (Validation.validateObjectIsNotNull(immigrant)) {
			if (immigrant.checkIfHasBomb() == true) {
				return true;
			}
			if (immigrant.checkIfHasPassport() == true) {
				return true;
			}
			if (immigrant.checkIfHasPassport() == false) {
				if (Generation.generateInteger(0, 100) > 50) {
					this.catchImmigrant(immigrant);
				} else {
					return true;
				}
			}
		} else {
			throw new PoliceEmployeeException("Invalid immigrant to be checked by police officer.");
		}
		return false;
	}

}
