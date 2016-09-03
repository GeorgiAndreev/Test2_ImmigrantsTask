package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import immigrantsTask.country.Town;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Validation;

public abstract class Immigrant implements Comparable<Immigrant> {
	
	private static int id;

	private String name;
	private float initialAmountMoney;
	private Town currentTown;
	protected TreeSet<Immigrant> relatives;
	private int lastId;

	public Immigrant(String name, float initialAmountMoney) throws ImmigrantException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new ImmigrantException("Invalid name.");
		}
		if (Validation.validateNumberIsPositive(initialAmountMoney)) {
			this.initialAmountMoney = initialAmountMoney;
		} else {
			throw new ImmigrantException("Invalid money.");
		}
		this.relatives = new TreeSet<Immigrant>();
		this.lastId = id++;
	}

	public void showImmigrantInfo() {
		if (this.currentTown == null) {
			System.out.println("has no current town to live in,");
		} else {
			System.out.println("lives currently in " + this.currentTown.getName() + ",");
		}

		System.out.println("has " + this.getInitialAmountMoney() + " euros,");
		boolean hasRelatives = (!relatives.isEmpty());
		if (hasRelatives) {
			System.out.print("and has these relatives: ");
			for (Iterator<Immigrant> iterator = relatives.iterator(); iterator.hasNext();) {
				Immigrant relative = (Immigrant) iterator.next();
				System.out.print(relative.getName() + "; ");
			}
		}
		if (!hasRelatives) {
			System.out.print("and has no relatives.");
		}
	}
	
	public abstract boolean checkIfHasBomb();
	
	public abstract boolean checkIfHasShootingWeapon() throws Exception;
	
	public abstract boolean checkIfHasPassport();

	public void addRelative(Immigrant relative) throws ImmigrantException {
		if (Validation.validateObjectIsNotNull(relative)  && (relative != this)) {
			this.relatives.add(relative);
			relative.relatives.add(this);
		} else {
			throw new ImmigrantException("Invalid relative.");
		}
	}
	
	public int returnCurrentNumberOfRelatives() {
		return this.relatives.size();
	}

	public void immigrate(Town town) throws Exception {
		if (Validation.validateObjectIsNotNull(town)) {
			if ((this.currentTown != null) && (this.currentTown.getName().equals(town.getName()))) {
				System.out.println("Immigrant is already in this town.");
				return;
			}
			town.addImigrant(this);
			for (Iterator<Immigrant> iterator = relatives.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				immigrant.immigrate(town);
			}
		} else {
			throw new ImmigrantException("Invalid town to immigrate to.");
		}
	}

	@Override
	public int compareTo(Immigrant immigrant) {
		//return this.getName().compareTo(((Immigrant) immigrant).getName());
		return this.getLastId() - ((Immigrant) immigrant).getLastId();
	}
	
	public boolean isNormal(){
		return false;
	}

	public String getName() {
		return name;
	}

	public float getInitialAmountMoney() {
		return initialAmountMoney;
	}

	public Town getCurrentTown() {
		return currentTown;
	}

	public int getLastId() {
		return lastId;
	}

	public void setInitialAmountMoney(float initialAmountMoney) throws ImmigrantException {
		if (Validation.validateNumberIsPositive(initialAmountMoney)) {
			this.initialAmountMoney = initialAmountMoney;
		} else {
			throw new ImmigrantException("Invalid amount money.");
		}
	}

	public void setCurrentTown(Town currentTown) throws ImmigrantException {
		if (Validation.validateObjectIsNotNull(currentTown)) {
			this.currentTown = currentTown;
		} else {
			throw new ImmigrantException("Invalid town.");
		}

	}

}
