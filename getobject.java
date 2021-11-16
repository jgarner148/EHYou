import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class getobject {


    public static Module module(String moduleID) throws IOException, ClassNotFoundException {
        String filename = "Modules/"+moduleID+".txt";
        FileInputStream fi = new FileInputStream(new File(filename));
        ObjectInputStream oi = new ObjectInputStream(fi);

        Module foundModule = (Module) oi.readObject();

        oi.close();
        fi.close();
        return foundModule;
    }

    public static Student student(String studentID) throws IOException, ClassNotFoundException {
        String filename = "Students/"+studentID+".txt";
        FileInputStream fi = new FileInputStream(new File(filename));
        ObjectInputStream oi = new ObjectInputStream(fi);

        Student foundStudent = (Student) oi.readObject();

        oi.close();
        fi.close();
        return foundStudent;
    }

    public static Tutor tutor(String tutorID) throws IOException, ClassNotFoundException {
        String filename = "Tutors/"+tutorID+".txt";
        FileInputStream fi = new FileInputStream(new File(filename));
        ObjectInputStream oi = new ObjectInputStream(fi);

        Tutor foundTutor = (Tutor) oi.readObject();

        oi.close();
        fi.close();
        return foundTutor;
    }

    public static Result result(String resultID) throws IOException, ClassNotFoundException {
        String filename = "Results/"+resultID+".txt";
        FileInputStream fi = new FileInputStream(new File(filename));
        ObjectInputStream oi = new ObjectInputStream(fi);

        Result foundResult = (Result) oi.readObject();

        oi.close();
        fi.close();
        return foundResult;
    }

}
