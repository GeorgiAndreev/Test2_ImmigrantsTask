package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.exceptions.TownException;
import immigrantsTask.exceptions.WeaponException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.exceptions.BombExploadedException;
import immigrantsTask.exceptions.CountryException;
import immigrantsTask.exceptions.IllegalImmigrantDiedFromRageException;
import immigrantsTask.weapons.Detonateable;
import immigrantsTask.weapons.IShooting;
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

	@Override
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
			if ((this.getCurrentTown() != null) && (this.getCurrentTown().getCountry() != null)
					&& (this.getCurrentTown().getName() != null)) {
				this.detonatedBomb = true;
				this.getCurrentTown().getCountry().removeTown(this.getCurrentTown());
				throw new BombExploadedException("Bomb exploaded in " + this.getCurrentTown().getName() + " town.");
			} else {
				System.out.println("Extremist must be in a town to detonate their bomb.");
			}
		}
	}

	@Override
	public boolean checkIfHasPassport() {
		return false;
	}

	@Override
	public void showImmigrantInfo() {
		System.out.println("\nThis immigrant (" + this.getName() + ") has no passport,");
		super.showImmigrantInfo();
	}

	public boolean hasDetonatedBomb() {
		return detonatedBomb;
	}

	@Override
	public void shootWithAllWeapons() throws Exception {
		int shotPatrons = 0;
		for (Iterator<Weapon> iterator = weapons.iterator(); iterator.hasNext();) {
			Weapon weapon = (Weapon) iterator.next();
			if (weapon instanceof IShooting) {
				shotPatrons += ((IShooting) weapon).shoot();
			}
		}
		int killedPeople = Generation.generateInteger((int) (shotPatrons / 10), (int) ((shotPatrons / 10) * 7));
		System.out.println("killed people: " + killedPeople);
		this.getCurrentTown().setNumberOfInhabitants(this.getCurrentTown().getNumberOfInhabitants() - killedPeople);
	}

	@Override
	public boolean checkIfHasShootingWeapon() throws WeaponException {
		if (Validation.validateObjectIsNotNull(weapons)) {
			if (this.weapons.isEmpty()) {
				return false;
			}
			for (Iterator<Weapon> iterator = weapons.iterator(); iterator.hasNext();) {
				Weapon weapon = (Weapon) iterator.next();
				if (weapon instanceof IShooting) {
					return true;
				}
			}
			return false;
		} else {
			throw new WeaponException("No list with weapons available.");
		}
	}

}
