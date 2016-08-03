package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.exceptions.IllegalImmigrantDiedFromRageException;
import immigrantsTask.weapons.Weapon;

public class EkstremistImmigrant extends Immigrant implements IIllegalImmigrant{

	private ArrayList<Weapon> weapons;

	public EkstremistImmigrant(String name, float nachalnaSumaPari) throws ImmigrantException {
		super(name, nachalnaSumaPari);
	}

	@Override
	public void buyWeapon(Weapon weapon) throws Exception {
		if (weapon.isSold() == true) {
			System.out.println("Weapon is already sold.");
			return;
		}
		if (this.getInitialAmountMoney() >= weapon.getPrice()) {
			this.weapons.add(weapon);
		} else {
			throw new IllegalImmigrantDiedFromRageException("Immigrant had not enough money to buy weapon and died from rage.");
		}
	}

	@Override
	public void shootAtPeople() throws Exception {
		
		
		
	}

}
