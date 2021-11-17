import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Staff extends Person{
    private String staffID;
    private int startyr;
    private int salary;

    public Staff(String fname, String lname, String dob, int startyr, int salary) throws IOException {
        super(fname, lname, dob);
        this.startyr = startyr;
        this.salary = salary;

        //Section for generating a staffID
        boolean looping = true;
        String filepath = "codes/staffcodes.csv";
        String genNumber = "";
        while (looping) {
            int yearstarted = this.startyr;
            int randomnumber = quickMethods.randnum(100, 999); //Generates a random number using my class randnum and its class method generate
            String yearAsString = String.valueOf(yearstarted); //Turns yearstarted into String
            String numAsString = String.valueOf(randomnumber); //Turns randumnumber into string
            genNumber = numAsString + yearAsString; //Combines to two
            boolean doesExist = quickMethods.checkIfInFile(filepath, genNumber);
            if (!doesExist) {
                looping = false;
            }
        }
        quickMethods.addStringToCSV(filepath, genNumber);
        this.staffID = genNumber;
    }


    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public int getStartyr() {
        return startyr;
    }

    public void setStartyr(int startyr) {
        this.startyr = startyr;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
