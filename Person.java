import java.io.Serializable;

public class Person implements Serializable {
	protected String fname;
	protected String lname;
	protected String dob;

	//Constructor
	public Person(String fname, String lname, String dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
	}


}
