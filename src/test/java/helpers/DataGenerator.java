package helpers;

import com.github.javafaker.Faker;

import java.util.Random;

public class DataGenerator {
    public static Faker faker = new Faker();

    public static String generateRandomString(int range) {
        String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < range; i++) {
            int number = new Random().nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public static String generateRandomInteger(int range) {
        String CHAR_LIST = "1234567890";
        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < range; i++) {
            int number = new Random().nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public static String generateRandomPhoneNumber() {
        String[] areaCode = {"531", "532", "533", "534", "535", "536", "537", "538", "539", "541", "541", "543", "544", "545", "546", "505", "506", "507", "551", "552", "553", "554", "555", "556", "557", "558", "559"};
        String countryCode = "90";
        return String.format("%s%s%s", countryCode, areaCode[faker.random().nextInt(0, areaCode.length - 1)], faker.number().digits(7));
    }

    public static String getRandomMasterPassPhoneNumber() {
        String[] phoneNumber = {"5543893417", "5304719621", "5375616668", "5548128476", "5435649113", "5342714495", "5065218199", "5380449546", "5552865741"};
        String countryCode = "90";
        int rnd = new Random().nextInt(phoneNumber.length);
        return String.format("%s%s", countryCode, phoneNumber[rnd]);
    }

    public static String generateRandomEmail() {
        return faker.name().username() + "@test.com";
    }

    public static String generateRandomName() {
        return faker.name().firstName().replaceAll("[^A-Za-z]", "").toUpperCase();
    }

    public static String generateRandomSurname() {
        return faker.name().lastName().replaceAll("[^A-Za-z]", "").toUpperCase();
    }

    public static String createPassword() {
        return faker.internet().password();
    }

    public static String generateRandomSearchKeyword() {
        String[] searchKeyword = {"et", "ET", "süt", "SÜT", "SUT", "kek", "muz", "şeker", "SEKER", "DOMATES"};
        return String.format(searchKeyword[faker.random().nextInt(0, searchKeyword.length - 1)]);
    }

    public static long generateRandomInteger(int min, int max) {
        return Math.round(min + Math.random() * (max - min + 1));
    }
}
