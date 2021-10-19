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
}