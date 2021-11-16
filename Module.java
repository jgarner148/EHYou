
public class
Module {
	private String modName;
	private String modCode;
	private String[] studentsTaking;
	private int[] totalMarks;
	private Tutor teacher;
	private Tutor moderator;

	//Constructor
	public Module(String modName, String modCode, String[] studentsTaking, int[] totalMarks, Tutor teacher, Tutor moderator) {
		this.modName = modName;
		this.modCode = modCode;
		this.studentsTaking = studentsTaking;
		this.totalMarks = totalMarks;
		this.teacher = teacher;
		this.moderator = moderator;
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

	public Tutor getTeacher() {
		return teacher;
	}

	public void setTeacher(Tutor teacher) {
		this.teacher = teacher;
	}

	public Tutor getModerator() {
		return moderator;
	}

	public void setModerator(Tutor moderator) {
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
