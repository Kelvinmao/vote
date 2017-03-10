package db.pojo;

import java.util.Date;

public class ProjectInfo
{
	private Long ProjectID;
	private String Academy;
	private String ProjectName;
	private String ProjectType;
	private	int StudentNum;
	private	String LeaderName;
	private	String LeaderPhone;
	private	String LeaderEmail;
	private	String StudentOther;
	private	String TutorName;
	private	String ProjectOrient;
	private	String ProjectNemo;
	private int VoteNum;
	
	public Long getProjectID() {
		return ProjectID;
	}
	public void setProjectID(Long projectID) {
		ProjectID = projectID;
	}
	public String getAcademy() {
		return Academy;
	}
	public void setAcademy(String academy) {
		Academy = academy;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getProjectType() {
		return ProjectType;
	}
	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}
	public int getStudentNum() {
		return StudentNum;
	}
	public void setStudentNum(int studentNum) {
		StudentNum = studentNum;
	}
	public String getLeaderName() {
		return LeaderName;
	}
	public void setLeaderName(String leaderName) {
		LeaderName = leaderName;
	}
	public String getLeaderPhone() {
		return LeaderPhone;
	}
	public void setLeaderPhone(String leaderPhone) {
		LeaderPhone = leaderPhone;
	}
	public String getLeaderEmail() {
		return LeaderEmail;
	}
	public void setLeaderEmail(String leaderEmail) {
		LeaderEmail = leaderEmail;
	}
	public String getStudentOther() {
		return StudentOther;
	}
	public void setStudentOther(String studentOther) {
		StudentOther = studentOther;
	}
	public String getTutorName() {
		return TutorName;
	}
	public void setTutorName(String tutorName) {
		TutorName = tutorName;
	}
	public String getProjectOrient() {
		return ProjectOrient;
	}
	public void setProjectOrient(String projectOrient) {
		ProjectOrient = projectOrient;
	}
	public String getProjectNemo() {
		return ProjectNemo;
	}
	public void setProjectNemo(String projectNemo) {
		ProjectNemo = projectNemo;
	}
	public int getVoteNum() {
		return VoteNum;
	}
	public void setVoteNum(int voteNum) {
		VoteNum = voteNum;
	}
	
}