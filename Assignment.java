
public class Assignment{
	private Module modulefrom;
	private int issueweek;
	private int grade;
	private String feedback;
	
	//constructor
	public Assignment(Module modulefrom, int issueweek, int grade,
			String feedback) {
		this.modulefrom = modulefrom;
		this.issueweek = issueweek;
		this.grade = grade;
		this.feedback = feedback;
	}
	

	//Getter for the moddule Module object
	public Module getModulefrom() {
		return modulefrom;
	}

	//Setter for the module from Module object
	public void setModulefrom(Module modulefrom) {
		this.modulefrom = modulefrom;
	}


	//Getter for the issue week variable
	public int getIssueweek() {
		return issueweek;
	}
	
	//Setter for the Issue week variable
	public void setIssueweek(int issueweek) {
		this.issueweek = issueweek;
	}

	//Getter for the grade variable
	public int getGrade() {
		return grade;
	}

	//Stter for the grade variable
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	//Getter for the feedback variable
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
	
}
