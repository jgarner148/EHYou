import java.io.*;

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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) throws IOException {
        this.fname = fname;
        this.updateClassFileStaff();
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) throws IOException {
        this.lname = lname;
        this.updateClassFileStaff();
    }

    public String getDob() {
        return dob;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) throws IOException {
        this.staffID = staffID;
        this.updateClassFileStaff();
    }

    public int getStartyr() {
        return startyr;
    }

    public void setStartyr(int startyr) throws IOException {
        this.startyr = startyr;
        this.updateClassFileStaff();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) throws IOException {
        this.salary = salary;
        this.updateClassFileStaff();
    }

    public void updateClassFileStaff() throws IOException {
        String filename = "";
        String classType = this.getClass().getSimpleName();
        if(classType == "Tutor"){
            filename = "Tutors/" +this.getStaffID() + ".txt";
        }
        if(classType == "Academic"){
            filename = "Academics/" +this.getStaffID() + ".txt";
        }

        File oldFile = new File(filename);
        oldFile.delete();

        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }

}
