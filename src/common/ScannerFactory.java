/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class ScannerFactory {

    private Scanner SCANNER;

    public Scanner getScanner() {
        if (SCANNER == null) {
            SCANNER = new Scanner(System.in);
        }
        return SCANNER;
    }

    public int getInt() {
        String input;
        while (true) {
            input = getScanner().nextLine();
            if (input.matches("^[\\d]+")) {
                break;
            }
        }
        return Integer.parseInt(input);
    }

    public String getYN() {
        String input;
        while (true) {
            input = getScanner().nextLine();
            if (input.matches("^[YyNn]")) {
                break;
            }
        }
        return input;
    }

    public String getString(String title) {
        String pattern = "[a-zA-Z0-9\\s]+";
        String input;
        while (true) {
            System.out.println(title);
            input = getScanner().nextLine();
            if (input.matches(pattern)) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        return input;
    }

    //-----------------------------------------------------------
//                     VALID Phone Number
//-----------------------------------------------------------   
    public String getPhone(String title) {
        String input;
        while (true) {
            System.out.println(title);
            input = getScanner().nextLine();
            if (input.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|(?:\\d{3}-){2}\\d{4} [a-z]+\\d{4}|\\(?(\\d{3}-)\\)?(?:\\d{3}-){1}\\d{4}|(?:\\d{3}.){2}\\d{4}|(?:\\d{3} ){2}\\d{4}")) {
                break;
            } else {
                System.out.println("Please input Phone flow");
                System.out.println("""
                                   1234567890
                                   123-456-7890 
                                   123-456-7890 x1234
                                   123-456-7890 ext1234
                                   (123)-456-7890
                                   123.456.7890
                                   123 456 7890""");
            }
        }
        return input;
    }

//-----------------------------------------------------------
//                     VALID Email
//-----------------------------------------------------------
    public String getEmail(String title) {
        final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        String input;
        while (true) {
            System.out.println(title);
            input = getScanner().nextLine();
            Matcher matcher = emailPattern.matcher(input);
            if (matcher.matches()) {
                break;
            }
        }
        return input;
    }

    //-----------------------------------------------------------
//                     VALID DATE
//-----------------------------------------------------------
    public Date getDate() throws ParseException {
        String input;
        while (true) {
            System.out.println("Enter Date");
            input = getScanner().nextLine();
            if (validDate(input)) {
                break;
            } else {
                System.out.println("Invalid Date input");
            }
        }
        return parseDate(input);
    }
//-----------------------------------------------------------

    public Date parseYear(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy");
        return df.parse(date);
    }
//-----------------------------------------------------------

    public Date parseDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.parse(date);
    }
//-----------------------------------------------------------

    public boolean validDate(String dob) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            Date dateFormat = df.parse(dob);
            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);
            calendar.setTime(dateFormat);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            if (year > 2023) {
                return false;
            }
            if (month == 2) {
                return validFebrary(day, year);
            } else {
                return validMonth(day, month);
            }
        } catch (ParseException e) {
            return false;
        }
    }
//-----------------------------------------------------------

    public boolean validMonth(int day, int month) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 ->
                day >= 1 && day <= 31;
            case 4, 6, 9, 11 ->
                day >= 1 && day <= 30;
            default ->
                false;
        };
    }
//-----------------------------------------------------------

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 == 0) || (year % 400 == 0);
    }
//-----------------------------------------------------------

    public boolean validFebrary(int day, int year) {
        if (isLeapYear(year)) {
            return day >= 1 && day <= 29;
        } else {
            return day >= 1 && day <= 28;
        }
    }

}
