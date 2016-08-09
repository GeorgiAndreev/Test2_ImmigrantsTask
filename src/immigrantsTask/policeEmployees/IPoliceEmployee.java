package immigrantsTask.policeEmployees;

import immigrantsTask.immigrants.Immigrant;

public interface IPoliceEmployee {
	
	void catchImmigrant(Immigrant immigrant) throws Exception;
	boolean checkImmigrant(Immigrant immigrant) throws Exception;

}
