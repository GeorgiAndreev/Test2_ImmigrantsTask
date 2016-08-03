package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.country.Town;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Validation;

public abstract class Immigrant {
	
	private String name;
	private float initialAmountMoney;
	private Town grad;
	protected ArrayList<Immigrant> relatives;
	
	public Immigrant(String name, float nachalnaSumaPari) throws ImmigrantException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new ImmigrantException("Invalid name");
		}
		if (Validation.validateNumber(nachalnaSumaPari)) {
			this.initialAmountMoney = nachalnaSumaPari;
		} else {
			throw new ImmigrantException("Invalid money");
		}
		
	}
	
	void addRodnina(Immigrant imigrant) throws ImmigrantException {
		if (Validation.validateObject(imigrant)) {
			this.relatives.add(imigrant);
		} else {
			throw new ImmigrantException("Invalid relative");
		}
	}

	public String getName() {
		return name;
	}

	public float getInitialAmountMoney() {
		return initialAmountMoney;
	}

}
