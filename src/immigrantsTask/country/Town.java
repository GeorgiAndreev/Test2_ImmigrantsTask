package immigrantsTask.country;

import java.util.ArrayList;

import immigrantsTask.exceptions.CountryException;
import immigrantsTask.exceptions.TownException;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.policeEmployees.PoliceEmployee;

public class Town {
	
	private String name;
	private Country darjava;
	private ArrayList<PoliceEmployee> policeiskiSlujiteli;
	private long broiJiteli;
	private ArrayList<Immigrant> imigranti;
	
	public Town(String name, long broiJiteli) throws TownException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new TownException("Invalid name.");
		}
		if (Validation.validateNumber(broiJiteli)) {
			this.broiJiteli = broiJiteli;
		} else {
			throw new TownException("Invalid broi jiteli.");
		}	
	}
	
	public void addImigrant(Immigrant imigrant) throws TownException {
		if (Validation.validateObject(imigrant)) {
			this.imigranti.add(imigrant);
		} else {
			throw new TownException("Invalid imigrant");
		}
	}
	
    public void addPoliceiskiSlujitel(PoliceEmployee policeiskiSlujitel) throws TownException {
    	if (Validation.validateObject(policeiskiSlujitel)) {
			this.policeiskiSlujiteli.add(policeiskiSlujitel);
		} else {
			throw new TownException("Invalid policeiski slujitel");
		}
		
	}

}
