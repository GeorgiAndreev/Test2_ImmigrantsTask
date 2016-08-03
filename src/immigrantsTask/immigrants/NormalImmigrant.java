package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Validation;

public class NormalImmigrant extends Immigrant {

	static final byte MAX_BROI_RODNINI = 10;
	
	private Passport pasport = new Passport(this.getName());

	public NormalImmigrant(String name, float nachalnaSumaPari) throws ImmigrantException {
		super(name, nachalnaSumaPari);
		
		// TODO Auto-generated constructor stub
	}

	
	@Override
	void addRodnina(Immigrant imigrant) throws ImmigrantException {
		if (Validation.validateObject(imigrant)) {
			int numberOfRelativesNow = 0;
			for (Iterator<Immigrant> iterator = relatives.iterator(); iterator.hasNext();) {
				Immigrant imigrantRodnina = (Immigrant) iterator.next();
				if (imigrantRodnina != null) {
					numberOfRelativesNow++;
				}
			}
			if (numberOfRelativesNow < 10) {
				this.relatives.add(imigrant);
			} else {
				throw new ImmigrantException("Ne moje pove4e rodnini max 10 moje da sa");
			}
			
		} else {
			throw new ImmigrantException("Invalid relative");
		}
	}
	

}
