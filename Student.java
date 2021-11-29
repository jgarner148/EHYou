import java.io.*;
import java.time.YearMonth;


public class Student extends Person{
	private String[] ModulesTaking;
	private String[] AllResults;
	private int StartYr;
	private int EndYr;
	private final String StudentNum;

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
			int yearstarted = this.StartYr;
			int randomnumber = quickMethods.randnum(1000, 9999); //Generates a random number using my class randnum and its class method generate
			String yearAsString = String.valueOf(yearstarted); //Turns yearstarted into String
			String numAsString = String.valueOf(randomnumber); //Turns randumnumber into string
			genNumber = yearAsString + numAsString; //Combines to two
			boolean doesExist = quickMethods.checkIfInFile(filepath, genNumber);
			if (!doesExist) {
				looping = false;
			}
		}

		this.StudentNum = genNumber;

		
		String filename = "Students/" +this.getStudentNum() + ".txt";
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();

		quickMethods.addStringToCSV(filepath, genNumber);

	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) throws IOException {
		this.fname = fname;
		this.updateClassFile();
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) throws IOException {
		this.lname = lname;
		this.updateClassFile();
	}

	public String getDob() {
		return dob;
	}

	public String[] getModulesTaking(){
		return ModulesTaking;
	}

	public void addToModulesTaking(String newmodule) throws IOException {
		this.ModulesTaking = AddToArray.string(this.ModulesTaking, newmodule);
		this.updateClassFile();
	}

	public int getStartYr() {
		return StartYr;
	}

	public void setStartYr(int startYr) throws IOException {
		this.StartYr = startYr;
		this.updateClassFile();
	}

	public int getEndYr() {
		return EndYr;
	}

	public void setEndYr(int endYr) throws IOException {
		EndYr = endYr;
		this.updateClassFile();
	}

	public String getStudentNum() {
		return StudentNum;
	}

	public String[] getAllResults() {
		return this.AllResults;
	}

	public void addToAllResults(String newresult) throws IOException {
		this.AllResults = AddToArray.string(this.AllResults, newresult);
		this.updateClassFile();
	}

	public int averageGradeForModule(String targetModuleCode) throws IOException, ClassNotFoundException {
		String[] allResults = this.getAllResults();
		int allgrades = 0;
		int resultsAmount = 0;
		for(int i=0; i < allResults.length; i++) {
			String currentResultCode = allResults[i];
			Result currentResult = getobject.result(currentResultCode);
			String modcode = currentResult.getAssModule();
			if(modcode.equals(targetModuleCode)){
				int currentGrade = currentResult.getGrade();
				allgrades = allgrades+currentGrade;
				resultsAmount++;
			}
		}
		if(resultsAmount == 0){
			return 0;
		}
		else {
			return allgrades / resultsAmount;
		}


	}

	public void updateClassFile() throws IOException {
		String filename = "Students/" +this.getStudentNum() + ".txt";
		File oldFile = new File(filename);
		oldFile.delete();

		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();
	}

}
