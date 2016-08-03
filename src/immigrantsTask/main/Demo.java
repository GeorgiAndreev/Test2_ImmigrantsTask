package immigrantsTask.main;

import java.util.ArrayList;

import immigrantsTask.country.Country;
import immigrantsTask.country.Town;
import immigrantsTask.helpClasses.Generation;
import immigrantsTask.immigrants.EkstremistImmigrant;
import immigrantsTask.immigrants.NormalenImmigrant;
import immigrantsTask.immigrants.Passport;
import immigrantsTask.immigrants.RadikalenImigrant;
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

			bulgariq.addGrad(pleven);
			bulgariq.addGrad(varna);
			bulgariq.addGrad(burgas);
			bulgariq.addGrad(smolqn);
			bulgariq.addGrad(sofiq);

			for (int index = 0; index < 10000; index++) {
				pleven.addPoliceiskiSlujitel(new PoliceOfficer(Generation.generateName(), pleven, bulgariq));
				varna.addPoliceiskiSlujitel(new PoliceOfficer(Generation.generateName(), varna, bulgariq));
				burgas.addPoliceiskiSlujitel(new PoliceOfficer(Generation.generateName(), burgas, bulgariq));
				smolqn.addPoliceiskiSlujitel(new PoliceOfficer(Generation.generateName(), smolqn, bulgariq));
				sofiq.addPoliceiskiSlujitel(new PoliceOfficer(Generation.generateName(), sofiq, bulgariq));

			}
			for (int index = 0; index < 400; index++) {
				pleven.addPoliceiskiSlujitel(new SpecPoliceOfficer(Generation.generateName(), pleven, bulgariq));
				varna.addPoliceiskiSlujitel(new SpecPoliceOfficer(Generation.generateName(), varna, bulgariq));
				burgas.addPoliceiskiSlujitel(new SpecPoliceOfficer(Generation.generateName(), burgas, bulgariq));
				smolqn.addPoliceiskiSlujitel(new SpecPoliceOfficer(Generation.generateName(), smolqn, bulgariq));
				sofiq.addPoliceiskiSlujitel(new SpecPoliceOfficer(Generation.generateName(), sofiq, bulgariq));
			}

			ArrayList<RadikalenImigrant> radikalniImigranti = new ArrayList<>(25);
			ArrayList<EkstremistImmigrant> ekstremistiImigranti = new ArrayList<>(35);
			ArrayList<NormalenImmigrant> normalniImigranti = new ArrayList<>(40);

			for (int index = 0; index < 9; index++) {
				String name = Generation.generateName();
				radikalniImigranti.add(new RadikalenImigrant(name, (float) (Math.random() * 100 + 10), new Passport(name)));
			}

			for (int index = 0; index < 16; index++) {
				radikalniImigranti.add(new RadikalenImigrant(Generation.generateName(), (float) (Math.random() * 100 + 10)));
			}

			for (int index = 0; index < 35; index++) {
				ekstremistiImigranti.add(new EkstremistImmigrant(Generation.generateName(), (float) (Math.random() * 100 + 10)));
			}

			for (int index = 0; index < 40; index++) {
				normalniImigranti.add(new NormalenImmigrant(Generation.generateName(), (float) (Math.random() * 100 + 10)));
			}

			byte broiBombi = (byte) (Math.random() * 70 + 1);
			byte broiPistoleti = (byte) (Math.random() * 70 + 1);
			byte broiAvtomati = (byte) (200 - broiBombi - broiPistoleti);

			ArrayList<Bomb> bombi = new ArrayList<>(broiAvtomati);
			ArrayList<Pistol> pistoleti = new ArrayList<>(broiAvtomati);
			ArrayList<SubmachineGun> avtomati = new ArrayList<>(broiAvtomati);

			for (int index = 0; index < broiBombi; index++) {
				bombi.add(new Bomb((float) (Math.random() * 100 + 50)));
			}
			for (int index = 0; index < broiPistoleti; index++) {
				pistoleti.add(new Pistol((float) (Math.random() * 100 + 50)));
			}
			for (int index = 0; index < broiAvtomati; index++) {
				avtomati.add(new SubmachineGun((float) (Math.random() * 100 + 50)));
			}

			ArrayList<Weapon> orujiq = new ArrayList<>(broiAvtomati + broiBombi + broiPistoleti);
			orujiq.addAll(avtomati);
			orujiq.addAll(pistoleti);
			orujiq.addAll(bombi);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
