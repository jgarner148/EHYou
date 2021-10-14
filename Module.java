
public class Module {
	private String name;
	private String code;
	private Tutor leader;
	private Tutor moderator;
	
	//constructor
	public Module(String name, String code, Tutor leader, Tutor moderator) {
		this.name = name;
		this.code = code;
		this.leader = leader;
		this.moderator = moderator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Tutor getLeader() {
		return leader;
	}

	public void setLeader(Tutor leader) {
		this.leader = leader;
	}

	public Tutor getModerator() {
		return moderator;
	}

	public void setModerator(Tutor moderator) {
		this.moderator = moderator;
	}
	
	
	
	
}
