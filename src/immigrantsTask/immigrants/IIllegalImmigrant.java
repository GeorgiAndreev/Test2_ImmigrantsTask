package immigrantsTask.immigrants;

import immigrantsTask.weapons.Weapon;

public interface IIllegalImmigrant {
	
	boolean checkIfHasShootingWeapon() throws Exception;
	void buyWeapon(Weapon weapon) throws Exception;
	void shootAtPeople() throws Exception;

}
