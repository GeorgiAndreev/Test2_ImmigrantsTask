package immigrantsTask.country;

import java.util.ArrayList;

import immigrantsTask.exceptions.TownException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.IIllegalImmigrant;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.policeEmployees.PoliceEmployee;
import immigrantsTask.policeEmployees.PoliceOfficer;
import immigrantsTask.policeEmployees.SpecPoliceOfficer;

public class Town  implements Comparable<Town>{

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
		if (Validation.validateNumberIsPositive(numberOfInhabitants)) {
			this.numberOfInhabitants = numberOfInhabitants;
		} else {
			throw new TownException("Invalid number of inhabitants.");
		}
		this.policeEmployees = new ArrayList<PoliceEmployee>();
		this.immigrants = new ArrayList<Immigrant>();
	}

	public void addImigrant(Immigrant immigrant) throws Exception {
		if (Validation.validateObjectIsNotNull(immigrant)) {
			PoliceEmployee policeEmployee = this.choosePoliceEmployeeToExamineImmigrant(immigrant);
			if (policeEmployee.checkImmigrant(immigrant)) {
				this.immigrants.add(immigrant);
				immigrant.setCurrentTown(this);
				System.out.println("Immigrant entered " + this.name + " town.");
			} else {
				policeEmployee.catchImmigrant(immigrant);
			}
		} else {
			throw new TownException("Invalid immigrant");
		}
	}

	public void addPoliceEmployee(PoliceEmployee policeEmployee) throws TownException {
		if (Validation.validateObjectIsNotNull(policeEmployee)) {
			this.policeEmployees.add(policeEmployee);
		} else {
			throw new TownException("Invalid police employee.");
		}
	}

	public PoliceEmployee choosePoliceEmployeeToExamineImmigrant(Immigrant immigrant) {
		int indexOfPoliceEmployee = Generation.generateInteger(0, this.policeEmployees.size() - 1);
		if (this.policeEmployees.get(indexOfPoliceEmployee) instanceof PoliceOfficer) {
			return this.policeEmployees.get(indexOfPoliceEmployee);
		}
		if (this.policeEmployees.get(indexOfPoliceEmployee) instanceof SpecPoliceOfficer) {
			if (immigrant instanceof IIllegalImmigrant) {
				return this.policeEmployees.get(indexOfPoliceEmployee);
			} else {
				while (true) {
					indexOfPoliceEmployee = Generation.generateInteger(0, this.policeEmployees.size() - 1);
					if (this.policeEmployees.get(indexOfPoliceEmployee) instanceof PoliceOfficer) {
						break;
					}
				}
			}
		}
		return this.policeEmployees.get(indexOfPoliceEmployee);

	}
	
	@Override
	public int compareTo(Town town) {
		return this.getName().compareTo(((Town) town).getName());
	}

	public String getName() {
		return this.name;
	}

	public long getNumberOfInhabitants() {
		return numberOfInhabitants;
	}

	public void setNumberOfInhabitants(long numberOfInhabitants) throws TownException {
		if (Validation.validateNumberIsPositive(numberOfInhabitants)) {
			this.numberOfInhabitants = numberOfInhabitants;
		} else {
			throw new TownException("Invalid number of inhabitants.");
		}
	}

	public Country getCountry() {
		return country;
	}

	public ArrayList<Immigrant> getImmigrants() {
		return immigrants;
	}

	public void setCountry(Country country) throws TownException {
		if (Validation.validateObjectIsNotNull(country)) {
			this.country = country;
		} else {
			throw new TownException("Invalid country.");
		}

	}

}
