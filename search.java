/**
 * Class housing the method used to search for an object
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class search {
    /**
     * Method to search for an object using the objects unique code
     * @param targetcode The code of the object to be searched for
     * @return the object type as a String or none if it does not exist
     * @throws IOException IO exception
     */
    public static String code(String targetcode) throws IOException{
        String objectType = "none";
         //An array housing all the differnet types of objects
        String[] objectTypesArray = {"Students","Modules", "Results","Researches", "Tutors", "Academics"};

        //Checking if the file exists for each object type in the objectTypes array
        for(int i=0; i < objectTypesArray.length; i++){
            String currentObjectType = objectTypesArray[i];
            if(objectType.equals("none")){
                try{
                    String filename = currentObjectType+"/"+targetcode+".txt";
                    FileInputStream fi = new FileInputStream(filename);
                    ObjectInputStream oi = new ObjectInputStream(fi);

                    oi.close();
                    fi.close();
                    objectType = currentObjectType;
                }
                catch (FileNotFoundException ex){
                    objectType = "none";
                }
            }
        }
        return objectType;
    }

    /**
     * Method to search for a student object using their full name
     * @param fullName The full name of the student being searched for
     * @return Either the student code of the student if they do exist or an empty string if they don't
     * @throws IOException IO exception
     * @throws ClassNotFoundException Class not found exception
     */
    public static String studentName(String fullName) throws IOException, ClassNotFoundException {
        String filePath = "codes/studentNumbers.csv";
        Scanner checker = new Scanner(new File(filePath));
        checker.useDelimiter(",");
        String IDnumber = "";
        //Checks if the full name inputted matches any of the names of currently existing students
        while (checker.hasNext()) {
            Student currentChecking = getobject.student(checker.next());
            String currentName = currentChecking.getFname() + " " + currentChecking.getLname();
            if(currentName.equals(fullName)){
                IDnumber = currentChecking.getStudentNum();
            }
            currentChecking = null;
        }
        return IDnumber;
    }

}
