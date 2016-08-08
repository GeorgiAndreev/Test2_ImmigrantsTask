package immigrantsTask.main;

import java.util.ArrayList;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.Immigrant;

public abstract class DemoMethods {

	public static void addTwoRelativesToImmigrants(ArrayList<Immigrant> immigrants) throws ImmigrantException {
		if (Validation.validateObjectIsNotNull(immigrants)) {
			if (immigrants.size() % 2 == 1) {
				System.out.println("This method works only for even arraylist.");
				return;
			} else {
				for (int index = 0; index < immigrants.size() / 2; index++) {
					immigrants.get(index).addRelative(immigrants.get(immigrants.size() - index - 1));
					immigrants.get(immigrants.size() - index - 1).addRelative(immigrants.get(index));
				}
				for (int index = 0; index < immigrants.size(); index += 2) {
					immigrants.get(index).addRelative(immigrants.get(index + 1));
					immigrants.get(index + 1).addRelative(immigrants.get(index));
				}
			}
		}
	}

}
