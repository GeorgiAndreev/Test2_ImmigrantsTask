package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;
import immigrantsTask.exceptions.BombExploadedException;

public class Bomb extends Weapon implements Detonateable{

	public Bomb(float price) throws WeaponException {
		super(price);
	}

	@Override
	public void explode() throws BombExploadedException {
		System.out.println("A bomb explodes!");
		throw new BombExploadedException("A bomb exploaded!");		
	}
}
