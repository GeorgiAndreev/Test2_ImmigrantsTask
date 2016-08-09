package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;
import immigrantsTask.helpClasses.Validation;

public abstract class Weapon {
	
	private float price;
	private boolean isSold;
	
	public Weapon(float price) throws WeaponException {
		if (Validation.validateNumberIsPositive(price)) {
			this.price = price;
		} else {
			throw new WeaponException("Invalid price.");
		}
	}
	
	public void markThatWeaponIsSold() {
		this.isSold = true;
	}

	public float getPrice() {
		return price;
	}

	public boolean isSold() {
		return isSold;
	}

	
}
