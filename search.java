import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;

public class search {
   public static String code(String targetcode) throws IOException{
    String objectType = "none";
    String[] objectTypesArray = {"Students","Modules", "Results","Researches", "Tutors", "Academics"};
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
