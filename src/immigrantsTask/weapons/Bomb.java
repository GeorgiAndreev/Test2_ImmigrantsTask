package immigrantsTask.weapons;

import immigrantsTask.exceptions.WeaponException;
import immigrantsTask.exceptions.VzriviSeBombaException;

public class Bomb extends Weapon implements Detonateable{

	public Bomb(float price) throws WeaponException {
		super(price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void vzriviSe() throws VzriviSeBombaException {
		System.out.println("Bomba se vzrivqvq!");
		throw new VzriviSeBombaException("Bomba se vzrivi!");
		
	}

}
