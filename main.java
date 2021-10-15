
public class main {

	public static void main(String[] args) {
		Research[] testerarray = {};
		Academic[] testerarray2 = {};
		Academic Phil = new Academic("Phil", "Smith", "20/05/2002", 245, "THG01", testerarray);
		Research AI = new Research("AI", testerarray2);
		Research Health = new Research("Health", testerarray2);
		Research Robot = new Research("Robot", testerarray2);
		Phil.addToCurrentResearch(AI);
		Phil.addToCurrentResearch(Health);
		Phil.addToCurrentResearch(Robot);
		Research[] philsresearch= Phil.getCurrentResearch();
		for(int i = 0; i<philsresearch.length;i++){
			Research firstresearch = philsresearch[i];
			String firstname = firstresearch.getResearchTitle();
			System.out.println(firstname);
		}
	}
}
