package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.exceptions.TownException;
import immigrantsTask.exceptions.BombExploadedException;
import immigrantsTask.exceptions.CountryException;
import immigrantsTask.exceptions.IllegalImmigrantDiedFromRageException;
import immigrantsTask.weapons.Detonateable;
import immigrantsTask.weapons.Weapon;

public class EkstremistImmigrant extends Immigrant implements IIllegalImmigrant {

	boolean detonatedBomb;
	private ArrayList<Weapon> weapons;

	public EkstremistImmigrant(String name, float nachalnaSumaPari) throws ImmigrantException {
		super(name, nachalnaSumaPari);
		this.weapons = new ArrayList<>();
	}

	@Override
	public void buyWeapon(Weapon weapon) throws Exception {
		if (weapon.isSold() == true) {
			System.out.println("Weapon is already sold.");
			return;
		}
		if (this.getInitialAmountMoney() >= weapon.getPrice()) {
			this.weapons.add(weapon);
			weapon.markThatWeaponIsSold();
			setInitialAmountMoney(this.getInitialAmountMoney() - weapon.getPrice());
		} else {
			throw new IllegalImmigrantDiedFromRageException(
					"Immigrant had not enough money to buy weapon and died from rage.");
		}
	}

	@Override
	public void shootAtPeople() throws Exception {

	}
	
	public boolean checkIfHasBomb() {
		for (Iterator<Weapon> iterator = weapons.iterator(); iterator.hasNext();) {
			Weapon weapon = (Weapon) iterator.next();
			if (weapon instanceof Detonateable) {
				return true;
			}
		}
		return false;
	}

	public void detonateBomb() throws CountryException, TownException, BombExploadedException {
		if (!(this.checkIfHasBomb())) {
			System.out.println("This immigrant has no bomb to detonate.");
		} else {
			if (this.getCurrentTown() != null) {
				this.getCurrentTown().getCountry().removeTown(this.getCurrentTown());
				throw new BombExploadedException("Bomb exploaded in town " + this.getCurrentTown().getName());
			}
			
		}
	}

	@Override
	public void showImmigrantInfo() {
		System.out.println("\nThis immigrant has no passport,");
		super.showImmigrantInfo();
	}

	@Override
	public boolean checkIfHasShootingWeapon() throws Exception {
		for (Iterator<Weapon> iterator = weapons.iterator(); iterator.hasNext();) {
			Weapon weapon = (Weapon) iterator.next();
			if (!(weapon instanceof Detonateable)) {
				return true;
			}
		}
		return false;
	}

}
