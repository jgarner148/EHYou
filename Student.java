
public class Student extends Person {
	private Module[] modulestaking;
	private int startyear;
	private int finishyear;
	private int studentnumber;
	
	
	//Constructor
	public Student(String fname, String lname, String dob, Module[] modulestaking, int startyear, int finishyear,
			int studentnumber) {
		super(fname, lname, dob);
		this.modulestaking = modulestaking;
		this.startyear = startyear;
		this.finishyear = finishyear;
		this.studentnumber = studentnumber;
	}


	public Module[] getModulestaking() {
		return modulestaking;
	}


	public void setModulestaking(Module newmodule) {
		int length = this.modulestaking.length; //Gets the length of the original array
		Module[] newmodulearray = new Module[length+1]; //Makes a new array that is one item bigger
		
		for(int i = 0; i < length; i++) { //sets all the old array items into the new array
			newmodulearray[i] = this.modulestaking[i];
		}
		
		newmodulearray[length+1] = newmodule; //sets the new module to its place in the new array
		
		this.modulestaking = newmodulearray; //overwrites the old array with the new one
	}


	public int getStartyear() {
		return startyear;
	}


	public void setStartyear(int startyear) {
		this.startyear = startyear;
	}


	public int getFinishyear() {
		return finishyear;
	}


	public void setFinishyear(int finishyear) {
		this.finishyear = finishyear;
	}


	public int getStudentnumber() {
		return studentnumber;
	}


	public void setStudentnumber(int studentnumber) {
		this.studentnumber = studentnumber;
	}

}
