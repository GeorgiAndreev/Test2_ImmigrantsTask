package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.weapons.Weapon;

public class RadikalImmigrant extends Immigrant implements IIllegalImmigrant{
	
	static final byte MAX_BROI_ORUJIQ = 5;
	
	Passport pasport;
	ArrayList<Weapon> orujiq; 
	
	public RadikalImmigrant(String name, float nachalnaSumaPari) throws ImmigrantException {
		super(name, nachalnaSumaPari);
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
	public void kupiSiOrujie(Weapon orujie) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strelqiPoHora() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
