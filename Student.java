import java.io.*;

/**
 *Class file for the student object
 */
public class Student extends Person{
	/**
	 * String array of the modules the student is taking
	 */
	private String[] ModulesTaking;
	/**
	 * String array of the all results the student has been given
	 */
	private String[] AllResults;
	/**
	 * The year the student started at the university
	 */
	private int StartYr;
	/**
	 * The year the student will leave the university
	 */
	private int EndYr;
	/**
	 * The student number
	 */
	private final String StudentNum;

	/**
	 * The constructor for student
	 * @param fname The student's first name
	 * @param lname The student's last name
	 * @param dob The students date of birth
	 * @param modulesTaking String array of the modules the student is taking
	 * @param allResults String array of the all results the student has been given
	 * @param startYr The year the student started at the university
	 * @param endYr The year the student will leave the university
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public Student(String fname, String lname, String dob, String[] modulesTaking, String[] allResults, int startYr, int endYr) throws IOException, ClassNotFoundException {
		super(fname, lname, dob); //Calling the constructor of the super classes using the taken in variables
		//Assigning the taken in variables to the appropriate class variable
		this.ModulesTaking = modulesTaking;
		this.AllResults = allResults;
		this.StartYr = startYr;
		this.EndYr = endYr;

		//Generating the student number
		boolean looping = true;
		String filepath = "codes/studentNumbers.csv";
		String genNumber = "";
		while (looping) {
			int yearstarted = this.StartYr;
			int randomnumber = quickMethods.randnum(1000, 9999); //Generates a random number using my class randnum and its class method generate  jglhmb
			String yearAsString = String.valueOf(yearstarted); //Turns yearstarted into String
			String numAsString = String.valueOf(randomnumber); //Turns randumnumber into string
			genNumber = yearAsString + numAsString; //Combines to two
			boolean doesExist = quickMethods.checkIfInFile(filepath, genNumber);
			if (!doesExist) {
				looping = false;
			}
		}

		this.StudentNum = genNumber; //Assigning the generated student number to the student number variable

		//Creating class file for the newly created student class
		String filename = "Students/" + this.getStudentNum() + ".txt";
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();

		//Adding the student number to each of the modules in the modules taking array
		boolean isModulesTakingEmpty = this.ModulesTaking.length == 0;
        if(!isModulesTakingEmpty){
			for (int i=0; i < this.ModulesTaking.length; i++){
				Module addingTo = getobject.module(this.ModulesTaking[i]);
				String[] fetchedStudents = addingTo.getStudentsTaking();
				boolean doesexist = false;
				for (int l =0; l < fetchedStudents.length;l++){
					if(fetchedStudents[l].equals(this.StudentNum)){
						doesexist = true;
					}
				}
				if (!doesexist){
					addingTo.addToStudentsTaking(this.StudentNum);
					addingTo.updateClassFile();
				}
			}
		}

		quickMethods.addStringToCSV(filepath, genNumber);//adding the student number to the overall student number CSV file

	}

	/**
	 * Getter for first name
	 * @return first name
	 */
	public String getFname() {return this.fname;}

	/**
	 * Setter for first name
	 * @param fname The value being assigned to first name
	 * @throws IOException IO Exception
	 */
	public void setFname(String fname) throws IOException {
		this.fname = fname;
		this.updateClassFile();
	}

	/**
	 * Getter for last name
	 * @return last name
	 */
	public String getLname() {return this.lname;}

	/**
	 * Setter for last name
	 * @param lname the value being assigned to last name
	 * @throws IOException IO Exception
	 */
	public void setLname(String lname) throws IOException {
		this.lname = lname;
		this.updateClassFile();
	}

	/**
	 * Getter for dob
	 * @return date of birth
	 */
	public String getDob() {return this.dob;}

	/**
	 * Setter for dob
	 * @param dob the value being assigned to dob
	 * @throws IOException IO Exception
	 */
	public void setDob(String dob) throws IOException {
		this.dob = dob;
		this.updateClassFile();
	}

	/**
	 * Getter for modules taking
	 * @return modules taking array
	 */
	public String[] getModulesTaking(){return this.ModulesTaking;}

