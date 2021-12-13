import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * Class housing methods aimed to shorten tasks with long and/or complex methods that are usually done multiple times
 */
public class quickMethods {
    /**
     * Method to generate a random number between two values
     * @param min Minimum value of random number
     * @param max Maximum value of random number
     * @return Generated random number
     */
    public static int randnum(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    /**
     * Method to check if a String is in an inputted CSV file
     * @param filePath Location of the file to be checked
     * @param itemChecking Item that is being looked for
     * @return Boolean that is "true" if the item exists in the file and "false" if the item does not
     * @throws FileNotFoundException File not found exception
     */
    public static boolean checkIfInFile(String filePath, String itemChecking) throws FileNotFoundException {
        Scanner checker = new Scanner(new File(filePath));
        checker.useDelimiter(",");
        int exists = 0;
        boolean reutrnValue = true;
        //checks if item exists in file
        while (checker.hasNext()) {
            if (checker.next().equals(itemChecking)) {
                exists++;
            }
        }
        //Returns false if it does not exist
        if (exists == 0) {
            reutrnValue = false;
        }
        checker.close();
        return reutrnValue; //returns true if it does exist
    }

    /**
     * Method to add a string to a CSV file
     * @param filepath location of CSV file that the string is being added to
     * @param itemAdding Item being added to the CSV file
     * @throws IOException IO Exception
     */
    public static void addStringToCSV(String filepath, String itemAdding) throws IOException {
        FileWriter csvWriter = new FileWriter(filepath, true);
        csvWriter.append(itemAdding);
        csvWriter.append(",");
        csvWriter.flush();
        csvWriter.close();
    }

    /**
     *Method for generating a random Char
     * @return random Char generated
     */
    public static char randchar() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }

    /**
     * Method for checking an inputted date of birth is valid in the format DD/MM/YYYY
     * @param DOBChecking Date of birth that needs to be checked
     * @return boolean that is "true" is the DOB is valid and "false" if the DOB is not valid
     */
    public static boolean checkDOB(String DOBChecking) {
        //Checking the length of the DOB
        boolean returnResult = DOBChecking.length() == 10;
        StringBuilder day = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder year = new StringBuilder();
        boolean slashcorrect1 = false;
        boolean slashcorrect2 = false;
        try {
            if (returnResult) {
                for (int i = 0; i < 11; i++) {
                    //adding numbers to day
                    if (i == 0 || i == 1) {
                        day.append(DOBChecking.charAt(i));
                    }
                    //Checking slash is in correct place
                    if (i == 2) {
                        int comparison = Character.compare(DOBChecking.charAt(i), '/');
                        if (comparison == 0) {
                            slashcorrect1 = true;
                        }
                    }
                    //adding numbers to month
                    if (i == 3 || i == 4) {
                        month.append(DOBChecking.charAt(i));
                    }
                    //checking slash is in correct place
                    if (i == 5) {
                        int comparison = Character.compare(DOBChecking.charAt(i), '/');
                        if (comparison == 0) {
                            slashcorrect2 = true;
                        }
                    }
                    //Adding numbers to year
                    if (i == 6 || i == 7 || i == 8 || i == 9) {
                        year.append(DOBChecking.charAt(i));
                    }
                }

                int dayAsNum = Integer.parseInt(day.toString());
                int monthAsNum = Integer.parseInt(month.toString());
                int yearAsNum = Integer.parseInt(year.toString());

                //Checking valid 31 day months
                if (!slashcorrect1 || !slashcorrect2 || dayAsNum > 31 || monthAsNum > 13 || yearAsNum < 1900) {
                    returnResult = false;
                }

                //Checking valid 30 day months
                if ((monthAsNum == 4 || monthAsNum == 6 || monthAsNum == 9 || monthAsNum == 11) && returnResult) {
                    if (dayAsNum > 30) {
                        returnResult = false;
                    }
                }

                //Checking valid 29 day month
                if (monthAsNum == 2 && returnResult) {
                    if (dayAsNum > 29) {
                        returnResult = false;
                    }
                }
            }
        } catch (NumberFormatException ex) {
            returnResult = false;
        }

        return returnResult;
    }

    /**
     * Method to remove String from a String array
     * @param stringRemoving String to be removed
     * @param originalArray Array string is being removed from
     * @return new array with removed String
     */
    public static String[] removeFromStringArray(String stringRemoving, String[] originalArray) {
        String[] newArray = new String[originalArray.length - 1];
        for (int i = 0, j = 0; i < originalArray.length; i++) {
            if (!originalArray[i].equals(stringRemoving)) {
                newArray[j++] = originalArray[i];
            }
        }
        return newArray;
    }

    /**
     * Method to check if an inputed item exists in an inputted string array
     * @param itemChecking String that is being checked for
     * @param arrayChecking String array that is being checked
     * @return A boolean value that is "true" if the string does exist and "false" if it does not
     */
    public static boolean checkIfInStringArray(String itemChecking, String[] arrayChecking){
       boolean isInArray = false;
       for(int i=0; i < arrayChecking.length; i++){
           if (arrayChecking[i].equals(itemChecking)) {
               isInArray = true;
               break;
           }
       }
       return isInArray;
    }
}