package immigrantsTask.policeEmployees;

import immigrantsTask.immigrants.Immigrant;

public interface IPoliceEmployee {
	
	void arrestImmigrant(Immigrant immigrant) throws Exception;
	boolean checkImmigrant(Immigrant immigrant) throws Exception;

}
