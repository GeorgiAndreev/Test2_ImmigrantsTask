package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;

public class SubmachineGun extends Weapon implements IShooting{

	public SubmachineGun(float price) throws WeaponException {
		super(price);
	}

	@Override
	public int shoot() throws Exception {
		System.out.println("Submachine gun shooting...");
		int numberOfPatrons = (int)(Math.random() * 1000 + 1);
		return numberOfPatrons;
	}

}
