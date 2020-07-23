package local;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Validation {
    public static final String DATA_JSON_FILE = "data/IBANData.json";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping().create();
    static List<Country> countriesData;

    static {
        try {
            countriesData = readCountries(DATA_JSON_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Validation() {
    }

    public static void readAndValidate(String pathStr, String name) throws IOException {
        Validation validation = new Validation();
        Path path = Paths.get(pathStr);
        File file = new File(path + "\\" + name);
        Scanner scanner = new Scanner(file);
        List<String> inputList = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            inputList.add(scanner.next());
        }

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(validation.generateOutputLocAndName(name))))
            );
            for (String s : inputList) {
                bufferedWriter.write(s + ";" + validation.validate(s, false) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String generateOutputLocAndName(String name) {
        return name.replace(".txt", "Out.txt");
    }

    public boolean validate(String number, boolean needConsole) throws IOException {
        if (checkLength(number)) {
            if (hasNoMoreThan2Letters(number)) {
                if (checkFirst2Chars(number)) {
                    if (checkIfLetterCapital(number)) {
                        String flag = generateFlag(number);
                        if (isValidCountry(flag)) {
                            if (isValidLengthToFlag(number.length(), flag)) {
                                String BBAN = generateBBAN(number);
                                String initChars = generateInitChars(number);
                                String switchedNumber = BBAN + initChars;
                                BigInteger convertedNumber = new BigInteger(switchedNumber);
                                BigInteger div = new BigInteger("97");
                                BigInteger rem = convertedNumber.mod(div);
                                BigInteger one = new BigInteger("1");
                                if (rem.compareTo(one) == 0) {
                                    if (needConsole) {
                                        System.out.println("IBAN number is valid!");
                                    }
                                    return true;
                                }
                                if (needConsole) {
                                    System.out.println("IBAN number is not valid!");
                                }
                                return false;
                            }
                            if (needConsole) {
                                System.out.println("Country flag does not match number length!");
                            }
                            return false;
                        }
                        if (needConsole) {
                            System.out.println("Country flag is not valid!");
                        }
                        return false;
                    }
                    if (needConsole) {
                        System.out.println("Letters must be capital!");
                    }
                    return false;
                }
                if (needConsole) {
                    System.out.println("Number must begin with 2 letters!");
                }
                return false;
            }
            if (needConsole) {
                System.out.println("There can not be more than 2 letters in the code!");
            }
            return false;
        }
        if (needConsole) {
            System.out.println("Number is too long!");
        }
        return false;
    }

    public static Map<String, Integer> generateLetterValues() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        char ch;
        int number = 10;
        for (ch = 'A'; ch <= 'Z'; ch++) {
            map.put(String.valueOf(ch), number);
            number++;
        }
        return map;
    }

    public static List<String> readWebMultipleInput(String name, String pathStr) throws IOException {
        Path path = Paths.get(pathStr);
        File file = new File(path + "\\" + name);
        Scanner scanner = new Scanner(file);
        List<String> list = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            list.add(scanner.next());
        }
        return list;
    }

    public static List<Country> readCountries(String filename) throws IOException {
        Path path = Paths.get(filename);
        BufferedReader reader = Files.newBufferedReader(path);
        Type t = new TypeToken<List<Country>>() {
        }.getType();
        List<Country> result = GSON.fromJson(reader, t);
        return result;
    }

    public static boolean checkLength(String number) {
        return number.length() <= 34;
    }

    public static boolean checkFirst2Chars(String testNumber) {
        char first = testNumber.charAt(0);
        char second = testNumber.charAt(1);
        return Character.isLetter(first) && Character.isLetter(second);
    }

    public static boolean hasNoMoreThan2Letters(String testNumber) {
        int count = 0;
        for (int i = 0; i < testNumber.length(); i++) {
            if (Character.isLetter(testNumber.charAt(i))) {
                count++;
            }
        }
        return count == 2;
    }

    public static boolean checkIfLetterCapital(String testNumber) {
        for (int i = 0; i < testNumber.length(); i++) {
            if (Character.isLetter(testNumber.charAt(i))) {
                if (Character.isLowerCase(testNumber.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public String generateFlag(String testNumber) {
        char first = testNumber.charAt(0);
        char second = testNumber.charAt(1);
        return String.valueOf(first) + second;
    }

    public String generateBBAN(String testNumber) {
        return testNumber.substring(4);
    }

    public String generateInitChars(String testNumber) {
        Validation validation = new Validation();
        String flag = validation.generateFlag(testNumber);
        String convertedFlag = validation.convertFlag(flag);
        return convertedFlag + testNumber.charAt(2) + testNumber.charAt(3);
    }

    public String convertFlag(String testFlag) {
        Map<String, Integer> map = Validation.generateLetterValues();
        String first = String.valueOf(testFlag.charAt(0));
        String second = String.valueOf(testFlag.charAt(1));
        int firstInt = 0;
        int secondInt = 0;
        for (Map.Entry<String, Integer> mp : map.entrySet()) {
            if (mp.getKey().equals(first)) {
                firstInt = mp.getValue();
            }
            if (mp.getKey().equals(second)) {
                secondInt = mp.getValue();
            }
        }
        return String.valueOf(firstInt) + secondInt;
    }

    public static boolean isValidCountry(String testFlag) {
        for (Country country : countriesData) {
            if (country.flag.equals(testFlag)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidLengthToFlag(int testLength, String testFlag) {
        for (Country country : countriesData) {
            if (country.flag.equals(testFlag) && country.length == testLength) {
                return true;
            }
        }
        return false;
    }
}


