/**
 * Class file for the Result object
 */

import java.io.*;
import java.time.YearMonth;

public class Result implements Serializable {
    /**
     * Result code
     */
    private final String resultCode;
    /**
     * The module the result is assigned to
     */
    private String AssModule;
    /**
     * The week the result was created
     */
    private String WeekAss;
    /**
     * The grade the student achieved
     */
    private int Grade;
    /**
     * The feedback, usually short
     */
    private String Feedback;
    /**
     * The student the result is assigned to
     */
    private String assStudent;

    /**
     * Constructor for Result
     * @param assModule The module the result is assigned to
     * @param weekAss The week the result was created
     * @param grade The grade the student achieved
     * @param feedback The feedback, usually short
     * @param assStudent The student the result is assigned to
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Result(String assModule, String weekAss, int grade, String feedback, String assStudent) throws IOException, ClassNotFoundException {
        //Assigning the taken in variables to the appropriate class variable
        this.AssModule = assModule;
        this.WeekAss = weekAss;
        this.Grade = grade;
        this.Feedback = feedback;
        this.assStudent = assStudent;


        //Generating hte result code
        boolean looping = true;
        String filepath = "codes/resultcodes.csv";
        String genCode = "";
        while(looping){
            int currentyear = YearMonth.now().getYear();
            String yearAsString = String.valueOf(currentyear);
            int randomnumber = quickMethods.randnum(10000, 99999);
            for(int i=0; i < 6; i++){
                char randomchar = quickMethods.randchar();
                String charAsString = Character.toString(randomchar);
                genCode = genCode + charAsString;
            }
            genCode = genCode+randomnumber+yearAsString;
            boolean doesExist = quickMethods.checkIfInFile(filepath, genCode);
            if (!doesExist) {
				looping = false;
			}
        }

        this.resultCode = genCode; //assigning the generated result code to the result code variable


       //Adding the result code to the assigned student
        boolean isStudentzero = this.assStudent.length() == 0;
        if(!isStudentzero) {
            Student Addingto = getobject.student(this.assStudent);
            String[] fetchedResults = Addingto.getAllResults();
            boolean doesexist = false;
            for (int l = 0; l < fetchedResults.length; l++) {
                if (fetchedResults[l].equals(this.resultCode)) {
                    doesexist = true;
                }
            }
            if (!doesexist) {
                Addingto.addToAllResults(this.resultCode);
                Addingto.updateClassFile();
            }
        }

        //Adding the grade to the assigned module
        Module resultModule = getobject.module(this.AssModule);
        resultModule.addToTotalMarks(this.Grade);

        //Creating the class file for the newly created result object
        String filename = "Results/" +this.getResultCode() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();

        quickMethods.addStringToCSV(filepath, genCode); //Adding the generated result code to the overall result code csv file


    }

    /**
     * Getter for assigned module
     * @return the assigned module
     */
    public String getAssModule() {return this.AssModule;}

    /**
     * Setter for the assigned module
     * @param assModule the value being assigned to assigned module
     * @throws IOException
     */
    public void setAssModule(String assModule) throws IOException {
        AssModule = assModule;
        this.updateClassFile();
    }

    /**
     * Getter for assigned week
     * @return the assigned week
     */
    public String getWeekAss() {return this.WeekAss;}

    /**
     * Setter for assigned week
     * @param weekAss the value being assigned to assigned week
     * @throws IOException
     */
    public void setWeekAss(String weekAss) throws IOException {
        WeekAss = weekAss;
        this.updateClassFile();
    }

    /**
     * Getter for grade
     * @return the Grade
     */
    public int getGrade() {return this.Grade;}

    /**
     * Setter for grade
     * @param grade the value being assigned to grade
     * @throws IOException
     */
    public void setGrade(int grade) throws IOException {
        this.Grade = grade;
        this.updateClassFile();
    }

    /**
     * Getter for feedback
     * @return the feedback
     */
    public String getFeedback() {return this.Feedback;}

    /**
     * Setter for feedback
     * @param feedback
     * @throws IOException
     */
    public void setFeedback(String feedback) throws IOException {
        this.Feedback = feedback;
        this.updateClassFile();
    }

    /**
     * Getter for result code
     * @return the Result's result code
     */
    public String getResultCode() {return this.resultCode;}

    /**
     * Getter for assigned student
     * @return the assigned student
     */
    public String getAssStudent() {return this.assStudent;}

    /**
     * Setter for assigned student
     * @param assStudent the value being assigned to assigned student
     */
    public void setAssStudent(String assStudent) throws IOException {
        this.assStudent = assStudent;
        this.updateClassFile();
    }

    /**
     * Method to update class file be deleting the old one and creating a new one
     * @throws IOException
     */
    public void updateClassFile() throws IOException {
        String filename = "Results/" +this.getResultCode() + ".txt";

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
