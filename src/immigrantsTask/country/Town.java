package immigrantsTask.country;

import java.util.ArrayList;

import immigrantsTask.exceptions.CountryException;
import immigrantsTask.exceptions.TownException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.IIllegalImmigrant;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.policeEmployees.PoliceEmployee;
import immigrantsTask.policeEmployees.PoliceOfficer;
import immigrantsTask.policeEmployees.SpecPoliceOfficer;

public class Town {
	
	private String name;
	private Country country;
	private ArrayList<PoliceEmployee> policeEmployees;
	private long numberOfInhabitants;

	private ArrayList<Immigrant> immigrants;
	
	public Town(String name, long numberOfInhabitants) throws TownException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new TownException("Invalid name.");
		}
		if (Validation.validateNumber(numberOfInhabitants)) {
			this.numberOfInhabitants = numberOfInhabitants;
		} else {
			throw new TownException("Invalid number of inhabitants.");
		}	
		this.policeEmployees = new ArrayList();
	}
	
	public void addImigrant(Immigrant immigrant) throws TownException {
		if (Validation.validateObject(immigrant)) {
			this.immigrants.add(immigrant);
		} else {
			throw new TownException("Invalid imigrant");
		}
	}
	
    public void addPoliceEmployee(PoliceEmployee policeEmployee) throws TownException {
    	if (Validation.validateObject(policeEmployee)) {
			this.policeEmployees.add(policeEmployee);
		} else {
			throw new TownException("Invalid police employee.");
		}	
	}
    
    public PoliceEmployee choosePoliceEmployeeToExamineImmigrant(Immigrant immigrant) {
    	int indexOfPoliceEmployee = Generation.generateInteger(0, this.policeEmployees.size());
    	if (this.policeEmployees.get(indexOfPoliceEmployee) instanceof PoliceOfficer) {
    		return this.policeEmployees.get(indexOfPoliceEmployee);
		} 
    	if (this.policeEmployees.get(indexOfPoliceEmployee) instanceof SpecPoliceOfficer) {
    		if (immigrant instanceof IIllegalImmigrant) {
    			return this.policeEmployees.get(indexOfPoliceEmployee);
			} else {
				while (true) {
					indexOfPoliceEmployee = Generation.generateInteger(0, this.policeEmployees.size());
					if (this.policeEmployees.get(indexOfPoliceEmployee) instanceof SpecPoliceOfficer) {
						break;
					}
				}	
			}	
		}
    	return this.policeEmployees.get(indexOfPoliceEmployee);
    	
    }
    
    public String getName() {
		return this.name;
	}

    public long getNumberOfInhabitants() {
		return numberOfInhabitants;
	}
}
