package immigrantsTask.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.helpClasses.MoneyOfImmigrantsComparator;
import immigrantsTask.helpClasses.Validation;
import immigrantsTask.immigrants.EkstremistImmigrant;
import immigrantsTask.immigrants.IIllegalImmigrant;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.immigrants.NormalImmigrant;
import immigrantsTask.immigrants.Passport;
import immigrantsTask.immigrants.RadicalImmigrant;
import immigrantsTask.policeEmployees.PoliceOfficer;
import immigrantsTask.policeEmployees.SpecPoliceOfficer;
import immigrantsTask.weapons.Bomb;
import immigrantsTask.weapons.Pistol;
import immigrantsTask.weapons.SubmachineGun;
import immigrantsTask.weapons.Weapon;

public class Demo {

	public static void addTwoRelativesToImmigrants(ArrayList<Immigrant> immigrants) throws ImmigrantException {
		if (Validation.validateObjectIsNotNull(immigrants)) {
			if (immigrants.size() % 2 == 1) {
				System.out.println("This method works only for even arraylist.");
				return;
			} else {
				for (int index = 0; index < immigrants.size() / 2; index++) {
					immigrants.get(index).addRelative(immigrants.get(immigrants.size() - index - 1));
					immigrants.get(immigrants.size() - index - 1).addRelative(immigrants.get(index));
				}
				for (int index = 0; index < immigrants.size(); index += 2) {
					immigrants.get(index).addRelative(immigrants.get(index + 1));
					immigrants.get(index + 1).addRelative(immigrants.get(index));
				}
			}
		}
	}

