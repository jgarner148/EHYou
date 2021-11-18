import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.YearMonth;

public class Result implements Serializable {
    private String resultCode;
    private String AssModule;
    private String WeekAss;
    private int Grade;
    private String Feedback;
    private String assStudent;

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

        quickMethods.addStringToCSV(filepath, genCode);
        this.resultCode = genCode;

        String filename = "Results/" +this.getResultCode() + ".txt";
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();

        Student Addingto = getobject.student(this.assStudent);
        String[] fetchedResults = Addingto.getAllResults();
        boolean doesexist = false;
        for(int l=0; l<fetchedResults.length;l++){
            if(fetchedResults[l].equals(this.resultCode)){
                doesexist=true;
            }
        }
        if(!doesexist) {
            Addingto.addToAllResults(this.resultCode);
        }
    }

    //Getters and Setters

    public String getAssModule() {
        return AssModule;
    }

    public void setAssModule(String assModule) {
        AssModule = assModule;
    }

    public String getWeekAss() {
        return WeekAss;
    }

    public void setWeekAss(String weekAss) {
        WeekAss = weekAss;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
