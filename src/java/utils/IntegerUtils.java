package utils;

import java.util.Scanner;

public class IntegerUtils {

    //take input from user about length of the array
    public static int inputNumberOfArray() {
        System.out.println("Input length of array");
        int numberOfArray = 0; // create a variable for number of the array
        Scanner sc = new Scanner(System.in); // Create scan object that take user input
        boolean isPositiveNumber = false; // create to loop for try-catch number of the array
        while (isPositiveNumber == false) {
            System.out.print("Enter number: ");
            try {
                numberOfArray = Integer.parseInt(sc.nextLine());
                if (numberOfArray <= 0)
                    throw new Exception(); // if the number of the array <= 0 then throw an Exception
                isPositiveNumber = true; // if run complete all the above then make isPositiveNumber true to break the loop
            } catch (Exception e) {
                System.out.println("Please Enter Positive Integer");// display this when user enter invalid value
            }
        }
        return numberOfArray;
    }

    // take user input about number that user want to find
    public static int inputNumber() {
        int searchValue = 0; // create a variable for the number need to search
        boolean isNumber = false; // create to loop for try-catch number need to search
        Scanner sc = new Scanner(System.in); // Create scan object that take user input
        while (isNumber == false) {
            try {
                searchValue = Integer.parseInt(sc.nextLine());
                isNumber = true; // if run complete all the above then make isPositiveNumber true to break the loop
            } catch (Exception e) {
                System.out.println("Please Enter An Integer"); // display this when user enter invalid value
            }
        }
        return searchValue;
    }

    // take user input about option that user want to choose
    public static int getOption() {
        boolean isValid = false; // check if that the user input is valid or not
        Scanner sc = new Scanner(System.in);    // scan object
        int option = 0; // initial a variable to store user input
        while (isValid == false) {
            try {
                System.out.print("Please choice one option: ");
                option = Integer.parseInt(sc.nextLine());
                if (option < 1 || option > 6)
                    throw new Exception(); // if user enter not in range option then throw Exception
                isValid = true; // if none above have a problem then change = true to break the loop
            } catch (Exception e) {
                System.err.println("Please enter an valid option");
            }
        }
        return option;
    }

    // if the first time input, user don't give any data then force them to
    public static int firstOption(boolean firstTimeInput) {
        int option = 0; // store the option from the user
        if (firstTimeInput == false) option = IntegerUtils.getOption(); // get the option from the user then store it
        else {
            while (firstTimeInput == true) { // if this is the first time
                option = IntegerUtils.getOption(); // get user input
                if (option == 2 || option == 3) { // if user don't give any data then force them to do it
                    System.out.println("Please enter an array first");
                } else firstTimeInput = false; // after the first time then the second don't need to
            }
        }
        return option;
    }
}
