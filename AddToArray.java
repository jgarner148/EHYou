/**
 * Methods for adding new items to preexisting arrays
 */
public class AddToArray {
    /**
     * Method to add a new int to a preexisting Int Array
     *
     * @param orignalarray The array that you want to add the new int to
     * @param newitem The int you want to add to the array
     * @return A new array with the newly added int
     */
    public static int[] integer(int[] orignalarray, int newitem){
        int length = orignalarray.length; //Gets the length of the original array
        int[] newarray = new int[length + 1]; //Makes a new array that is one item bigger

        //sets all the old array items into the new array
        System.arraycopy(orignalarray, 0, newarray, 0, length);

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }

    /**
     * Method to add new String to a preexisting string array
     * @param orignalarray The array that you want to add the new String to
     * @param newitem The String you want to add to the array
     * @return A new array with a newly added String
     */
    public static String[] string(String[] orignalarray, String newitem){
        int length = orignalarray.length; //Gets the length of the original array
        String[] newarray = new String[length + 1]; //Makes a new array that is one item bigger

        //sets all the old array items into the new array
        System.arraycopy(orignalarray, 0, newarray, 0, length);

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
}
