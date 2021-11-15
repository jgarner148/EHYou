import java.io.*;
import java.time.YearMonth;


public class Student extends Person {
	private Module[] ModulesTaking;
	private Result[] AllResults;
	private int StartYr;
	private int EndYr;
	private String StudentNum;
	
	
	//Constructor
	public Student(String fname, String lname, String dob, Module[] modulesTaking, Result[] allResults, int startYr, int endYr) throws IOException {
		super(fname, lname, dob);
		this.ModulesTaking = modulesTaking;
		this.AllResults = allResults;
		this.StartYr = startYr;
		this.EndYr = endYr;
		//Section for generating a user number
		boolean looping = true;
		String filepath = "studentNumbers.csv";
		String genNumber = "";
		while (looping == true) {
			int currentyear = YearMonth.now().getYear(); //Gets the current year
			int randomnumber = quickMethods.randnum(1000, 9999); //Generates a random number using my class randnum and its class method generate
			String yearAsString = String.valueOf(currentyear); //Turns currentyear into String
			String numAsString = String.valueOf(randomnumber); //Turns randumnumber into string
			genNumber = yearAsString + numAsString; //combines to two
			boolean doesExist = quickMethods.checkIfInFile(filepath, genNumber);
			if (doesExist == false) {
				looping = false;
			}
		}
		quickMethods.addStringToCSV(filepath, genNumber);
		this.StudentNum = genNumber;
	}

	public Module[] getModulesTaking(){
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

	public String getStudentNum() {
		return StudentNum;
	}

	public void setStudentNum(String studentNum) {
		StudentNum = studentNum;
	}

	public Result[] getAllResults() {
		return this.AllResults;
	}

	public void addToAllResults(Result newresult) {
		this.AllResults = AddToArray.result(this.AllResults, newresult);
	}

	public int averageGradeForModule(Module targetModule){
		String moduleCode = targetModule.getModCode();
		Result[] allResults = getAllResults(); //Gets the Results array from the class
		int allgrades = 0;
		int resultsAmount = 0;
		for(int i=0; i < allResults.length; i++){  //Iterates through the results array and gets the grade from each object and adds it to a running total
			Result currentResult = allResults[i];
			Module currentResultModule = currentResult.getAssModule();
			String currentResultModCode = currentResultModule.getModCode();
			if(moduleCode == currentResultModCode){
				int currentGrade = currentResult.getGrade();
				allgrades = allgrades + currentGrade;
				resultsAmount++;
			}
		}
		int average = allgrades / resultsAmount;
		return average;
	}

}
