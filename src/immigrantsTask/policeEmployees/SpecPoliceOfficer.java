package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.PoliceEmployeeException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.EkstremistImmigrant;
import immigrantsTask.immigrants.IIllegalImmigrant;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.immigrants.NormalImmigrant;
import immigrantsTask.immigrants.RadicalImmigrant;

public class SpecPoliceOfficer extends PoliceEmployee implements IPoliceEmployee {

	public SpecPoliceOfficer(String name, Town grad, Country darjawa) throws PoliceEmployeeException {
		super(name, grad, darjawa);
	}

	@Override
	public void catchImmigrant(Immigrant immigrant) throws Exception {
		if (Validation.validateObjectIsNotNull(immigrant)) {
			System.out.println("Spec police officer caught immigrant.");
		} else {
			throw new PoliceEmployeeException("Invalid immigrant for catching.");
		}
	}

	@Override
	public boolean checkImmigrant(Immigrant immigrant) throws Exception {
		if (Validation.validateObjectIsNotNull(immigrant)) {
			if (!(immigrant instanceof IIllegalImmigrant)) {
				throw new PoliceEmployeeException(
						"Spec police officers examine only radical and extremist immigrants.");
			}
			if (immigrant instanceof RadicalImmigrant) {
				if (((RadicalImmigrant) immigrant).getPasport() != null) {
					return true;
				}
				if (((RadicalImmigrant) immigrant).getPasport() == null) {
					if (Generation.generateInteger(0, 100) > 10) {
						this.catchImmigrant(immigrant);
					} else {
						return true;
					}
				}
			}
			if (immigrant instanceof EkstremistImmigrant) {
				if (Generation.generateInteger(0, 100) > 10) {
					this.catchImmigrant(immigrant);
				} else {
					return true;
				}
			}
		} else {
			throw new PoliceEmployeeException("Invalid immigrant to be checked by spec police officer.");
		}
		return false;
	}

}
