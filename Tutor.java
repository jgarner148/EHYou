public class Tutor extends Person {
	private int StaffNum;
	private String Office;

	public Tutor(String fname, String lname, String dob, int staffNum, String office) {
		super(fname, lname, dob);
		this.StaffNum = staffNum;
		this.Office = office;
	}

	public int getStaffNum() {
		return StaffNum;
	}

	public void setStaffNum(int staffNum) {
		StaffNum = staffNum;
	}

	public String getOffice() {
		return Office;
	}

	public void setOffice(String office) {
		Office = office;
	}
}
