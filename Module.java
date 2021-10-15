
public class  Module {
	private String modName;
	private String modCode;
	private Student[] studentsTaking;
	private Tutor teacher;
	private Tutor moderator;

	//Constructor
	public Module(String modName, String modCode, Student[] studentsTaking, Tutor teacher, Tutor moderator) {
		this.modName = modName;
		this.modCode = modCode;
		this.studentsTaking = studentsTaking;
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

	public Student[] getStudentsTaking() {
		return studentsTaking;
	}

	public void addToStudentsTaking(Student studentsTaking) {
		this.studentsTaking = AddToArray.student(this.studentsTaking, studentsTaking);
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
}
