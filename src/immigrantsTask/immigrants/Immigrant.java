package immigrantsTask.immigrants;

import java.util.ArrayList;
import java.util.Iterator;

import immigrantsTask.country.Town;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Validation;

public abstract class Immigrant {
	
	private String name;
	private float initialAmountMoney;
	private Town currentTown;
	protected ArrayList<Immigrant> relatives;
	
	public void showImmigrantInfo() {
		System.out.println("lives currently in " + this.currentTown + ",");
		System.out.println("has " + this.getInitialAmountMoney() + "euros,");
		boolean hasRelatives = false;
		for (Iterator<Immigrant> iterator = relatives.iterator(); iterator.hasNext();) {
			Immigrant relative = (Immigrant) iterator.next();
			if (relative != null) {
				hasRelatives = true;
				break;
			}
		}
		if (hasRelatives) {
			System.out.println("and has these relatives:");
			for (Iterator<Immigrant> iterator = relatives.iterator(); iterator.hasNext();) {
				Immigrant relative = (Immigrant) iterator.next();
				if (relative != null) {
					System.out.print(relative.getName() + "; ");
				}
			}
		}
		if (!hasRelatives) {
			System.out.println("and has no relatives.");
		}
	}
	
	public Immigrant(String name, float initialAmountMoney) throws ImmigrantException {
		if (Validation.validateString(name)) {
			this.name = name;
		} else {
			throw new ImmigrantException("Invalid name.");
		}
		if (Validation.validateNumber(initialAmountMoney)) {
			this.initialAmountMoney = initialAmountMoney;
		} else {
			throw new ImmigrantException("Invalid money.");
		}
		
	}
	
	public void addRelative(Immigrant relative) throws ImmigrantException {
		if (Validation.validateObject(relative)) {
			this.relatives.add(relative);
			relative.relatives.add(this);
		} else {
			throw new ImmigrantException("Invalid relative");
		}
	}
	
	public int getNumberOfRelatives() {
		int numberOfRelatives =0;
		for (Iterator<Immigrant> iterator = relatives.iterator(); iterator.hasNext();) {
			Immigrant immigrant = (Immigrant) iterator.next();
			if(immigrant != null) {
				numberOfRelatives++;
			}
		}
		return numberOfRelatives;
	}

	public String getName() {
		return name;
	}

	public float getInitialAmountMoney() {
		return initialAmountMoney;
	}
	

}
