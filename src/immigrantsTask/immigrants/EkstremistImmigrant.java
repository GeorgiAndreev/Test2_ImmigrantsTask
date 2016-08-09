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

	private boolean detonatedBomb;
	private ArrayList<Weapon> weapons;

	public EkstremistImmigrant(String name, float initialAmountMoney) throws ImmigrantException {
		super(name, initialAmountMoney);
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
			if ((this.getCurrentTown() != null) && (this.getCurrentTown().getCountry() != null) && (this.getCurrentTown().getName() != null)) {
				this.detonatedBomb = true;
				this.getCurrentTown().getCountry().removeTown(this.getCurrentTown());
				throw new BombExploadedException("Bomb exploaded in " + this.getCurrentTown().getName() + " town.");
			} else {
				System.out.println("Extremist must be in a town to detonate their bomb.");
			}		
		}
	}

	@Override
	public void showImmigrantInfo() {
		System.out.println("\nThis immigrant (" + this.getName() + ") has no passport,");
		super.showImmigrantInfo();
	}

	public boolean isDetonatedBomb() {
		return detonatedBomb;
	}

}
