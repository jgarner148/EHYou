import java.io.IOException;

public class Result {
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
