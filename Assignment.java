
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
	

	public Module getModulefrom() {
		return modulefrom;
	}


	public void setModulefrom(Module modulefrom) {
		this.modulefrom = modulefrom;
	}


	public int getIssueweek() {
		return issueweek;
	}

	public void setIssueweek(int issueweek) {
		this.issueweek = issueweek;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
	
}