	public static void main(String[] args) {

		try {

			// 1. create country with 5 towns, set their number of inhabitants,
			// create police employees and divide them equally among towns

			Country bulgariq = new Country("Bulgariq");

			Town pleven = new Town("Pleven", 100000);
			Town varna = new Town("Varna", 200000);
			Town burgas = new Town("Burgas", 150000);
			Town smolqn = new Town("Smolqn", 90000);
			Town sofiq = new Town("Sofiq", 2000000);

			bulgariq.addTown(pleven);
			bulgariq.addTown(varna);
			bulgariq.addTown(burgas);
			bulgariq.addTown(smolqn);
			bulgariq.addTown(sofiq);

			for (int index = 0; index < 1000; index++) {
				pleven.addPoliceEmployee(new PoliceOfficer(Generation.generateMaleOrFemaleName(), pleven, bulgariq));
				varna.addPoliceEmployee(new PoliceOfficer(Generation.generateMaleOrFemaleName(), varna, bulgariq));
				burgas.addPoliceEmployee(new PoliceOfficer(Generation.generateMaleOrFemaleName(), burgas, bulgariq));
				smolqn.addPoliceEmployee(new PoliceOfficer(Generation.generateMaleOrFemaleName(), smolqn, bulgariq));
				sofiq.addPoliceEmployee(new PoliceOfficer(Generation.generateMaleOrFemaleName(), sofiq, bulgariq));
			}
			for (int index = 0; index < 400; index++) {
				pleven.addPoliceEmployee(
						new SpecPoliceOfficer(Generation.generateMaleOrFemaleName(), pleven, bulgariq));
				varna.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateMaleOrFemaleName(), varna, bulgariq));
				burgas.addPoliceEmployee(
						new SpecPoliceOfficer(Generation.generateMaleOrFemaleName(), burgas, bulgariq));
				smolqn.addPoliceEmployee(
						new SpecPoliceOfficer(Generation.generateMaleOrFemaleName(), smolqn, bulgariq));
				sofiq.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateMaleOrFemaleName(), sofiq, bulgariq));
			}

			// 2. create 100 immigrants of different types with different
			// probabilities,
			// set their names, money and passports where possible,
			// give every immigrant two relatives from the other immigrants

			int numberOfAllImmigrants = 100;
			int numberOfRadicalsWithPassport = 9;
			int numberOfRadicalsWithoutPassport = 16; 
			int numberOfExtremists = 35;
			int numberOfNormals = 40;
			
			ArrayList<Immigrant> immigrants = new ArrayList<>(numberOfAllImmigrants);

			for (int index = 0; index < numberOfAllImmigrants; index++) {
				if (index < numberOfRadicalsWithPassport) {
					String name = Generation.generateMaleOrFemaleName();
					immigrants.add(new RadicalImmigrant(name, Generation.generateMoney(850, 4000), new Passport(name)));
				}
				if ((index >= numberOfRadicalsWithPassport) && (index < (numberOfRadicalsWithPassport + numberOfRadicalsWithoutPassport))) {
					immigrants.add(new RadicalImmigrant(Generation.generateMaleOrFemaleName(),
							Generation.generateMoney(850, 4000)));
				}
				if ((index >= (numberOfRadicalsWithPassport + numberOfRadicalsWithoutPassport)) && (index < (numberOfRadicalsWithPassport + numberOfRadicalsWithoutPassport + numberOfExtremists))) {
					immigrants.add(new EkstremistImmigrant(Generation.generateMaleOrFemaleName(),
							Generation.generateMoney(850, 4000)));
				}
				if ((index >= (numberOfRadicalsWithPassport + numberOfRadicalsWithoutPassport + numberOfExtremists)) && (index < (numberOfRadicalsWithPassport + numberOfRadicalsWithoutPassport + numberOfExtremists + numberOfNormals))) {
					immigrants.add(new NormalImmigrant(Generation.generateMaleOrFemaleName(),
							Generation.generateMoney(400, 1000)));
				}			
			}

			Collections.shuffle(immigrants);

			addTwoRelativesToImmigrants(immigrants);

			// 3. create 200 weapons from different types,
			// every illegal immigrant tries to buy 5 of them,
			// and if a weapon is sold it cannot be sold again

			int numberOfAllWeapons = 200;
			int numberOfBombs = Generation.generateInteger(0, 70);
			int numberOfPistols = Generation.generateInteger(0, 70);
			int numberOfSubmachineGuns = numberOfAllWeapons - numberOfBombs - numberOfPistols;
			
			ArrayList<Weapon> weapons = new ArrayList<>(numberOfAllWeapons);

			for (int index = 0; index < numberOfAllWeapons; index++) {
				if (index < numberOfBombs) {
					weapons.add(new Bomb(Generation.generateMoney(300, 900)));
				}
				if ((index >= numberOfBombs) && (index < (numberOfBombs + numberOfPistols))) {
					weapons.add(new Pistol(Generation.generateMoney(300, 900)));
				}
				if ((index >= (numberOfBombs + numberOfPistols)) && (index < (numberOfBombs + numberOfPistols + numberOfSubmachineGuns))) {
					weapons.add(new SubmachineGun(Generation.generateMoney(300, 900)));
				}	
			}

			Collections.shuffle(weapons);

			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				if (immigrant instanceof IIllegalImmigrant) {
					int indexOfWeapon = Generation.generateInteger(0, weapons.size() - 1);
					for (int index = 1; index <= 5; index++) {
						((IIllegalImmigrant) immigrant).buyWeapon(weapons.get(indexOfWeapon));
					}
				}
			}

			// 4. all immigrants immigrate to random town,
			// when immigrant immigrates to a town, a police officer is chosen
			// to check their passport

			System.out.println("\n");
			
			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				pleven.addImigrant(immigrant);
			}

			// 5. show information for every immigrant:
			// current town, wheather has password, money and names of relatives

			int count = 1;
			System.out.println("\nImmigrants unsorted:");
			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				System.out.println("\n" + (count++));
				immigrant.showImmigrantInfo();
				System.out.println();
			}

			// 6. 20 random illegal immigrants shoot or detonate themselves if
			// they have a bomb

			int counter = 0;
			System.out.println("\n");
			for (Iterator<Immigrant> iterator = pleven.getImmigrants().iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				if (immigrant instanceof EkstremistImmigrant) {
					if (((EkstremistImmigrant) immigrant).checkIfHasBomb()) {
						((EkstremistImmigrant) immigrant).detonateBomb();
						counter++;
					} 
				}
				if (immigrant instanceof RadicalImmigrant) {
					if (((RadicalImmigrant) immigrant).checkIfHasShootingWeapon()) {
						((RadicalImmigrant) immigrant).shootWithAllWeapons();
						counter++;
					}
				}
				if (counter >= 20) {
					break;
				}
			}

			// 7. show towns sorted by number of survived inhabitants,
			// show immigrants sorted by amount of money they have,
			// show all immigrants that had bomb and had detonated it

			bulgariq.showTownsSortedByNumberOfInhabitants();

			immigrants.sort(new MoneyOfImmigrantsComparator());

			count = 1;
			System.out.println("\nImmigrants sorted by amount of money:");
			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				System.out.println("\n" + (count++));
				immigrant.showImmigrantInfo();
				System.out.println();
			}

			int countDetonatedBombs = 0;
			System.out.println("\nExtremist immigrants that detonated bomb:");
			for (Iterator<Immigrant> iterator = pleven.getImmigrants().iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				if (immigrant instanceof EkstremistImmigrant) {
					if (((EkstremistImmigrant) immigrant).isDetonatedBomb()) {
						System.out.println("\n" + (++countDetonatedBombs));
						immigrant.showImmigrantInfo();
					}
				}
			}
			if (countDetonatedBombs == 0) {
				System.out.println("No bombs detonated.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
