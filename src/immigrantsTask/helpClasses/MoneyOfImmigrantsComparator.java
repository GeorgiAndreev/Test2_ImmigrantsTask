package immigrantsTask.helpClasses;

import java.util.Comparator;

import immigrantsTask.immigrants.Immigrant;

public class MoneyOfImmigrantsComparator implements Comparator<Immigrant>{

	@Override
	public int compare(Immigrant immigrant1, Immigrant immigrant2) {
		return (int)(immigrant1.getInitialAmountMoney() - immigrant2.getInitialAmountMoney());
	}

}
