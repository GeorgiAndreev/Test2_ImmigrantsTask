package immigrantsTask.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import immigrantsTask.comparators.ImmigrantsByMoneyComparator;
import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
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

	public static void main(String[] args) {

		final int NUMBER_OF_TOWNS = 5;
		final int NUMBER_OF_ALL_IMMIGRANTS = 100;
		final int NUMBER_OF_RADICAL_IMMIGRANTS = 25;
		final int NUMBER_OF_EXTREMIST_IMMIGRANTS = 35;
		final int NUMBER_OF_NORMAL_IMMIGRANTS = 40;

		try {

			// 1. create country with 5 towns, set their number of inhabitants,
			// create police employees and divide them equally among towns

			Country country = new Country(Generation.generateTownOrCountryName());

			for (int index = 0; index < NUMBER_OF_TOWNS; index++) {
				country.addTown(
						new Town(Generation.generateTownOrCountryName(), Generation.generateInteger(80000, 200000)));
			}

			country.addPoliceEmployeesToAllTowns();

			// 2. create 100 immigrants of different types with different
			// probabilities,
			// set their names, money and passports where possible,
			// give every immigrant two relatives from the other immigrants

			ArrayList<Immigrant> immigrants = new ArrayList<>(NUMBER_OF_ALL_IMMIGRANTS);

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

			Collections.shuffle(immigrants);

			immigrants.get(0).addRelative(immigrants.get(immigrants.size() - 1));
			immigrants.get(immigrants.size() - 1).addRelative(immigrants.get(0));
			for (int index = 0; index < immigrants.size(); index += 2) {
				immigrants.get(index).addRelative(immigrants.get(index + 1));
				immigrants.get(index + 1).addRelative(immigrants.get(index));
			}
			for (int index = 1; index < immigrants.size() - 1; index += 2) {
				immigrants.get(index).addRelative(immigrants.get(index + 1));
				immigrants.get(index + 1).addRelative(immigrants.get(index));
			}

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
				if ((index >= (numberOfBombs + numberOfPistols))
						&& (index < (numberOfBombs + numberOfPistols + numberOfSubmachineGuns))) {
					weapons.add(new SubmachineGun(Generation.generateMoney(300, 900)));
				}
			}

			Collections.shuffle(weapons);

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

			// 4. all immigrants immigrate to random town,
			// when immigrant immigrates to a town, a police officer is chosen
			// to check their passport

			Town townToUseInDemo = country.getRandomTown();

			System.out.println("\nImmigrants start to immigrate...\n");
			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				immigrant.immigrate(townToUseInDemo);
			}

			// 5. show information for every immigrant:
			// current town, wheather has password, money and names of relatives

			int numberOfImmigrant = 1;
			System.out.println("\nAll immigrants unsorted:");
			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				System.out.println("\n" + (numberOfImmigrant++));
				immigrant.showImmigrantInfo();
				System.out.println();
			}

			// 6. 20 random illegal immigrants shoot or detonate themselves if
			// they have a bomb

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
				if (counter >= 20) {
					break;
				}
			}

			// 7. show towns sorted by number of survived inhabitants,
			// show immigrants sorted by amount of money they have,
			// show all immigrants that had bomb and had detonated it

			country.showTownsSortedByNumberOfInhabitants();

			immigrants.sort(new ImmigrantsByMoneyComparator());

			numberOfImmigrant = 1;
			System.out.println("\nAll immigrants sorted by amount of money:");
			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				System.out.println("\n" + (numberOfImmigrant++));
				immigrant.showImmigrantInfo();
				System.out.println();
			}

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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
