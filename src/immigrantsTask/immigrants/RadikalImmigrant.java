package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.exceptions.IllegalImmigrantDiedFromRageException;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.weapons.IShooting;
import immigrantsTask.weapons.Weapon;

public class RadikalImmigrant extends Immigrant implements IIllegalImmigrant{
	
	static final byte MAX_NUMBER_OF_WEAPONS = 5;
	
	private Passport pasport;
	private ArrayList<Weapon> weapons; 
	
	public RadikalImmigrant(String name, float initialAmountMoney) throws ImmigrantException {
		super(name, initialAmountMoney);
		this.weapons = new ArrayList<>();
	}
	
	public RadikalImmigrant(String name, float nachalnaSumaPari, Passport passport) throws ImmigrantException {
		super(name, nachalnaSumaPari);
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
			this.weapons.add(weapon);
			weapon.markThatWeaponIsSold();
			setInitialAmountMoney(this.getInitialAmountMoney() - weapon.getPrice());
		} else {
			throw new IllegalImmigrantDiedFromRageException("Immigrant had not enough money to buy weapon and died from rage.");
		}	
	}

	@Override
	public void shootAtPeople() throws Exception {	
		
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

}
