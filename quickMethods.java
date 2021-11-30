import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class quickMethods {

    public static int randnum(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public static int stringToNum(String originalString) {
        int stringAsNum = 0;
        /**
         * Try catch loop code supplied from
         * https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
         * I have added my own variable names and removed the print statement
         */
        try {
            stringAsNum = Integer.parseInt(originalString);  //Converts String into an int
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return stringAsNum;
    }

    public static boolean checkIfInFile(String filePath, String itemChecking) throws FileNotFoundException {
        Scanner checker = new Scanner(new File(filePath));
        checker.useDelimiter(",");
        int exists = 0;
        boolean reutrnValue = true;
        while (checker.hasNext()) {
            if (checker.next().equals(itemChecking)) {
                exists++;
            }
        }
        if (exists == 0) {
            reutrnValue = false;
        }
        checker.close();
        return reutrnValue;
    }

    public static void addStringToCSV(String filepath, String itemAdding) throws IOException {
        FileWriter csvWriter = new FileWriter(filepath, true);
        csvWriter.append(itemAdding);
        csvWriter.append(",");
        csvWriter.flush();
        csvWriter.close();
    }

    public static char randchar() {
        Random random = new Random();

        char randomchar = (char) (random.nextInt(26) + 'a');

        return randomchar;
    }

    public static boolean checkDOB(String DOBChecking) {
        boolean returnResult = DOBChecking.length() == 10;
        StringBuilder day = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder year = new StringBuilder();
        boolean slashcorrect1 = false;
        boolean slashcorrect2 = false;
        try {
            if (returnResult) {
                for (int i = 0; i < 11; i++) {
                    if (i == 0 || i == 1) {
                        day.append(DOBChecking.charAt(i));
                    }
                    if (i == 2) {
                        int comparison = Character.compare(DOBChecking.charAt(i), '/');
                        if (comparison == 0) {
                            slashcorrect1 = true;
                        }
                    }
                    if (i == 3 || i == 4) {
                        month.append(DOBChecking.charAt(i));
                    }
                    if (i == 5) {
                        int comparison = Character.compare(DOBChecking.charAt(i), '/');
                        if (comparison == 0) {
                            slashcorrect2 = true;
                        }
                    }
                    if (i == 6 || i == 7 || i == 8 || i == 9) {
                        year.append(DOBChecking.charAt(i));
                    }
                }

                int dayAsNum = Integer.parseInt(day.toString());
                int monthAsNum = Integer.parseInt(month.toString());
                int yearAsNum = Integer.parseInt(year.toString());

                if (!slashcorrect1 || !slashcorrect2 || dayAsNum > 31 || monthAsNum > 13 || yearAsNum < 1900) {
                    returnResult = false;
                }

                if ((monthAsNum == 4 || monthAsNum == 6 || monthAsNum == 9 || monthAsNum == 11) && returnResult) {
                    if (dayAsNum > 30) {
                        returnResult = false;
                    }
                }

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

    public static String[] removeFromStringArray(String stringRemoving, String[] originalArray) {
        String[] newArray = new String[originalArray.length - 1];
        for (int i = 0, j = 0; i < originalArray.length; i++) {
            if (!originalArray[i].equals(stringRemoving)) {
                newArray[j++] = originalArray[i];
            }
        }
        return newArray;
    }
}