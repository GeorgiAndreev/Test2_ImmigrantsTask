package immigrantsTask.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import immigrantsTask.comparators.ImmigrantsByMoneyComparator;
import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.exceptions.BombExploadedException;
import immigrantsTask.exceptions.CountryException;
import immigrantsTask.exceptions.ImmigrantException;
import immigrantsTask.exceptions.PassportException;
import immigrantsTask.exceptions.TownException;
import immigrantsTask.exceptions.WeaponException;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.immigrants.EkstremistImmigrant;
import immigrantsTask.immigrants.IIllegalImmigrant;
import immigrantsTask.immigrants.Immigrant;
import immigrantsTask.immigrants.NormalImmigrant;
import immigrantsTask.immigrants.RadicalImmigrant;
import immigrantsTask.weapons.Bomb;
import immigrantsTask.weapons.Pistol;
import immigrantsTask.weapons.SubmachineGun;
import immigrantsTask.weapons.Weapon;

public class Demo {
	
	private static final int NUMBER_OF_WEAPONS = 200;
	private static final int NUMBER_OF_TOWNS = 5;
	private static final int NUMBER_OF_ALL_IMMIGRANTS = 100;
	private static final int NUMBER_OF_RADICAL_IMMIGRANTS = 25;
	private static final int NUMBER_OF_EXTREMIST_IMMIGRANTS = 35;
	private static final int NUMBER_OF_NORMAL_IMMIGRANTS = 40;
	private static final int NUMBER_OF_SHOOTERS = 20;

	public static void main(String[] args) {
		
		Country country = null;
		ArrayList<Immigrant> immigrants = null;
		ArrayList<Weapon> weapons = null;
		Town townToUseInDemo = null;

		try {

			// 1. create country with 5 towns, set their number of inhabitants,
			// create police employees and divide them equally among towns

			country = new Country(Generation.generateTownOrCountryName());

			generateTowns(country);

			country.addPoliceEmployeesToAllTowns();

			// 2. create 100 immigrants of different types with different
			// probabilities,
			// set their names, money and passports where possible,
			// give every immigrant two relatives from the other immigrants

			immigrants = new ArrayList<Immigrant>(NUMBER_OF_ALL_IMMIGRANTS);

			generateImmigrants(immigrants);

			Collections.shuffle(immigrants);

			addTwoRelatives(immigrants);

			// 3. create 200 weapons from different types,
			// every illegal immigrant tries to buy 5 of them,
			// and if a weapon is sold it cannot be sold again

			int numberOfAllWeapons = NUMBER_OF_WEAPONS;
			int numberOfBombs = Generation.generateInteger(0, 70);
			int numberOfPistols = Generation.generateInteger(0, 70);
			int numberOfSubmachineGuns = numberOfAllWeapons - numberOfBombs - numberOfPistols;

			weapons = new ArrayList<Weapon>(numberOfAllWeapons);

			generateWeapons(weapons, numberOfAllWeapons, numberOfBombs, numberOfPistols, numberOfSubmachineGuns);

			Collections.shuffle(weapons);

			startBuyingWeapons(immigrants, weapons);

			// 4. all immigrants immigrate to random town,
			// when immigrant immigrates to a town, a police officer is chosen
			// to check their passport

			townToUseInDemo = country.getRandomTown();

			startImmigration(immigrants, townToUseInDemo);

			// 5. show information for every immigrant:
			// current town, wheather has password, money and names of relatives

			showImmigrantsUnsorted(immigrants);

			// 6. 20 random illegal immigrants shoot or detonate themselves if
			// they have a bomb

			immigrantsOpenFire(townToUseInDemo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. show towns sorted by number of survived inhabitants,
			// show immigrants sorted by amount of money they have,
			// show all immigrants that had bomb and had detonated it

			country.showTownsSortedByNumberOfInhabitants();

			showImmigrantsSortedByMoney(immigrants);

			showBombDetonators(townToUseInDemo);

		}

	}

	private static void addTwoRelatives(ArrayList<Immigrant> immigrants) throws ImmigrantException {
		immigrants.get(0).addRelative(immigrants.get(immigrants.size() - 1));
		for (int index = 0; index < immigrants.size(); index += 2) {
			immigrants.get(index).addRelative(immigrants.get(index + 1));
		}
		for (int index = 1; index < immigrants.size() - 1; index += 2) {
			immigrants.get(index).addRelative(immigrants.get(index + 1));
		}
	}

	private static void generateTowns(Country country) throws CountryException, TownException {
		for (int index = 0; index < NUMBER_OF_TOWNS; index++) {
			country.addTown(
					new Town(Generation.generateTownOrCountryName(), Generation.generateInteger(80000, 200000)));
		}
	}

	private static void startBuyingWeapons(ArrayList<Immigrant> immigrants, ArrayList<Weapon> weapons)
			throws Exception {
		System.out.println("\nImmigrants start to buy weapons...\n");

		for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
			Immigrant immigrant = (Immigrant) iterator.next();
			if (immigrant instanceof IIllegalImmigrant) {
				int indexOfWeapon = Generation.generateInteger(0, weapons.size() - 1);
				for (int index = 1; index <= 5; index++) {
					((IIllegalImmigrant) immigrant).buyWeapon(weapons.get(indexOfWeapon));
				}
			}
		}
	}

