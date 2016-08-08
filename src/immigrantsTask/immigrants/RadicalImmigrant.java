package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.IllegalImmigrantDiedFromRageException;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.weapons.Detonateable;
import immigrantsTask.weapons.IShooting;
import immigrantsTask.weapons.Weapon;

public class RadicalImmigrant extends Immigrant implements IIllegalImmigrant{
	
	static final byte MAX_NUMBER_OF_WEAPONS = 5;
	
	private Passport pasport;
	private ArrayList<IShooting> weapons; 
	
	public RadicalImmigrant(String name, float initialAmountMoney) throws ImmigrantException {
		super(name, initialAmountMoney);
		this.weapons = new ArrayList<>();
	}
	
	public RadicalImmigrant(String name, float initialAmountMoney, Passport passport) throws ImmigrantException {
		super(name, initialAmountMoney);
		if (Validation.validateObjectIsNotNull(passport)) {
			this.pasport = passport;
		} else {
			throw new ImmigrantException("Invalid passport.");
		}
		this.weapons = new ArrayList<>();
	}
	
	@Override
	public void buyWeapon(Weapon weapon) throws Exception {
		if (!(weapon instanceof IShooting)) {
			System.out.println("Radical immigrants can buy only shooting weapons.");
			return;
		}
		if (weapon.isSold() == true) {
			System.out.println("Weapon is already sold.");
			return;
		}
		if (this.getInitialAmountMoney() >= weapon.getPrice()) {
			this.weapons.add((IShooting) weapon);
			weapon.markThatWeaponIsSold();
			setInitialAmountMoney(this.getInitialAmountMoney() - weapon.getPrice());
		} else {
			throw new IllegalImmigrantDiedFromRageException("Immigrant had not enough money to buy weapon and died from rage.");
		}	
	}
	
    public void shootWithAllWeapons() throws Exception {	
    	int shotPatrons = 0;
		for (Iterator<IShooting> iterator = weapons.iterator(); iterator.hasNext();) {
			Weapon weapon = (Weapon) iterator.next();
			shotPatrons += ((IShooting) weapon).shoot();
		}
		int killedPeople = Generation.generateInteger(((1/10) * shotPatrons), ((7/10) * shotPatrons));
		this.getCurrentTown().setNumberOfInhabitants(this.getCurrentTown().getNumberOfInhabitants() - killedPeople);
	}

	@Override
	public void showImmigrantInfo() {
		if (this.pasport == null) {
			System.out.println("\nThis immigrant has no passport,");
		} else {
			System.out.println("\nThis immigrant has passport,");
		}
		super.showImmigrantInfo();
	}

	
	public boolean checkIfHasShootingWeapon() {
		if (weapons.isEmpty()) {
			return false;
		}
		return true;
	}

	public Passport getPasport() {
		return pasport;
	}

}
