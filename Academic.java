
public class Academic extends Tutor{
	private String[] CurrentResearch;

	//Constructor
	public Academic(String fname, String lname, String dob, int staffNum, String office, String[] currentResearch) {
		super(fname, lname, dob, staffNum, office);
		this.CurrentResearch = currentResearch;
	}
	
	//Getter for the research object array
	public String[] getCurrentResearch() {
		return CurrentResearch;
	}

	//Method to add a new research object to the research object array
	public void addToCurrentResearch(String currentResearch) {
		this.CurrentResearch = AddToArray.string(this.CurrentResearch, currentResearch);
	}
}
