/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import common.ScannerFactory;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DaoInputString {

    private static DaoInputString instance = null;
    ScannerFactory sc;

    public DaoInputString() {
        sc = new ScannerFactory();
    }

    public static DaoInputString Instance() {
        if (instance == null) {
            synchronized (DaoInputString.class) {
                if (instance == null) {
                    instance = new DaoInputString();
                }
            }
        }
        return instance;
    }   

    public HashMap<String, List<Integer>> getNumber(String input) {

        HashMap<String, List<Integer>> numbers = new HashMap<>();

        numbers.put("All", new ArrayList<>());
        numbers.put("Perfect", new ArrayList<>());
        numbers.put("Even", new ArrayList<>());
        numbers.put("Odd", new ArrayList<>());

        String numberString = input.replaceAll("^[^\\d]*", "").replaceAll("[^\\d]+", ",");

        int[] number = Arrays.stream(numberString.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i : number) {
            numbers.get("All").add(i);
            if (isPerfect(i)) {
                numbers.get("Perfect").add(i);
            }
            if (isEven(i)) {
                numbers.get("Even").add(i);
            } else {
                numbers.get("Odd").add(i);
            }
        }
        return numbers;
    }

    public HashMap<String, StringBuilder> getCharacter(String input) {

        HashMap<String, StringBuilder> characters = new HashMap<>();

        characters.put("All", new StringBuilder());
        characters.put("UpperCase", new StringBuilder());
        characters.put("LowerCase", new StringBuilder());
        characters.put("Specical", new StringBuilder());

        String charString = input.replaceAll("^[\\d]*", "").replaceAll("[\\d]+", "");

        for (int i = 0; i < charString.length(); i++) {

            characters.get("All").append(charString.charAt(i));

            if (Character.isUpperCase(charString.charAt(i))) {
                characters.get("UpperCase").append(charString.charAt(i));
            }
            if (Character.isLowerCase(charString.charAt(i))) {
                characters.get("LowerCase").append(charString.charAt(i));
            }
            if (!Character.isLetter(charString.charAt(i))) {
                characters.get("Specical").append(charString.charAt(i));
            }
        }
        return characters;
    }

    public void printNumber(HashMap<String, List<Integer>> listNumber) {
        for (String key : listNumber.keySet()) {
            System.out.print(key + ": " + (!listNumber.get(key).isEmpty() ? "[" : "\n"));
            for (int i = 0; i < listNumber.get(key).size(); i++) {
                System.out.print(listNumber.get(key).get(i) + (i < listNumber.get(key).size() - 1 ? ", " : "]\n"));
            }
        }
    }

    public void printCharacter(HashMap<String, StringBuilder> listChar) {
        for (String key : listChar.keySet()) {
            System.out.print(key + ": " + (!listChar.get(key).isEmpty() ? listChar.get(key) + "\n" : "\n"));
        }
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public boolean isPerfect(int number) {
        if (number > 0) {
            return number == Math.pow((int) Math.sqrt(number), 2);
        }
        return false;
    }
}
