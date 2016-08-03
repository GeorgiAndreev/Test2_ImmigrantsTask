package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.weapons.Weapon;

public class RadikalImmigrant extends Immigrant implements IIllegalImmigrant{
	
	static final byte MAX_NUMBER_OF_WEAPONS = 5;
	
	private Passport pasport;
	private ArrayList<Weapon> weapons; 
	
	public RadikalImmigrant(String name, float initialAmountMoney) throws ImmigrantException {
		super(name, initialAmountMoney);
		// TODO Auto-generated constructor stub
	}
	
	public RadikalImmigrant(String name, float nachalnaSumaPari, Passport passport) throws ImmigrantException {
		super(name, nachalnaSumaPari);
		this.pasport = passport;
		// TODO Auto-generated constructor stub
	}
	
	void vzriviBomba(){
		
	}

	@Override
	public void buyWeapon(Weapon weapon) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shootAtPeople() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
