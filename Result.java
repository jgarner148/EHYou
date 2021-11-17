import java.io.IOException;
import java.time.YearMonth;

public class Result {
    private String resultCode;
    private String AssModule;
    private String WeekAss;
    private int Grade;
    private String Feedback;

    //Constructor

    public Result(String assModule, String weekAss, int grade, String feedback) throws IOException, ClassNotFoundException {
        AssModule = assModule;
        WeekAss = weekAss;
        Grade = grade;
        Feedback = feedback;
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
}
