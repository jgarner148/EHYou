import java.io.*;
import java.time.YearMonth;

public class Result implements Serializable {
    private final String resultCode;
    private String AssModule;
    private String WeekAss;
    private int Grade;
    private String Feedback;
    private final String assStudent;

    //Constructor
    public Result(String assModule, String weekAss, int grade, String feedback, String assStudent) throws IOException, ClassNotFoundException {
        this.AssModule = assModule;
        this.WeekAss = weekAss;
        this.Grade = grade;
        this.Feedback = feedback;
        this.assStudent = assStudent;
        Module resultModule = getobject.module(this.AssModule);
        resultModule.addToTotalMarks(this.Grade);

        //section for generating resultcode
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

        this.resultCode = genCode;

        String filename = "Results/" +this.getResultCode() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();

        quickMethods.addStringToCSV(filepath, genCode);

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
    }

    //Getters and Setters

    public String getAssModule() {
        return AssModule;
    }

    public void setAssModule(String assModule) throws IOException {
        AssModule = assModule;
        this.updateClassFile();
    }

    public String getWeekAss() {
        return WeekAss;
    }

    public void setWeekAss(String weekAss) throws IOException {
        WeekAss = weekAss;
        this.updateClassFile();
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) throws IOException {
        this.Grade = grade;
        this.updateClassFile();
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) throws IOException {
        this.Feedback = feedback;
        this.updateClassFile();
    }

    public String getResultCode() {
        return resultCode;
    }

    public void updateClassFile() throws IOException {
        String filename = "Results/" +this.getResultCode() + ".txt";
        File oldFile = new File(filename);
        oldFile.delete();

        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
}
