/**
 * Class housing the method used to search for an object
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;

public class search {
    /**
     * Method to search for an object using the objects unique code
     * @param targetcode The code of the object to be searched for
     * @return the object type as a String or none if it does not exist
     * @throws IOException
     */
    public static String code(String targetcode) throws IOException{
        String objectType = "none";
        /**
         * An array hosuing all the differnet types of objects
         */
        String[] objectTypesArray = {"Students","Modules", "Results","Researches", "Tutors", "Academics"};

        //Checking if the file exists for each object type in the objectTypes array
        for(int i=0; i < objectTypesArray.length; i++){
            String currentObjectType = objectTypesArray[i];
            if(objectType.equals("none")){
                try{
                    String filename = currentObjectType+"/"+targetcode+".txt";
                    FileInputStream fi = new FileInputStream(new File(filename));
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
}
