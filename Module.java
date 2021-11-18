import java.io.IOException;

public class
Module {
	private String modName;
	private String modCode;
	private String[] studentsTaking;
	private int[] totalMarks;
	private String[] teachers;
	private String moderator;

	//Constructor
	public Module(String modName, String modCode, String[] studentsTaking, int[] totalMarks, String[] teachers, String moderator) throws IOException, ClassNotFoundException {
		this.modName = modName;
		this.modCode = modCode;
		this.studentsTaking = studentsTaking;
		this.totalMarks = totalMarks;
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
			}
		}

		Tutor addingTo = getobject.tutor(this.moderator);
		String[] featchedModules = addingTo.getModulesModerating();
		boolean doesexist = false;
		for(int l=0; l<featchedModules.length;l++){
			if(featchedModules[l].equals(this.modCode)){
				doesexist=true;
			}
		}
		if(!doesexist) {
			addingTo.addToModulesModerating(this.modCode);
		}


	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getModCode() {
		return modCode;
	}

	public void setModCode(String modCode) {
		this.modCode = modCode;
	}

	public String[] getStudentsTaking() {
		return studentsTaking;
	}

	public void addToStudentsTaking(String studentsTaking) {
		this.studentsTaking = AddToArray.string(this.studentsTaking, studentsTaking);
	}

	public int[] getTotalMarks(){return this.totalMarks;}

	public void addToTotalMarks(int newmark){
		this.totalMarks = AddToArray.integer(this.totalMarks, newmark);
	}

	public String[] getTeachers() {
		return teachers;
	}

	public void addToTeachers(String newteacher) {
		this.teachers = AddToArray.string(this.teachers, newteacher);
	}

	public String getModerator() {
		return moderator;
	}

	public void setModerator(String moderator) {
		this.moderator = moderator;
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




}
