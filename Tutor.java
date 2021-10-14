public class Tutor extends Person{
	private int tutornumber;
	private Module[] taughtmodules;
	
	//Constructor
	public Tutor(String fname, String lname, String dob, int tutornumber, Module[] taughtmodules) {
		super(fname, lname, dob);
		this.tutornumber = tutornumber;
		this.taughtmodules = taughtmodules;
	}

	public int getTutornumber() {
		return tutornumber;
	}

	public void setTutornumber(int tutornumber) {
		this.tutornumber = tutornumber;
	}

	public Module[] getTaughtmodules() {
		return taughtmodules;
	}

	/**
	 * This method is used for when the user wants to add a new module to to the Tutor objects taughtmodules array
	 * @param newmodule This is the module that the user is finishing to add to the tutors array of taught modules
	 */
	public void addnewTaughtmodules(Module newmodule) {
		int length = this.taughtmodules.length; //Gets the length of the original array
		Module[] newmodulearray = new Module[length+1]; //Makes a new array that is one item bigger
		
		for(int i = 0; i < length; i++) { //sets all the old array items into the new array
			newmodulearray[i] = this.taughtmodules[i];
		}
		
		newmodulearray[length+1] = newmodule; //sets the new module to its place in the new array
		
		this.taughtmodules = newmodulearray; //overwrites the old array with the new one
	}
	
	
}
