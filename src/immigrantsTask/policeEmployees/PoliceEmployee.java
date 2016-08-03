package immigrantsTask.policeEmployees;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;

public abstract class PoliceEmployee {
	
	String name;
	Town grad;
	Country darjawa;
	
	public PoliceEmployee(String name, Town grad, Country darjawa) {
		this.name = name;
		this.grad = grad;
		this.darjawa = darjawa;
	}
	
	//public PoliceiskiSlujitel(String name) {
	//	this.name = name;
	//}
	
	

}
