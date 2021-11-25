import java.io.*;

public class
Module implements Serializable {
	private String modName;
	private String modCode;
	private String[] studentsTaking;
	private int[] totalMarks = new int[0];
	private String[] teachers;
	private String moderator;

	//Constructor
	public Module(String modName, String modCode, String[] studentsTaking, /**int[] totalMarks,**/ String[] teachers, String moderator) throws IOException, ClassNotFoundException {
		this.modName = modName;
		this.modCode = modCode;
		this.studentsTaking = studentsTaking;
		//this.totalMarks = totalMarks;
		this.teachers = teachers;
		this.moderator = moderator;

		for(int i=0; i<this.teachers.length; i++){
			Tutor addingTo = getobject.tutor(this.teachers[i]);
			String[] fetchedModules = addingTo.getModulesTeaching();
			boolean doesexist = false;
			for(int l=0; l<fetchedModules.length;l++){
				if(fetchedModules[l].equals(this.modCode)){
					doesexist=true;
				}
			}
			if(!doesexist) {
				addingTo.addToModulesTeaching(this.modCode);
				addingTo.updateClassFile();
			}
		}

		for(int i=0; i<this.studentsTaking.length; i++){
			Student addingTo = getobject.student(this.studentsTaking[i]);
			String[] fetchedModules = addingTo.getModulesTaking();
			boolean doesexist = false;
			for(int l=0; l<fetchedModules.length;l++){
				if(fetchedModules[l].equals(this.modCode)){
					doesexist=true;
				}
			}
			if(!doesexist) {
				addingTo.addToModulesTaking(this.modCode);
				addingTo.updateClassFile();
			}
		}




		boolean isModZero = false;
		if(this.moderator.length()==0){
			isModZero = true;
		}
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

		String filename = "Modules/" +this.getModCode() + ".txt";
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();

		quickMethods.addStringToCSV("codes/modulecodes.csv", modCode);

	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) throws IOException {
		this.modName = modName;
		this.updateClassFile();
	}

	public String getModCode() {
		return modCode;
	}

	public void setModCode(String modCode) throws IOException {
		this.modCode = modCode;
		this.updateClassFile();
	}

	public String[] getStudentsTaking() {
		return studentsTaking;
	}

	public void addToStudentsTaking(String studentsTaking) throws IOException {
		this.studentsTaking = AddToArray.string(this.studentsTaking, studentsTaking);
		this.updateClassFile();
	}

	public int[] getTotalMarks(){return this.totalMarks;}

	public void addToTotalMarks(int newmark) throws IOException {
		this.totalMarks = AddToArray.integer(this.totalMarks, newmark);
		this.updateClassFile();
	}

	public String[] getTeachers() {
		return teachers;
	}

	public void addToTeachers(String newteacher) throws IOException {
		this.teachers = AddToArray.string(this.teachers, newteacher);
		this.updateClassFile();
	}

	public String getModerator() {
		return moderator;
	}

	public void setModerator(String moderator) throws IOException {
		this.moderator = moderator;
		this.updateClassFile();
	}

	public int getAverageMark(){
		int allmarks = 0;
		for (int i = 0; i>this.totalMarks.length; i++){
			allmarks = allmarks + this.totalMarks[i];
		}
		int totalAverage = allmarks/this.totalMarks.length;
		return totalAverage;
	}

	public int getMinMark(){
		int minmark = 101;
		for (int i = 0 ; i>this.totalMarks.length; i++){
			int currentmark =  this.totalMarks[i];
			if (currentmark<minmark){
				minmark =currentmark;
			}
		}
		return minmark;
	}

	public int getMaxMark(){
		int maxmark = 0;
		for (int i = 0 ; i>this.totalMarks.length; i++){
			int currentmark =  this.totalMarks[i];
			if (currentmark>maxmark){
				maxmark =currentmark;
			}
		}
		return maxmark;
	}

	public void updateClassFile() throws IOException {
		String filename = "Modules/" +this.getModCode() + ".txt";
		File oldFile = new File(filename);
		oldFile.delete();

		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();
	}
}
