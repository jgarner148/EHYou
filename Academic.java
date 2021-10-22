
public class Academic extends Tutor{
	private Research[] CurrentResearch;

	//Constructor
	public Academic(String fname, String lname, String dob, int staffNum, String office, Research[] currentResearch) {
		super(fname, lname, dob, staffNum, office);
		this.CurrentResearch = currentResearch;
	}
	
	//Getter for the research object array
	public Research[] getCurrentResearch() {
		return CurrentResearch;
	}

	//Method to add a new research object to the research object array
	public void addToCurrentResearch(Research currentResearch) {
		this.CurrentResearch = AddToArray.research(this.CurrentResearch, currentResearch);
	}
}
