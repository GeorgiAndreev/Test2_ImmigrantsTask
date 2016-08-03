package immigrantsTask.weapons;

import immigrantsTask.exceptions.OrujieException;
import immigrantsTask.helpClasses.Validation;

public abstract class Weapon {
	
	float price;
	boolean isSold;
	
	public Weapon(float price) throws OrujieException {
		if (Validation.validateNumber(price)) {
			this.price = price;
		} else {
			throw new OrujieException("Invalid price");
		}
	}
	
	void prodaiSe() {
		this.isSold = true;
	}

	public float getPrice() {
		return price;
	}

	
}
