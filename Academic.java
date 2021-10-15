
public class Academic extends Tutor{
	private Research[] CurrentResearch;

	public Academic(String fname, String lname, String dob, int staffNum, String office, Research[] currentResearch) {
		super(fname, lname, dob, staffNum, office);
		this.CurrentResearch = currentResearch;
	}

	public Research[] getCurrentResearch() {
		return CurrentResearch;
	}

	public void addToCurrentResearch(Research currentResearch) {
		this.CurrentResearch = AddToArray.research(this.CurrentResearch, currentResearch);
	}
}
