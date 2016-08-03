package immigrantsTask.helpClasses;

public abstract class Validation {
	
	public static boolean validateString(String string) {
		if ((string != null) && (!string.equals(""))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validateObject(Object object) {
		if (object != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validateNumber(long number) {
		if (number > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validateNumber(float number) {
		if (number > 0) {
			return true;
		} else {
			return false;
		}
	}


}
