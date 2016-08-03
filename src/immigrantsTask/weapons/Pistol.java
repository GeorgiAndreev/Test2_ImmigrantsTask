package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;

public class Pistol extends Weapon implements IShooting{

	public Pistol(float price) throws WeaponException {
		super(price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int shoot() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
