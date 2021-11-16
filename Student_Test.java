import java.io.IOException;

public class Student_Test extends Person {
    private String[] modules;

    public Student_Test(String fname, String lname, String dob, String[] modules) throws IOException {
        super(fname, lname, dob);
        String filepath = "Students/allstudents.csv";
        //quickMethods.addStringToCSV(filepath, this.getFname());
        //System.out.println(this.getFname());
    }
}
