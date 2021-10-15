
public class Student extends Person {
	private Module[] ModulesTaking;
	private int StartYr;
	private int EndYr;
	private int StudentNum;
	
	
	//Constructor
	public Student(String fname, String lname, String dob, Module[] ModulesTaking, int startYr, int endYr, int studentNum) {
		super(fname, lname, dob);
		this.ModulesTaking = ModulesTaking;
		this.StartYr = startYr;
		this.EndYr = endYr;
		this.StudentNum = studentNum;
	}

	public Module[] getModulestaking() {
		return ModulesTaking;
	}


	public void setModulestaking(Module newmodule) {
		int length = this.ModulesTaking.length; //Gets the length of the original array
		Module[] newmodulearray = new Module[length+1]; //Makes a new array that is one item bigger
		
		for(int i = 0; i < length; i++) { //sets all the old array items into the new array
			newmodulearray[i] = this.ModulesTaking[i];
		}
		
		newmodulearray[length+1] = newmodule; //sets the new module to its place in the new array
		
		this.ModulesTaking = newmodulearray; //overwrites the old array with the new one
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
}
