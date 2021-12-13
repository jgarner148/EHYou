import java.io.*;

/**
 * Class file for the Staff object
 */
public class Staff extends Person{
    /**
     * The staff ID
     */
    private final String staffID;
    /**
     * The year the staff started working at the university
     */
    private int startyr;
    /**
     * The staff's salary
     */
    private int salary;

    /**
     * Constructor for staff
     * @param fname Staff's first name
     * @param lname Staff's last name
     * @param dob Staff's Date of Birth
     * @param startyr The year the staff started working at the university
     * @param salary The staff's salary
     * @throws IOException IO Exception
     */
    public Staff(String fname, String lname, String dob, int startyr, int salary) throws IOException {
        super(fname, lname, dob);//Calling the constructor of the super classes using the taken in variables
        //Assigning the taken in variables to the appropriate class variable
        this.startyr = startyr;
        this.salary = salary;

        //Generating the Staff ID
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


        quickMethods.addStringToCSV(filepath, genNumber); //Adding the staff ID to the overall staff codes CSV file
        this.staffID = genNumber; //Assigning the generated staff
    }

    /**
     * Getter for first name
     * @return the staff's first name
     */
    public String getFname() {return fname;}

    /**
     * Setter for first name
     * @param fname the value being assigned to first name
     * @throws IOException IO Exception
     */
    public void setFname(String fname) throws IOException {
        this.fname = fname;
        this.updateClassFileStaff();
    }

    /**
     * Getter for last name
     * @return the staff's last name
     */
    public String getLname() {return this.lname;}

    /**
     * Setter for last name
     * @param lname the value being assigned to last name
     * @throws IOException IO Exception
     */
    public void setLname(String lname) throws IOException {
        this.lname = lname;
        this.updateClassFileStaff();
    }

    /**
     * Getter for DOB
     * @return the staff's date of birth
     */
    public String getDob() {return this.dob;}

    /**
     * Setter for dob
     * @param dob the value being assigned to dob
     * @throws IOException IO Exception
     */
    public void setDob(String dob) throws IOException {
        this.dob = dob;
        this.updateClassFileStaff();
    }

    /**
     * Getter for staff ID
     * @return the staff ID
     */
    public String getStaffID() {return this.staffID;}

    /**
     * getter for start year
     * @return the staff's start year
     */
    public int getStartyr() {return this.startyr;}

    /**
     * Setter for start year
     * @param startyr the value being assigned to start year
     * @throws IOException IO Exception
     */
    public void setStartyr(int startyr) throws IOException {
        this.startyr = startyr;
        this.updateClassFileStaff();
    }

    /**
     * Getter for salary
     * @return the staff's salary
     */
    public int getSalary() {return this.salary;}

    /**
     * The setter for salary
     * @param salary the value being assigned to salary
     * @throws IOException IO Exception
     */
    public void setSalary(int salary) throws IOException {
        this.salary = salary;
        this.updateClassFileStaff();
    }

    /**
     * Method to update class file be deleting the old one and creating a new one
     * @throws IOException IO Exception
     */
    public void updateClassFileStaff() throws IOException {
        String filename = "";

        String classType = this.getClass().getSimpleName();//Getting the class Type
        //Getting the file path if the class type is tutor
        if(classType.equals("Tutor")){
            filename = "Tutors/" +this.getStaffID() + ".txt";
        }
        //Getting the file path if the class type is academic
        if(classType.equals("Academic")){
            filename = "Academics/" +this.getStaffID() + ".txt";
        }

        //Deleting the old file
        File oldFile = new File(filename);
        oldFile.delete();

        //Creating the new file
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
}
