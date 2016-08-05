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
import immigrantsTask.immigrants.RadikalImmigrant;
import immigrantsTask.policeEmployees.PoliceOfficer;
import immigrantsTask.policeEmployees.SpecPoliceOfficer;
import immigrantsTask.weapons.Bomb;
import immigrantsTask.weapons.Pistol;
import immigrantsTask.weapons.SubmachineGun;
import immigrantsTask.weapons.Weapon;

public class Demo {

	public static void addTwoRelativesToImmigrants(ArrayList<Immigrant> immigrants) throws ImmigrantException {
		if (Validation.validateObject(immigrants)) {
			if (immigrants.size() % 2 == 1) {
				System.out.println("This method works only for even arraylist.");
				return;
			} else {
				for (int index = 0; index < immigrants.size() / 2; index ++) {
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
			//    create police employees and divide them eyually among towns 

			Town pleven = new Town("Pleven", 100000);
			Town varna = new Town("Varna", 200000);
			Town burgas = new Town("Burgas", 150000);
			Town smolqn = new Town("Smolqn", 90000);
			Town sofiq = new Town("Sofiq", 2000000);

			Country bulgariq = new Country("Bulgariq");

			bulgariq.addTown(pleven);
			bulgariq.addTown(varna);
			bulgariq.addTown(burgas);
			bulgariq.addTown(smolqn);
			bulgariq.addTown(sofiq);

			for (int index = 0; index < 1000; index++) {
				pleven.addPoliceEmployee(new PoliceOfficer(Generation.generateName(), pleven, bulgariq));
				varna.addPoliceEmployee(new PoliceOfficer(Generation.generateName(), varna, bulgariq));
				burgas.addPoliceEmployee(new PoliceOfficer(Generation.generateName(), burgas, bulgariq));
				smolqn.addPoliceEmployee(new PoliceOfficer(Generation.generateName(), smolqn, bulgariq));
				sofiq.addPoliceEmployee(new PoliceOfficer(Generation.generateName(), sofiq, bulgariq));
			}
			for (int index = 0; index < 400; index++) {
				pleven.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateName(), pleven, bulgariq));
				varna.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateName(), varna, bulgariq));
				burgas.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateName(), burgas, bulgariq));
				smolqn.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateName(), smolqn, bulgariq));
				sofiq.addPoliceEmployee(new SpecPoliceOfficer(Generation.generateName(), sofiq, bulgariq));
			}
			
			// 2. create 100 immigrants of different types with different probabilities, 
			//    set their names, money and passports where possible,
			//    give every immigrant two relatives from the other immigrants

			ArrayList<RadikalImmigrant> radikalImmigrants = new ArrayList<>(25);
			ArrayList<EkstremistImmigrant> ekstremistImmigrants = new ArrayList<>(35);
			ArrayList<NormalImmigrant> normalImmigrants = new ArrayList<>(40);
			ArrayList<Immigrant> immigrants = new ArrayList<>(100);

			for (int index = 0; index < 9; index++) {
				String name = Generation.generateName();
				radikalImmigrants
						.add(new RadikalImmigrant(name, Generation.generateMoney(850, 4000), new Passport(name)));
			}

			for (int index = 0; index < 16; index++) {
				radikalImmigrants
						.add(new RadikalImmigrant(Generation.generateName(), Generation.generateMoney(850, 4000)));
			}

			for (int index = 0; index < 35; index++) {
				ekstremistImmigrants
						.add(new EkstremistImmigrant(Generation.generateName(), Generation.generateMoney(850, 4000)));
			}

			for (int index = 0; index < 40; index++) {
				normalImmigrants
						.add(new NormalImmigrant(Generation.generateName(), Generation.generateMoney(400, 1000)));
			}

			immigrants.addAll(normalImmigrants);
			immigrants.addAll(radikalImmigrants);
			immigrants.addAll(ekstremistImmigrants);
			Collections.shuffle(immigrants);

			addTwoRelativesToImmigrants(immigrants);
			
			// 3. create 200 weapons from different types,
			//    every illegal immigrant tries to buy 5 of them,
			//    and if a weapon is sold it cannot be sold again

			int numberOfBombs = Generation.generateInteger(0, 70);
			int numberOfPistols = Generation.generateInteger(0, 70);
			int numberOfSubmachineGuns = (int) (200 - numberOfBombs - numberOfPistols);

			ArrayList<Bomb> bombs = new ArrayList<>(numberOfBombs);
			ArrayList<Pistol> pistols = new ArrayList<>(numberOfPistols);
			ArrayList<SubmachineGun> submachineGuns = new ArrayList<>(numberOfSubmachineGuns);

			for (int index = 0; index < numberOfBombs; index++) {
				bombs.add(new Bomb(Generation.generateMoney(300, 900)));
			}
			for (int index = 0; index < numberOfPistols; index++) {
				pistols.add(new Pistol(Generation.generateMoney(300, 900)));
			}
			for (int index = 0; index < numberOfSubmachineGuns; index++) {
				submachineGuns.add(new SubmachineGun(Generation.generateMoney(300, 900)));
			}

			ArrayList<Weapon> weapons = new ArrayList<>(numberOfSubmachineGuns + numberOfBombs + numberOfPistols);
			weapons.addAll(submachineGuns);
			weapons.addAll(pistols);
			weapons.addAll(bombs);
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
			
			// 5. show information for every immigrant: 
			//    current town, wheather has password, money and names of relatives 
			
			int count = 1;
			System.out.println("\nImmigrants unsorted:");
			for (Iterator<Immigrant> iterator = immigrants.iterator(); iterator.hasNext();) {
				Immigrant immigrant = (Immigrant) iterator.next();
				System.out.println("\n" + (count++));
				immigrant.showImmigrantInfo();
				System.out.println();
			}
			
			// 7. show towns sorted by number of survived inhabitants,
			//    show immigrants sorted by amount of money they have,
			//    show all immigrants that had bomb and had detonated it
			
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
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
