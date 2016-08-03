package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.ImigrantException;
import immigrantsTask.helpClasses.Validation;

public class NormalenImmigrant extends Immigrant {

	static final byte MAX_BROI_RODNINI = 10;
	
	Passport pasport = new Passport(this.name);

	public NormalenImmigrant(String name, float nachalnaSumaPari) throws ImigrantException {
		super(name, nachalnaSumaPari);
		
		// TODO Auto-generated constructor stub
	}

	
	@Override
	void addRodnina(Immigrant imigrant) throws ImigrantException {
		if (Validation.validateObject(imigrant)) {
			int numberOfRelativesNow = 0;
			for (Iterator<Immigrant> iterator = rodnini.iterator(); iterator.hasNext();) {
				Immigrant imigrantRodnina = (Immigrant) iterator.next();
				if (imigrantRodnina != null) {
					numberOfRelativesNow++;
				}
			}
			if (numberOfRelativesNow < 10) {
				this.rodnini.add(imigrant);
			} else {
				throw new ImigrantException("Ne moje pove4e rodnini max 10 moje da sa");
			}
			
		} else {
			throw new ImigrantException("Invalid relative");
		}
	}
	

}
