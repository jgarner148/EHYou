import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.YearMonth;
import java.util.Scanner;


public class Student extends Person {
	private Module[] ModulesTaking;
	private Result[] AllResults;
	private int StartYr;
	private int EndYr;
	private int StudentNum;
	
	
	//Constructor
	public Student(String fname, String lname, String dob, Module[] modulesTaking, Result[] allResults, int startYr, int endYr) throws FileNotFoundException {
		super(fname, lname, dob);
		this.ModulesTaking = modulesTaking;
		this.AllResults = allResults;
		this.StartYr = startYr;
		this.EndYr = endYr;
		//Section for generating a user number
		Boolean looping = true;
		int generatedStudentNum = 0; //defines the int that will be assigned the random number
		while(looping==true) {
			System.out.println("looped");
			int currentyear = YearMonth.now().getYear(); //Gets the current year
			int randomnumber = quickMethods.randnum(1000, 9999); //Generates a random number using my class randnum and its class method generate
			String yearAsString = String.valueOf(currentyear);
			String numAsString = String.valueOf(randomnumber);
			String allAsString = yearAsString + numAsString;

			generatedStudentNum = quickMethods.stringToNum(allAsString);  //Converts String into an int


			Scanner csvScan = new Scanner(new File("studentNumbers.csv"));
			csvScan.useDelimiter(",");
			while (csvScan.hasNext()){
				String currentNumberString = csvScan.next();
				System.out.println(currentNumberString);
				int currentNumber = quickMethods.stringToNum(currentNumberString);
				  if(currentNumber != generatedStudentNum){
				  	looping = false;
				  }
			}
			}
		PrintWriter writer = new PrintWriter("studentNumbers.csv");
		String newCSVItem = String.valueOf(generatedStudentNum);
		writer.write(newCSVItem + ",");
		this.StudentNum = generatedStudentNum;
	}

	public Module[] getModulesTaking() {
		return ModulesTaking;
	}


	public void addToModulesTaking(Module newmodule) {
		this.ModulesTaking = AddToArray.module(this.ModulesTaking, newmodule);
	}


	public int getStartYr() {
		return StartYr;
	}

	public void setStartYr(int startYr) {
		StartYr = startYr;
	}

	public int getEndYr() {
		return EndYr;
	}

	public void setEndYr(int endYr) {
		EndYr = endYr;
	}

	public int getStudentNum() {
		return StudentNum;
	}

	public void setStudentNum(int studentNum) {
		StudentNum = studentNum;
	}

	public Result[] getAllResults() {
		return AllResults;
	}

	public void addToAllResults(Result newresult) {
		this.AllResults = AddToArray.result(this.AllResults, newresult);
	}

	public int averageGradeForModule(){
		Result[] allResults = getAllResults();
		int i;
		int currentGrade;
		int allgrades;
		for(i=0; i < allresults.length; i++){
			Result currentResult = allResults[i];
			currentGrade = currentResult.getGrade();
			allgrades = allgrades + currentGrade;
		}

		int average = allgrades / allResults.length;
		return average;
	}

}
