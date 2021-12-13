import java.io.*;

/**
 * Class file for Module object
 */
public class Module implements Serializable {
	/**
	 * Module name
	 */
	private String modName;
	/**
	 * Module Code
	 */
	private final String modCode;
	/**
	 * String array of the IDs of all the students taking the module
	 */
	private String[] studentsTaking;
	/**
	 * Int array of all the marks
	 */
	private int[] totalMarks = new int[0];
	/**
	 * String array of all the IDs of the Tutors teaching the module
	 */
	private String[] teachers;
	/**
	 * ID of the tutor moderating the module
	 */
	private String moderator;

	/**
	 * Constructor for Module
	 * @param modName Module name
	 * @param modCode Module code
	 * @param studentsTaking String array of the IDs of all the students taking the module
	 * @param teachers String array of all the IDs of the Tutors teaching the module
	 * @param moderator ID of the tutor moderating the module
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException CLass not Found Exception
	 */
	public Module(String modName, String modCode, String[] studentsTaking, String[] teachers, String moderator) throws IOException, ClassNotFoundException {
		//Assigning the taken in variables to the appropriate class variable
		this.modName = modName;
		this.modCode = modCode;
		this.studentsTaking = studentsTaking;
		this.teachers = teachers;
		this.moderator = moderator;

		//Creating the class file for the newly created module object
		String filename = "Modules/" +this.getModCode() + ".txt";
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();

		//Adding the module ID to all the tutors in the teachers array
		boolean isTeachersEmpty = this.teachers.length == 0;
		if(!isTeachersEmpty) {
			for (int i = 0; i < this.teachers.length; i++) {
				Tutor addingTo = getobject.tutor(this.teachers[i]);
				String[] fetchedModules = addingTo.getModulesTeaching();
				boolean doesexist = false;
				for (int l = 0; l < fetchedModules.length; l++) {
					if (fetchedModules[l].equals(this.modCode)) {
						doesexist = true;
					}
				}
				if (!doesexist) {
					addingTo.addToModulesTeaching(this.modCode);
					addingTo.updateClassFile();
				}
			}
		}

		//Adding the module ID to all the students in the students array
		boolean isStudentsEmpty = this.studentsTaking.length == 0;
		if(!isStudentsEmpty) {
			for (int i = 0; i < this.studentsTaking.length; i++) {
				Student addingTo = getobject.student(this.studentsTaking[i]);
				String[] fetchedModules = addingTo.getModulesTaking();
				boolean doesexist = false;
				for (int l = 0; l < fetchedModules.length; l++) {
					if (fetchedModules[l].equals(this.modCode)) {
						doesexist = true;
					}
				}
				if (!doesexist) {
					addingTo.addToModulesTaking(this.modCode);
					addingTo.updateClassFile();
				}
			}
		}

		//Adding the module ID to the Tutor moderating the module
		boolean isModZero = this.moderator.length() == 0;
		if(!isModZero) {
			Tutor addingTo = getobject.tutor(this.moderator);
			String[] featchedModules = addingTo.getModulesModerating();
			boolean doesexist = false;
			for (int l = 0; l < featchedModules.length; l++) {
				if (featchedModules[l].equals(this.modCode)) {
					doesexist = true;
				}
			}
			if (!doesexist) {
				addingTo.addToModulesModerating(this.modCode);
				addingTo.updateClassFile();
			}
		}

		quickMethods.addStringToCSV("codes/modulecodes.csv", modCode);//Adding the module code the overall module codes csv file

	}

	/**
	 * Getter for Module name
	 * @return Module's name
	 */
	public String getModName() {return this.modName;}

	/**
	 * Setter for module name
	 * @param modName the value being assigned to module name
	 * @throws IOException IO Exception
	 */
	public void setModName(String modName) throws IOException {
		this.modName = modName;
		this.updateClassFile();
	}

	/**
	 *Getter for the module code
	 * @return The module's code
	 */
	public String getModCode() {return this.modCode;}

	/**
	 * Getter for students taking array
	 * @return module's students taking string array
	 */
	public String[] getStudentsTaking() {return this.studentsTaking;}

	/**
	 * Method to add string to the students taking array
	 * @param studentsTaking item to be added to the students taking array
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found Exception
	 */
	public void addToStudentsTaking(String studentsTaking) throws IOException, ClassNotFoundException {
		this.studentsTaking = AddToArray.string(this.studentsTaking, studentsTaking);
		this.updateClassFile();

		//Updating the student class that's just be assigned to this module
		Student addingTo = getobject.student(studentsTaking);
		String[] addingToArray = addingTo.getModulesTaking();
		boolean exists = quickMethods.checkIfInStringArray(this.modCode, addingToArray);
		if(!exists) {
			addingTo.addToModulesTaking(this.modCode);
		}
	}

