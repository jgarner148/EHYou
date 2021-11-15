import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class quickMethods{

    public static int randnum(int min, int max){
        return (int)(Math.random()*((max - min) + 1))+min;
    }

    public static int stringToNum(String originalString){
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
        if (exists == 0) {reutrnValue = false;}
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









}