
public class Student extends Person {
	private Module[] ModulesTaking;
	private Result[] AllResults;
	private int StartYr;
	private int EndYr;
	private int StudentNum;
	
	
	//Constructor
	public Student(String fname, String lname, String dob, Module[] modulesTaking, Result[] allResults, int startYr, int endYr, int studentNum) {
		super(fname, lname, dob);
		this.ModulesTaking = modulesTaking;
		this.AllResults = allResults;
		this.StartYr = startYr;
		this.EndYr = endYr;
		this.StudentNum = studentNum;
	}

	public Module[] getModulesTaking() {
		return ModulesTaking;
	}


	public void addToModulesTaking(Module newmodule) {
		this.ModulesTaking = AddToArray.module(this.ModulesTaking, newmodule);
	}


	public int getStartYr() {
		return StartYr;
	}

	public void setStartYr(int startYr) {
		StartYr = startYr;
	}

	public int getEndYr() {
		return EndYr;
	}

	public void setEndYr(int endYr) {
		EndYr = endYr;
	}

	public int getStudentNum() {
		return StudentNum;
	}

	public void setStudentNum(int studentNum) {
		StudentNum = studentNum;
	}

	public Result[] getAllResults() {
		return AllResults;
	}

	public void addToAllResults(Result newresult) {
		this.AllResults = AddToArray.result(this.AllResults, newresult);
	}
}