	/**
	 * Method to remove string from the studetns taking array
	 * @param removingStudent item to be removed from studetns array
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void removeFromStudentsTaking(String removingStudent) throws IOException, ClassNotFoundException {
		this.studentsTaking = quickMethods.removeFromStringArray(removingStudent, this.studentsTaking);
		this.updateClassFile();

		//Updating the student class that has just been removed from the students taking array
		Student removingFrom = getobject.student(removingStudent);
		String[] removingFromArray = removingFrom.getModulesTaking();
		boolean exists = quickMethods.checkIfInStringArray(this.modCode, removingFromArray);
		if(exists) {
			removingFrom.removeFromModules(this.modCode);
		}
	}

	/**
	 * Method to add an Int to the total marks array
	 * @param newmark Int being added to the array
	 * @throws IOException IO Exception
	 */
	public void addToTotalMarks(int newmark) throws IOException {
		this.totalMarks = AddToArray.integer(this.totalMarks, newmark);
		this.updateClassFile();
	}

	/**
	 * Getter for the teachers array
	 * @return The module's teachers array
	 */
	public String[] getTeachers() {return this.teachers;}

	/**
	 * Method to add new tutor to the teachers array
	 * @param newteacher Item being added to teachers array
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void addToTeachers(String newteacher) throws IOException, ClassNotFoundException {
		this.teachers = AddToArray.string(this.teachers, newteacher);
		this.updateClassFile();

		//Updating the tutor that has just been added to the teachers array
		Tutor addingTo = getobject.tutor(newteacher);
		String[] addingToArray = addingTo.getModulesTeaching();
		boolean exists = quickMethods.checkIfInStringArray(this.modCode, addingToArray);
		if (!exists) {
			addingTo.addToModulesTeaching(this.modCode);
		}
	}

	/**
	 * Method to remove a tutor from the teachers array
	 * @param removingTeacher Item being removed from teachers array
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void removeFromTeachers(String removingTeacher) throws IOException, ClassNotFoundException {
		this.teachers = quickMethods.removeFromStringArray(removingTeacher, this.teachers);
		this.updateClassFile();

		//Updating the tutor that has just been removed from this module
		Tutor removingFrom = getobject.tutor(removingTeacher);
		String[] removingFromArray = removingFrom.getModulesTeaching();
		boolean exists = quickMethods.checkIfInStringArray(this.modCode, removingFromArray);
		if(exists) {
			removingFrom.removeFromModulesTeaching(this.modCode);
		}
	}

	/**
	 * Getter for moderator
	 * @return The module's moderator
	 */
	public String getModerator() {return this.moderator;}

	/**
	 * Setter for moderator
	 * @param moderator Value being added to moderator
	 * @throws IOException IO Exception
	 * @throws ClassNotFoundException Class not found exception
	 */
	public void setModerator(String moderator) throws IOException, ClassNotFoundException {
		this.moderator = moderator;
		this.updateClassFile();

		//Updating the tutor that has just been assigned as moderator
		Tutor newMod = getobject.tutor(moderator);
		String[] newModArray = newMod.getModulesModerating();
		boolean exists = quickMethods.checkIfInStringArray(this.modCode, newModArray);
		if(!exists){
			newMod.addToModulesModerating(this.modCode);
		}
	}

	/**
	 * Method for getting a modules average mark
	 * @return Average mark calculated
	 */
	public int getAverageMark(){
		int allmarks = 0;
		//Adding all the marks to a variable
		for (int i = 0; i<this.totalMarks.length; i++){
			allmarks = allmarks + this.totalMarks[i];
		}
		//Returning 0 is the array is empty
		if(this.totalMarks.length==0){
			return 0;
		}
		//Returning the average
		else {
			return allmarks / this.totalMarks.length;
		}
	}

	/**
	 * Method for getting the minimum mark for the module
	 * @return Minimum mark calculated
	 */
	public int getMinMark(){
		int minmark = 101;
		//Finding the lowest mark
		for (int i = 0 ; i<this.totalMarks.length; i++){
			int currentmark =  this.totalMarks[i];
			if (currentmark<minmark){
				minmark =currentmark;
			}
		}
		//Returning 0 if no marks are found
		if(minmark == 101){
			return 0;
		}
		//Returning the lowest mark
		else {
			return minmark;
		}
	}

	/**
	 * Method for getting the maximum mark for the module
	 * @return Maximum mark calculated
	 */
	public int getMaxMark(){
		int maxmark = 0;
		//Finding the maximum mark
		for (int i = 0 ; i<this.totalMarks.length; i++){
			int currentmark =  this.totalMarks[i];
			if (currentmark>maxmark){
				maxmark =currentmark;
			}
		}
		return maxmark;//Returning the maximum mark
	}

	/**
	 * Method to update class file be deleting the old one and creating a new one
	 * @throws IOException IO Exception
	 */
	public void updateClassFile() throws IOException {
		String filename = "Modules/" +this.getModCode() + ".txt";

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
