package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.exceptions.IllegalImmigrantDiedFromRageException;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.weapons.IShooting;
import immigrantsTask.weapons.Weapon;

public class RadicalImmigrant extends Immigrant implements IIllegalImmigrant{
	
	static final byte MAX_NUMBER_OF_WEAPONS = 5;
	
	private Passport pasport;
	private ArrayList<IShooting> weapons; 
	
	public RadicalImmigrant(String name, float initialAmountMoney) throws ImmigrantException {
		super(name, initialAmountMoney);
		this.weapons = new ArrayList<IShooting>();
	}
	
	public RadicalImmigrant(String name, float initialAmountMoney, Passport passport) throws ImmigrantException {
		super(name, initialAmountMoney);
		if (Validation.validateObjectIsNotNull(passport)) {
			this.pasport = passport;
		} else {
			throw new ImmigrantException("Invalid passport.");
		}
		this.weapons = new ArrayList<IShooting>();
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
			this.getCurrentTown().getImmigrants().remove(this);
			throw new IllegalImmigrantDiedFromRageException("Immigrant had not enough money to buy weapon and died from rage.");
		}	
	}
	
    public void shootWithAllWeapons() throws Exception {	
    	int shotPatrons = 0;
		for (Iterator<IShooting> iterator = weapons.iterator(); iterator.hasNext();) {
			Weapon weapon = (Weapon) iterator.next();
			shotPatrons += ((IShooting) weapon).shoot();
		}
		
		int killedPeople = Generation.generateInteger((int)(shotPatrons / 10), (int)((shotPatrons / 10) * 7));
		System.out.println("killed people: " + killedPeople);
		this.getCurrentTown().setNumberOfInhabitants(this.getCurrentTown().getNumberOfInhabitants() - killedPeople);
	}

	@Override
	public void showImmigrantInfo() {
		if (this.pasport == null) {
			System.out.println("\nThis immigrant (" + this.getName() + ") has no passport,");
		} else {
			System.out.println("\nThis immigrant (" + this.getName() + ") has passport,");
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