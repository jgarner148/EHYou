import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class file for the get object methods which create a previously created object type using its ID number
 */
public class getobject {
    /**
     * Method for creating a module object from its ID
     * @param moduleID The ID of the module object being made
     * @return The newly made module object
     * @throws IOException IO Exception
     * @throws ClassNotFoundException Class not found exception
     */
    public static Module module(String moduleID) throws IOException, ClassNotFoundException {
        //Finding the file for the module in the file system
        String filename = "Modules/"+moduleID+".txt";
        FileInputStream fi = new FileInputStream(filename);
        ObjectInputStream oi = new ObjectInputStream(fi);

        Module foundModule = (Module) oi.readObject();//Creating the module using the found file

        oi.close();
        fi.close();
        return foundModule;
    }
    /**
     * Method for creating a student object from its ID
     * @param studentID The ID of the student being made
     * @return The newly made student object
     * @throws IOException IO Exception
     * @throws ClassNotFoundException Class not found exception
     */
    public static Student student(String studentID) throws IOException, ClassNotFoundException {
        //Finding the file for the student in the file system
        String filename = "Students/"+studentID+".txt";
        FileInputStream fi = new FileInputStream(filename);
        ObjectInputStream oi = new ObjectInputStream(fi);

        Student foundStudent = (Student) oi.readObject();//Creating the student using the found file

        oi.close();
        fi.close();
        return foundStudent;
    }

    /**
     * Method for creating a tutor object from its ID
     * @param staffID The ID of the tutor being made
     * @return The newly made student object
     * @throws IOException IO Exception
     * @throws ClassNotFoundException Class not found exception
     */
    public static Tutor tutor(String staffID) throws IOException, ClassNotFoundException {
        //Finding the file for the tutor in the file system
        String filename = "Tutors/"+staffID+".txt";
        FileInputStream fi = new FileInputStream(filename);
        ObjectInputStream oi = new ObjectInputStream(fi);

        Tutor foundTutor = (Tutor) oi.readObject();//Creating the tutor object

        oi.close();
        fi.close();
        return foundTutor;
    }
    /**
     * Method for creating an academic object from its ID
     * @param staffID The ID of the academic being made
     * @return The newly made academic object
     * @throws IOException IO Exception
     * @throws ClassNotFoundException Class not found exception
     */
    public static Academic academic(String staffID) throws IOException, ClassNotFoundException {
        //Finding hte file for the academic in the file system
        String filename = "Academics/"+staffID+".txt";
        FileInputStream fi = new FileInputStream(filename);
        ObjectInputStream oi = new ObjectInputStream(fi);

        Academic foundAcademic = (Academic) oi.readObject();//Creating the academic object

        oi.close();
        fi.close();
        return foundAcademic;
    }
    /**
     * Method for creating a result object from its ID
     * @param resultID The ID of the result being made
     * @return The newly made result object
     * @throws IOException IO Exception
     * @throws ClassNotFoundException Class not found exception
     */
    public static Result result(String resultID) throws IOException, ClassNotFoundException {
        //Finding the file for the result in the file system
        String filename = "Results/"+resultID+".txt";
        FileInputStream fi = new FileInputStream(filename);
        ObjectInputStream oi = new ObjectInputStream(fi);

        Result foundResult = (Result) oi.readObject();//Creating the result object

        oi.close();
        fi.close();
        return foundResult;
    }

    /**
     * Method for creating a research object from its ID
     * @param researchIO The ID for the research being made
     * @return The newly made research object
     * @throws IOException IO Exception
     * @throws ClassNotFoundException Class not found exception
     */
    public static Research research(String researchIO) throws IOException, ClassNotFoundException {
        //Finding the file for the research in the file system
        String filename = "Researches/"+researchIO+".txt";
        FileInputStream fi = new FileInputStream(filename);
        ObjectInputStream oi = new ObjectInputStream(fi);

        Research foundResult = (Research) oi.readObject(); //Creating the research object

        oi.close();
        fi.close();
        return foundResult;
    }

}
