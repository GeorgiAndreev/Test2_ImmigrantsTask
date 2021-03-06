package immigrantsTask.helpClasses;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public abstract class Generation {
	
	public static String generateTownOrCountryName() {
		return generateMaleOrFemaleName();
	}
	
	public static String generateMaleOrFemaleName() {
		int zeroOrOne = generateInteger(0, 1);
		if (zeroOrOne == 0) {
			return generateFemaleName();
		} else {
			return generateMaleName();
		}
	}
	
	public static String generateMaleName() {
		String maleName;
		char[] vowelSmallChars = "aeiouy".toCharArray();
		char[] consonantSmallChars = "bcdfghjklmnpqrstvwxz".toCharArray();
		char[] vowelBigChars = "AEIOUY".toCharArray();
		char[] consonantBigChars = "BCDFGHJKLMNPQRSTVWXZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int evenOrOdd = generateInteger(0, 1);
		if (evenOrOdd == 0) {
			//example: Ahil
			char ch = vowelBigChars[random.nextInt(vowelBigChars.length)];
		    sb.append(ch);
		    int numberOfLetters = generateOddInteger(3, 7);
			for (int index = 1; index <= numberOfLetters; index++) {
				if (index % 2 == 1) {
					ch = consonantSmallChars[random.nextInt(consonantSmallChars.length)];
				    sb.append(ch);
				}
				if (index % 2 == 0) {
					ch = vowelSmallChars[random.nextInt(vowelSmallChars.length)];
				    sb.append(ch);
				}
			}
			maleName = sb.toString();
		} else {
			//example: Halim
			char ch = consonantBigChars[random.nextInt(consonantBigChars.length)];
		    sb.append(ch);
		    int numberOfLetters = generateEvenInteger(2, 8);
			for (int index = 1; index <= numberOfLetters; index++) {
				if (index % 2 == 0) {
					ch = consonantSmallChars[random.nextInt(consonantSmallChars.length)];
				    sb.append(ch);
				}
				if (index % 2 == 1) {
					ch = vowelSmallChars[random.nextInt(vowelSmallChars.length)];
				    sb.append(ch);
				}
			}
			maleName = sb.toString();
		}
		
		return maleName;
	}
	
	public static String generateFemaleName() {
		StringBuilder sb = new StringBuilder(generateMaleName());
		sb.append('a');
		String femaleName = sb.toString();
		return femaleName;
	}
	
	public static float roundToSecondPositionAfterPoint(float floatToRound)
	{
		//DecimalFormat newFormat = new DecimalFormat("#.##");
		//float twoDecimal =  Float.valueOf(newFormat.format(floatToRound));
		//return twoDecimal;
		//return (float) ((float)Math.round (floatToRound * 100.0) / 100.0);
		BigDecimal money = BigDecimal.valueOf(floatToRound);
		money = money.setScale(2, BigDecimal.ROUND_HALF_UP);
		return money.floatValue();
    }
	
	public static float generateMoney(int min, int max) {
		float unRoundMoney = (float)(min + (Math.random() * ((max - min) + 1)));
		return roundToSecondPositionAfterPoint(unRoundMoney);
	}
	
	public static int generateInteger(int min, int max) {
		return (int)(min + (Math.random() * ((max - min) + 1)));
	}
	
	public static int generateOddInteger(int min, int max) {	
		int newOddInt;
		int newInt;
		while (true) {
			newInt = generateInteger(min, max);
			if (newInt % 2 == 1) {
				newOddInt = newInt;
				break;
			}
		}
		return newOddInt;
	}
	
	public static int generateEvenInteger(int min, int max) {	
		int newEvenInt;
		int newInt;
		while (true) {
			newInt = generateInteger(min, max);
			if (newInt % 2 == 0) {
				newEvenInt = newInt;
				break;
			}
		}
		return newEvenInt;
	}

}
