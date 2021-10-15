import java.time.LocalDate;

public class Person {
	private String fname;
	private String lname;
	private String dob;

	//Constructor
	public Person(String fname, String lname, String dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDob() {
		return dob;
	}

}
