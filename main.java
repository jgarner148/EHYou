import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		/**
		//This code was for testing purposes only to check that the add to array functions worked
		Research[] testarray1 = {}; //Creating a blank Research object array
		Academic[] testerarray2 = {}; //Creating a blank Academic object array
		Academic Phil = new Academic("Phil", "Smith", "20/05/2002", 245, "THG01", testarray1); //Creating a new Academic object named Phil
		Research AI = new Research("AI", testerarray2); //Creating a new Research object named AI
		Research Health = new Research("Health", testerarray2); //Creating a new Research object named Health
		Research Robot = new Research("Robot", testerarray2); //Creating a new Research object named Robot
		Phil.addToCurrentResearch(AI); //Adding the AI research object to the Phil Academic's current research array
		Phil.addToCurrentResearch(Health); //Adding the Health research object to the Phil Academic's current research array
		Phil.addToCurrentResearch(Robot); //Adding the Robot research object to the Phil Academic's current research array
		Research[] philsresearch= Phil.getCurrentResearch(); //Making a new research object array that stored the rsearch object array from the Phil Academic object 
		//For loop that gets every item from teh philsresearch array and prints it out
		for(int i = 0; i<philsresearch.length;i++){
			Research firstresearch = philsresearch[i];
			String firstname = firstresearch.getResearchTitle();
			System.out.println(firstname);
		}
		 */
		Module[] testarray3 = {};
		Result[] testarray4 = {};
		Student student1 = new Student("Dave", "Smith", "10/09/2001", testarray3, testarray4, 2020, 2021);


	}
}
