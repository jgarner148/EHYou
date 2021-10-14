
public class Academic extends Person{
	private int academicnumber;
	
	//Constructor
	public Academic(String fname, String lname, String dob, int academicnumber) {
		super(fname, lname, dob);
		this.academicnumber = academicnumber;
	}

	public int getAcademicnumber() {
		return academicnumber;
	}

	public void setAcademicnumber(int academicnumber) {
		this.academicnumber = academicnumber;
	}
	
	


	
}
