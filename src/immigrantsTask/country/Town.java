package immigrantsTask.country;

import java.util.ArrayList;

import immigrantsTask.exceptions.DarjavaException;
import immigrantsTask.exceptions.GradException;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.policeEmployees.PoliceEmployee;

public class Town {
	
	private String name;
	private Country darjava;
	private ArrayList<PoliceEmployee> policeiskiSlujiteli;
	private long broiJiteli;
	private ArrayList<Immigrant> imigranti;
	
	public Town(String name, long broiJiteli) throws GradException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new GradException("Invalid name.");
		}
		if (Validation.validateNumber(broiJiteli)) {
			this.broiJiteli = broiJiteli;
		} else {
			throw new GradException("Invalid broi jiteli.");
		}	
	}
	
	public void addImigrant(Immigrant imigrant) throws GradException {
		if (Validation.validateObject(imigrant)) {
			this.imigranti.add(imigrant);
		} else {
			throw new GradException("Invalid imigrant");
		}
	}
	
    public void addPoliceiskiSlujitel(PoliceEmployee policeiskiSlujitel) throws GradException {
    	if (Validation.validateObject(policeiskiSlujitel)) {
			this.policeiskiSlujiteli.add(policeiskiSlujitel);
		} else {
			throw new GradException("Invalid policeiski slujitel");
		}
		
	}

}
