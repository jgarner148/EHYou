public class AddToArray {
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Academic
    /**
     * Method to add a new Academic object to a preexisting Academic Array
     *
     * @param orignalarray The array that you want to add the new object to
     * @param newitem      The object you want to add to the array
     * @return The new array with the newly added object
     */

    /**
    public static Academic[] academic(Academic[] orignalarray, Academic newitem) {
        int length = orignalarray.length; //Gets the length of the original array
        Academic[] newarray = new Academic[length + 1]; //Makes a new array that is one item bigger

        for (int i = 0; i < length; i++) { //sets all the old array items into the new array
            newarray[i] = orignalarray[i];
        }

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
     **/
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Student
    /**
     * Method to add a new Student object to a preexisting Student Array
     *
     * @param orignalarray The array that you want to add the new object to
     * @param newitem      The object you want to add to the array
     * @return The new array with the newly added object
     */
    public static Student[] student(Student[] orignalarray, Student newitem) {
        int length = orignalarray.length; //Gets the length of the original array
        Student[] newarray = new Student[length + 1]; //Makes a new array that is one item bigger

        for (int i = 0; i < length; i++) { //sets all the old array items into the new array
            newarray[i] = orignalarray[i];
        }

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Research

    /**
     * Method to add a new Research object to a preexisting Research Array
     *
     * @param orignalarray The array that you want to add the new object to
     * @param newitem      The object you want to add to the array
     * @return The new array with the newly added object
     */
    public static Research[] research(Research[] orignalarray, Research newitem) {
        int length = orignalarray.length; //Gets the length of the original array
        Research[] newarray = new Research[length + 1]; //Makes a new array that is one item bigger

        for (int i = 0; i < length; i++) { //sets all the old array items into the new array
            newarray[i] = orignalarray[i];
        }

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Module
    /**
     * Method to add a new Module object to a preexisting Module Array
     *
     * @param orignalarray The array that you want to add the new object to
     * @param newitem      The object you want to add to the array
     * @return The new array with the newly added object
     */
    public static Module[] module(Module[] orignalarray, Module newitem) {
        int length = orignalarray.length; //Gets the length of the original array
        Module[] newarray = new Module[length + 1]; //Makes a new array that is one item bigger

        for (int i = 0; i < length; i++) { //sets all the old array items into the new array
            newarray[i] = orignalarray[i];
        }

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Result

    /**
     * Method to add a new Result object to a preexisting result Array
     *
     * @param orignalarray The array that you want to add the new object to
     * @param newitem      The object you want to add to the array
     * @return The new array with the newly added object
     */
    public static Result[] result(Result[] orignalarray, Result newitem) {
        int length = orignalarray.length; //Gets the length of the original array
        Result[] newarray = new Result[length + 1]; //Makes a new array that is one item bigger

        for (int i = 0; i < length; i++) { //sets all the old array items into the new array
            newarray[i] = orignalarray[i];
        }

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Int
    /**
     * Method to add a new int to a preexisting Int Array
     *
     * @param orignalarray The array that you wan to add the new in to
     * @param newitem       The int you want to add to the array
     * @return The new array with the newly added int
     */
    public static int[] integer(int[] orignalarray, int newitem){
        int length = orignalarray.length; //Gets the length of the original array
        int[] newarray = new int[length + 1]; //Makes a new array that is one item bigger

        for (int i = 0; i < length; i++) { //sets all the old array items into the new array
            newarray[i] = orignalarray[i];
        }

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //String
    public static String[] string(String[] orignalarray, String newitem){
        int length = orignalarray.length; //Gets the length of the original array
        String[] newarray = new String[length + 1]; //Makes a new array that is one item bigger

        for (int i = 0; i < length; i++) { //sets all the old array items into the new array
            newarray[i] = orignalarray[i];
        }

        newarray[length] = newitem; //sets the new item to its place in the new array

        return newarray;
    }
}
