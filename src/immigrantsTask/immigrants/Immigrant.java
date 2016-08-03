package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.country.Town;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Validation;

public abstract class Immigrant {
	
	String name;
	float nachalnaSumaPari;
	Town grad;
	ArrayList<Immigrant> rodnini;
	
	public Immigrant(String name, float nachalnaSumaPari) throws ImmigrantException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new ImmigrantException("Invalid name");
		}
		if (Validation.validateNumber(nachalnaSumaPari)) {
			this.nachalnaSumaPari = nachalnaSumaPari;
		} else {
			throw new ImmigrantException("Invalid money");
		}
		
	}
	
	void addRodnina(Immigrant imigrant) throws ImmigrantException {
		if (Validation.validateObject(imigrant)) {
			this.rodnini.add(imigrant);
		} else {
			throw new ImmigrantException("Invalid relative");
		}
	}
	
	

}
