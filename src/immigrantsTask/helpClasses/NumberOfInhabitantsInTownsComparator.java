package immigrantsTask.helpClasses;

import java.util.Comparator;

import immigrantsTask.country.Town;

public class NumberOfInhabitantsInTownsComparator implements Comparator<Town>{

	@Override
	public int compare(Town town1, Town town2) {
		return (int)(town1.getNumberOfInhabitants() - town2.getNumberOfInhabitants());
	}

}
