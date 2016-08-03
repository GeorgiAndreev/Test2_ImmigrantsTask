package immigrantsTask.immigrants;

import java.util.ArrayList;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.exceptions.IllegalImmigrantDiedFromRageException;
import immigrantsTask.weapons.Weapon;

public class EkstremistImmigrant extends Immigrant implements IIllegalImmigrant{

	private ArrayList<Weapon> orujiq;

	public EkstremistImmigrant(String name, float nachalnaSumaPari) throws ImmigrantException {
		super(name, nachalnaSumaPari);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void kupiSiOrujie(Weapon orujie) throws Exception {
		if (this.getInitialAmountMoney() >= orujie.getPrice()) {
			this.orujiq.add(orujie);
		} else {
			throw new IllegalImmigrantDiedFromRageException("Nqmam pari da si kupq orujie i umrqh ot qd");
		}
		
	}

	@Override
	public void strelqiPoHora() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
