package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.exceptions.ImigrantException;
import immigrantsTask.exceptions.UmrqhOtQdException;
import immigrantsTask.weapons.Weapon;

public class EkstremistImmigrant extends Immigrant implements INelegalenImmigrant{

	ArrayList<Weapon> orujiq;

	public EkstremistImmigrant(String name, float nachalnaSumaPari) throws ImigrantException {
		super(name, nachalnaSumaPari);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void kupiSiOrujie(Weapon orujie) throws Exception {
		if (this.nachalnaSumaPari >= orujie.getPrice()) {
			this.orujiq.add(orujie);
		} else {
			throw new UmrqhOtQdException("Nqmam pari da si kupq orujie i umrqh ot qd");
		}
		
	}

	@Override
	public void strelqiPoHora() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
