package immigrantsTask.main;

import java.util.ArrayList;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.immigrants.EkstremistImmigrant;
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

	public static void main(String[] args) {

		try {

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

			for (int index = 0; index < 10000; index++) {
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

			ArrayList<RadikalImmigrant> radikalImmigrants = new ArrayList<>(25);
			ArrayList<EkstremistImmigrant> ekstremistImmigrants = new ArrayList<>(35);
			ArrayList<NormalImmigrant> normalImmigrants = new ArrayList<>(40);

			for (int index = 0; index < 9; index++) {
				String name = Generation.generateName();
				radikalImmigrants.add(new RadikalImmigrant(name, Generation.generateMoney(400, 1000), new Passport(name)));
			}

			for (int index = 0; index < 16; index++) {
				radikalImmigrants.add(new RadikalImmigrant(Generation.generateName(), Generation.generateMoney(400, 1000)));
			}

			for (int index = 0; index < 35; index++) {
				ekstremistImmigrants.add(new EkstremistImmigrant(Generation.generateName(), Generation.generateMoney(400, 1000)));
			}

			for (int index = 0; index < 40; index++) {
				normalImmigrants.add(new NormalImmigrant(Generation.generateName(), Generation.generateMoney(400, 1000)));
			}

			byte numberOfBombs = (byte) (Math.random() * 70 + 1);
			byte numberOfPistols = (byte) (Math.random() * 70 + 1);
			byte numberOfSubmachineGuns = (byte) (200 - numberOfBombs - numberOfPistols);

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
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
