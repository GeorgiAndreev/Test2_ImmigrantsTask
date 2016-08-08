package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;
import immigrantsTask.helpClasses.Generation;

public class Pistol extends Weapon implements IShooting{

	public Pistol(float price) throws WeaponException {
		super(price);
	}

	@Override
	public int shoot() throws Exception {
		System.out.println("Pistol shooting...");
		int numberOfPatrons = Generation.generateInteger(30, 200);
		return numberOfPatrons;
	}

}
