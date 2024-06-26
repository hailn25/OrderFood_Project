
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Validation {

    public static String removeUnnecessaryBlank(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    public static String removeAllBlank(String input) {
        return input.trim().replaceAll("\\s+", "");
    }

    public static String pressYNtoContinue(String mess) {
        //"Do you want to continue (Y/N): "
        String input = getStringByRegex(mess, "Y/N only!!!", "[YNyn]");
        return input;
    }

    public static String pressUDtoContinue(String mess) {
        //"Do you want to continue (Y/N): "
        String input = getStringByRegex(mess, "U/D only!!!", "[UDud]");
        return input;
    }

    public static String normalFormName(String input) {
        input = removeUnnecessaryBlank(input);
        String temp[] = input.split(" ");
        input = "";
        for (int i = 0; i < temp.length; i++) {
            input += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                input += " ";
            }
        }
        return input;
    }

    public static String getNonEmptyString(String mess) {
        String ret = "";
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print(mess);
            ret = removeUnnecessaryBlank(scan.nextLine());
            if (ret.equalsIgnoreCase("")) {
                throwError("Please input Non-Empty String!!!");
                continue;
            }
            return ret;
        }
    }

    public static String normalFormStringAfterDot(String input) {
        String output = "";
        input = removeUnnecessaryBlank(input);
        input = String.valueOf(input.charAt(0)).toUpperCase() + input.substring(1);

        input = input.replaceAll("\\.\\s+", "\\.").replaceAll("\\s+\\.", "\\.");
        output += input.charAt(0);
        for (int i = 1; i < input.length(); i++) {

            if (input.charAt(i - 1) == '.' && Character.isAlphabetic(input.charAt(i))) {
                output += " " + Character.toUpperCase(input.charAt(i));
            } else {
                output += input.charAt(i);
            }
        }
        return output;
    }

    public static int getInt(String mess, String errorNumberFormat, String errorOutOfRange, int min, int max) {
        while (true) {
            int ret = Integer.parseInt(getStringByRegex(mess, errorNumberFormat, "[0-9]+"));
            if (ret < min || ret > max) {
                throwError(errorOutOfRange);
            } else {
                return ret;
            }
        }

    }
    
    public static double getDouble(String mess, String errorNumberFormat, String errorOutOfRange, double min, double max) {
        while (true) {
            double ret = Double.parseDouble(getStringByRegex(mess, errorNumberFormat, "-?[0-9]+(\\.[0-9]+)?"));
            if (ret < min || ret > max) {
                throwError(errorOutOfRange);
            } else {
                return ret;
            }
        }

    }

    public static String getStringByRegex(String mess, String error, String regex) {
        Scanner scan = new Scanner(System.in);
        String output = null;
        while (true) {
            System.out.print(mess);
            output = scan.nextLine();
            if (output.matches(regex)) {
                return output;
            } else {
                throwError(error);
            }
        }
    }

    public static String getEmail(String mess) {

        String regex = "^[A-Za-z](.*)([@]{1})(.{2,})(\\.)(.{2,})";//phai bat dau bang chu cai
        String email = getStringByRegex(mess, "Please enter email with format <account name>@<domain>.<domain>", regex);
        return email;
    }

    public static String getPhone(int minLength, String mess) {
        String regex = "-?[0-9 ]+"; // [0-9 ]+
        while (true) {
            String phoneNum = getStringByRegex(mess, "Please enter number integer only!", regex).replaceAll("\\s+", "");
            if (phoneNum.length() < minLength) {
                throwError("Phone number must be at least 9 digit");
            } else {
                if (Double.parseDouble(phoneNum) < 0) {
                    throwError("Invalid phone");
                }else{
                    return phoneNum;
                } 
            }
        }
    }

//    public static String getDob(String mess, String error){
//        Scanner scan = new Scanner(System.in);
//        while (true) {
//            try {
//                System.out.print(mess);
//                String result = scan.nextLine();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                Date date = sdf.parse(result);
//                sdf.setLenient(false);
//                Date now = new Date();
//                if (date.after(now)) {
//                    throwError("Date of birth must smaller than today");
//                }else{
//                    return result;
//                }
//            } catch (ParseException ex) {
//                throwError("Invalid!");
//            } catch (NumberFormatException e) {
//                throwError("Invalid format!");
//            }
//        }
//    }

    public static String getDob(String mess, String error) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(mess);
                String result = scan.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(result);
                Date now = new Date();
                if (date.after(now)) {
                    System.err.println("Date of birth must smaller than today!");
                } else {
                    if (result.equalsIgnoreCase(sdf.format(date))) {
                        return result;
                    } else {
                        System.err.println("Invalid date!");
                    }
                }
            } catch (ParseException ex) {
                System.err.println(error);
            } catch (NumberFormatException e) {
                System.err.println("Invalid!");
            }
        }
    }
    
    public static String getSex(String mess) {
        String ret = "";
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print(mess);
            ret = removeUnnecessaryBlank(scan.nextLine());
            if (!ret.equalsIgnoreCase("male") && !ret.equalsIgnoreCase("female")) {
                throwError("Please input sex 'male' or 'female'!!!");
                continue;
            }
            return ret;
        }
    }

    public static void throwError(String message) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }
}