package immigrantsTask.immigrants;

import java.util.Iterator;

import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.exceptions.PassportException;
import immigrantsTask.helpClasses.Validation;

public class NormalImmigrant extends Immigrant {

	static final byte MAX_BROI_RODNINI = 10;

	private Passport pasport;

	public NormalImmigrant(String name, float nachalnaSumaPari) throws ImmigrantException, PassportException {
		super(name, nachalnaSumaPari);
		this.pasport = new Passport(this.getName());
	}

	@Override
	public boolean isNormal() {
		return true;
	}

	@Override
	public void addRelative(Immigrant relative) throws ImmigrantException {
		if (Validation.validateObjectIsNotNull(relative) && (relative != this)) {
			int currentNumberOfRelatives = this.relatives.size();
			if (!relative.isNormal()) {
				if (currentNumberOfRelatives < 10) {
					this.relatives.add(relative);
					relative.relatives.add(this);
				} else {
					throw new ImmigrantException("Cannot add more relatives: max number of relatives is 10.");
				}
			}
			if (relative.isNormal()) {
				if ((currentNumberOfRelatives < 10) && (relative.returnCurrentNumberOfRelatives() < 10)) {
					this.relatives.add(relative);
					relative.relatives.add(this);
				} else {
					throw new ImmigrantException("Cannot add more relatives: max number of relatives is 10.");
				}
			}
		} else {
			throw new ImmigrantException("Invalid relative.");
		}
	}

	@Override
	public void showImmigrantInfo() {
		System.out.println("\nThis immigrant (" + this.getName() + ") has passport,");
		super.showImmigrantInfo();
	}

	@Override
	public boolean checkIfHasBomb() {
		return false;
	}

	@Override
	public boolean checkIfHasPassport() {
		return true;
	}

	@Override
	public boolean checkIfHasShootingWeapon() throws Exception {
		return false;
	}

}