package common;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomizer {
    private static int length = 10;
    private static boolean useLetters = true;
    private static boolean useNumbers = true;

    public static String generateString(){
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
