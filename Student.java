import java.io.*;
import java.time.YearMonth;


public class Student extends Person{
	private String[] ModulesTaking;
	private String[] AllResults;
	private int StartYr;
	private int EndYr;
	private String StudentNum;
	
	
	//Constructor
	public Student(String fname, String lname, String dob, String[] modulesTaking, String[] allResults, int startYr, int endYr) throws IOException {
		super(fname, lname, dob);
		this.ModulesTaking = modulesTaking;
		this.AllResults = allResults;
		this.StartYr = startYr;
		this.EndYr = endYr;
		//Section for generating a user number
		boolean looping = true;
		String filepath = "codes/studentNumbers.csv";
		String genNumber = "";
		while (looping) {
			int currentyear = YearMonth.now().getYear(); //Gets the current year
			int randomnumber = quickMethods.randnum(1000, 9999); //Generates a random number using my class randnum and its class method generate
			String yearAsString = String.valueOf(currentyear); //Turns currentyear into String
			String numAsString = String.valueOf(randomnumber); //Turns randumnumber into string
			genNumber = yearAsString + numAsString; //Combines to two
			boolean doesExist = quickMethods.checkIfInFile(filepath, genNumber);
			if (!doesExist) {
				looping = false;
			}
		}
		quickMethods.addStringToCSV(filepath, genNumber);
		this.StudentNum = genNumber;

		
		String filename = "Students/" +this.getStudentNum() + ".txt";
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();

	}

	public String[] getModulesTaking(){
		return ModulesTaking;
	}


	public void addToModulesTaking(String newmodule) {
		this.ModulesTaking = AddToArray.string(this.ModulesTaking, newmodule);
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

	public String[] getAllResults() {
		return this.AllResults;
	}

	public void addToAllResults(String newresult) {
		this.AllResults = AddToArray.string(this.AllResults, newresult);
	}

	
	public int averageGradeForModule(String targetModuleCode) throws IOException, ClassNotFoundException {
		String[] allResults = this.getAllResults();
		int allgrades = 0;
		int resultsAmount = 0;
		for(int i=0; i < allResults.length; i++) {
			String currentResultCode = allResults[i];
			Result currentResult = getobject.result(currentResultCode);
			String currentResultModule = currentResult.getAssModule();
			Module currentModule = getobject.module(currentResultModule);
			String modcode = currentModule.getModCode();
			if(modcode.equals(targetModuleCode)){
				int currentGrade = currentResult.getGrade();
				allgrades = allgrades+currentGrade;
				resultsAmount++;
			}
		}
		return allgrades/resultsAmount;


	}
	 

}
