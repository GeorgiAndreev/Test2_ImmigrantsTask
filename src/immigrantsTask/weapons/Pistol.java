package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;

public class Pistol extends Weapon implements IShooting{

	public Pistol(float price) throws WeaponException {
		super(price);
	}

	@Override
	public int shoot() throws Exception {
		System.out.println("Pistol shooting...");
		int numberOfPatrons = (int)(Math.random() * 1000 + 1);
		return numberOfPatrons;
	}

}
