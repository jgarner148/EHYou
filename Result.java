public class Result {
    private Module AssModule;
    private String WeekAss;
    private int Grade;
    private String Feedback;

    //Constructor

    public Result(Module assModule, String weekAss, int grade, String feedback) {
        AssModule = assModule;
        WeekAss = weekAss;
        Grade = grade;
        Feedback = feedback;
        assModule.addToTotalMarks(grade);
    }

    //Getters and Setters

    public Module getAssModule() {
        return AssModule;
    }

    public void setAssModule(Module assModule) {
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
