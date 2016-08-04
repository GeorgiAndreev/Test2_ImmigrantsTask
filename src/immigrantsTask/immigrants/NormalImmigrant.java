package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.exceptions.PassportException;
import immigrantsTask.helpClasses.Validation;

public class NormalImmigrant extends Immigrant {

	static final byte MAX_BROI_RODNINI = 10;
	
	private Passport pasport;

	public NormalImmigrant(String name, float nachalnaSumaPari) throws ImmigrantException, PassportException {
		super(name, nachalnaSumaPari);
		this.pasport = new Passport(this.getName());
	}

	
	@Override
	public void addRelative(Immigrant immigrant) throws ImmigrantException {
		if (Validation.validateObject(immigrant)) {
			int currentNumberOfRelatives = 0;
			for (Iterator<Immigrant> iterator = relatives.iterator(); iterator.hasNext();) {
				Immigrant imigrantRodnina = (Immigrant) iterator.next();
				if (imigrantRodnina != null) {
					currentNumberOfRelatives++;
				}
			}
			if (currentNumberOfRelatives < 10) {
				this.relatives.add(immigrant);
			} else {
				throw new ImmigrantException("Cannot add more relatives: max number of relatives is 10.");
			}
			
		} else {
			throw new ImmigrantException("Invalid relative.");
		}
	}


	@Override
	public void showImmigrantInfo() {
		System.out.println("\nThis immigrant has passport,");
		super.showImmigrantInfo();
	}
	

}
