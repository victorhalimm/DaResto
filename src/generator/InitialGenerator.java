package generator;

import java.util.Random;

public class InitialGenerator {
	static Random rand = new Random();
	
	public static String generate() {
        return String.valueOf((char)(rand.nextInt(26)+'A')) + String.valueOf((char)(rand.nextInt(26)+'A'));
    }
}