	private static void generateWeapons(ArrayList<Weapon> weapons, int numberOfAllWeapons, int numberOfBombs,
			int numberOfPistols, int numberOfSubmachineGuns) throws WeaponException {
		for (int index = 0; index < numberOfAllWeapons; index++) {
			if (index < numberOfBombs) {
				weapons.add(new Bomb(Generation.generateMoney(300, 900)));
			}
			if ((index >= numberOfBombs) && (index < (numberOfBombs + numberOfPistols))) {
				weapons.add(new Pistol(Generation.generateMoney(300, 900)));
			}
			if ((index >= (numberOfBombs + numberOfPistols))
					&& (index < (numberOfBombs + numberOfPistols + numberOfSubmachineGuns))) {
				weapons.add(new SubmachineGun(Generation.generateMoney(300, 900)));
			}
		}
	}

	private static void generateImmigrants(ArrayList<Immigrant> immigrants)
			throws ImmigrantException, PassportException {
		for (int index = 0; index < NUMBER_OF_ALL_IMMIGRANTS; index++) {
			if ((index >= 0) && (index < NUMBER_OF_RADICAL_IMMIGRANTS)) {
				immigrants.add(new RadicalImmigrant(Generation.generateMaleOrFemaleName(),
						Generation.generateMoney(850, 4000)));
			}
			if ((index >= (NUMBER_OF_RADICAL_IMMIGRANTS))
					&& (index < (NUMBER_OF_RADICAL_IMMIGRANTS + NUMBER_OF_EXTREMIST_IMMIGRANTS))) {
				immigrants.add(new EkstremistImmigrant(Generation.generateMaleOrFemaleName(),
						Generation.generateMoney(850, 4000)));
			}
			if (index >= (NUMBER_OF_ALL_IMMIGRANTS - NUMBER_OF_NORMAL_IMMIGRANTS)) {
				immigrants.add(new NormalImmigrant(Generation.generateMaleOrFemaleName(),
						Generation.generateMoney(400, 1000)));
			}
		}
	}

	private static void startImmigration(ArrayList<Immigrant> immigrants, Town townToUseInDemo) throws Exception {
		System.out.println("\nImmigrants start to immigrate...\n");
		for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
			Immigrant immigrant = (Immigrant) iterator.next();
			immigrant.immigrate(townToUseInDemo);
		}
	}

	private static void showImmigrantsUnsorted(ArrayList<Immigrant> immigrants) {
		int numberOfImmigrant = 1;
		System.out.println("\nAll immigrants unsorted:");
		for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
			Immigrant immigrant = (Immigrant) iterator.next();
			System.out.println("\n" + (numberOfImmigrant++));
			immigrant.showImmigrantInfo();
			System.out.println();
		}
	}

	private static void immigrantsOpenFire(Town townToUseInDemo)
			throws CountryException, TownException, BombExploadedException, Exception {
		int counter = 0;
		System.out.println("\n");
		for (Iterator<Immigrant> iterator = townToUseInDemo.getImmigrants().iterator(); iterator.hasNext();) {
			Immigrant immigrant = (Immigrant) iterator.next();
			if (immigrant.checkIfHasBomb()) {
				((EkstremistImmigrant) immigrant).detonateBomb();
				counter++;
			} else {
				if (immigrant.checkIfHasShootingWeapon()) {
					((IIllegalImmigrant) immigrant).shootWithAllWeapons();
					counter++;
				}
			}
			if (counter >= NUMBER_OF_SHOOTERS) {
				break;
			}
		}
	}

	private static void showBombDetonators(Town townToUseInDemo) {
		int countDetonatedBombs = 0;
		System.out.println("\nExtremist immigrants that detonated bomb:");
		for (Iterator<Immigrant> iterator = townToUseInDemo.getImmigrants().iterator(); iterator.hasNext();) {
			Immigrant immigrant = (Immigrant) iterator.next();
			if (immigrant instanceof EkstremistImmigrant) {
				if (((EkstremistImmigrant) immigrant).hasDetonatedBomb()) {
					System.out.println("\n" + (++countDetonatedBombs));
					immigrant.showImmigrantInfo();
					System.out.println();
				}
			}
		}
		if (countDetonatedBombs == 0) {
			System.out.println("No bombs detonated.");
		}
	}

	private static void showImmigrantsSortedByMoney(ArrayList<Immigrant> immigrants) {
		immigrants.sort(new ImmigrantsByMoneyComparator());

		int numberOfImmigrant = 1;
		System.out.println("\nAll immigrants sorted by amount of money:");
		for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
			Immigrant immigrant = (Immigrant) iterator.next();
			System.out.println("\n" + (numberOfImmigrant++));
			immigrant.showImmigrantInfo();
			System.out.println();
		}
	}

}
