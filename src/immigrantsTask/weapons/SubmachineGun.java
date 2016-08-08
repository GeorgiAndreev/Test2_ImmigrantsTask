package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;
import immigrantsTask.helpClasses.Generation;

public class SubmachineGun extends Weapon implements IShooting{

	public SubmachineGun(float price) throws WeaponException {
		super(price);
	}

	@Override
	public int shoot() throws Exception {
		System.out.println("Submachine gun shooting...");
		int numberOfPatrons = Generation.generateInteger(100, 400);
		return numberOfPatrons;
	}

}