	/**
	 * Method to add a module code to the modules taking array
	 * @param newmodule the module code being added to the array
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void addToModulesTaking(String newmodule) throws IOException, ClassNotFoundException {
		this.ModulesTaking = AddToArray.string(this.ModulesTaking, newmodule);
		this.updateClassFile();

		//Updating the module that has just been added this is student
		Module addingTo = getobject.module(newmodule);
		String[] addingToArray = addingTo.getStudentsTaking();
		boolean exists = quickMethods.checkIfInStringArray(this.StudentNum, addingToArray);
		if(!exists) {
			addingTo.addToStudentsTaking(this.StudentNum);
		}
	}

	/**
	 * Method to remove module from the modules taking array
	 * @param removingModule the module code of the item being removed from the array
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void removeFromModules(String removingModule) throws IOException, ClassNotFoundException {
		this.ModulesTaking = quickMethods.removeFromStringArray(removingModule, this.ModulesTaking);
		this.updateClassFile();

		//Updating the module that has just been removed from the array
		Module removingFrom = getobject.module(removingModule);
		String[] removingFromArray = removingFrom.getStudentsTaking();
		boolean exists = quickMethods.checkIfInStringArray(this.StudentNum, removingFromArray);
		if(exists) {
			removingFrom.removeFromStudentsTaking(this.StudentNum);
		}
	}

	/**
	 * getter for start year
	 * @return start year
	 */
	public int getStartYr() {return this.StartYr;}

	/**
	 * Setter for start year
	 * @param startYr the value being assigned to start year
	 * @throws IOException IO Exception
	 */
	public void setStartYr(int startYr) throws IOException {
		this.StartYr = startYr;
		this.updateClassFile();
	}

	/**
	 * Getter for end year
	 * @return end year
	 */
	public int getEndYr() {return this.EndYr;}

	/**
	 * Setter for end year
	 * @param endYr value being assigned to end year
	 * @throws IOException IO Exception
	 */
	public void setEndYr(int endYr) throws IOException {
		EndYr = endYr;
		this.updateClassFile();
	}

	/**
	 * Getter for student number
	 * @return student number
	 */
	public String getStudentNum() {return this.StudentNum;}

	/**
	 * Getter for the all results array
	 * @return all results array
	 */
	public String[] getAllResults() {return this.AllResults;}

	/**
	 * Method to add a result to all results array
	 * @param newresult string being added to array
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void addToAllResults(String newresult) throws IOException, ClassNotFoundException {
		this.AllResults = AddToArray.string(this.AllResults, newresult);
		this.updateClassFile();

		//Updating the Result object
		Result addingTo = getobject.result(newresult);
		String addingToStudent = addingTo.getAssStudent();
		if(!addingToStudent.equals(this.StudentNum)){
			addingTo.setAssStudent(this.StudentNum);
		}
	}

	/**
	 * Method to remove result code from all results string array
	 * @param removingResult result to be removed
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void removeFromAllResults(String removingResult) throws IOException, ClassNotFoundException {
		this.AllResults = quickMethods.removeFromStringArray(removingResult, this.AllResults);
		this.updateClassFile();

		//Updating the Result object
		Result removingFrom = getobject.result(removingResult);
		String removingFromStudent = removingFrom.getAssStudent();
		if(removingFromStudent.equals(this.StudentNum)){
			removingFrom.setAssStudent("");
		}
	}

	/**
	 * Method to work out a students average grade for a given module
	 * @param targetModuleCode Module to be calculated from
	 * @return Result of calculation
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public int averageGradeForModule(String targetModuleCode) throws IOException, ClassNotFoundException {
		String[] allResults = this.getAllResults();
		int allgrades = 0;
		int resultsAmount = 0;
		//Adding up all the grades
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
		//If there are no results returning 0
		if(resultsAmount == 0){
			return 0;
		}
		//returning the result of the calculation
		else {
			return allgrades / resultsAmount;
		}
	}

	/**
	 * Method to update class file be deleting the old one and creating a new one
	 * @throws IOException IO Exception
	 */
	public void updateClassFile() throws IOException {
		String filename = "Students/" +this.getStudentNum() + ".txt";

		//Deleting the old file
		File oldFile = new File(filename);
		oldFile.delete();

		//Creating the new file
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();
	}
}
