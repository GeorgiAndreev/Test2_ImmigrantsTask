package immigrantsTask.helpClasses;

import java.util.Random;

public abstract class Generation {
	
	public static String generateName() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String randomString = sb.toString();
		return randomString;
	}
	
	public static float generateMoney(int min, int max) {
		return (float)(min + (Math.random() * ((max - min) + 1)));
	}
	
	public static int generateInteger(int min, int max) {
		return (int)(min + (Math.random() * ((max - min) + 1)));
	}

}
