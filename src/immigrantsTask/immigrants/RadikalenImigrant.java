package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.exceptions.ImigrantException;
import immigrantsTask.weapons.Weapon;

public class RadikalenImigrant extends Immigrant implements INelegalenImmigrant{
	
	static final byte MAX_BROI_ORUJIQ = 5;
	
	Passport pasport;
	ArrayList<Weapon> orujiq; 
	
	public RadikalenImigrant(String name, float nachalnaSumaPari) throws ImigrantException {
		super(name, nachalnaSumaPari);
		// TODO Auto-generated constructor stub
	}
	
	public RadikalenImigrant(String name, float nachalnaSumaPari, Passport passport) throws ImigrantException {
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
