package db.pojo;

import java.util.Date;

public class VoteRecord
{
	private Long VoteID;
	private String OutID;
	private Long ProjectID;
	private int Checked;
	private String UserIP;
	private int InnerSchool;
	
	public Long getVoteID() {
		return VoteID;
	}
	public void setVoteID(Long voteID) {
		VoteID = voteID;
	}
	public String getOutID() {
		return OutID;
	}
	public void setOutID(String outID) {
		OutID = outID;
	}
	public Long getProjectID() {
		return ProjectID;
	}
	public void setProjectID(Long projectID) {
		ProjectID = projectID;
	}
	public int getChecked() {
		return Checked;
	}
	public void setChecked(int checked) {
		Checked = checked;
	}
	public String getUserIP() {
		return UserIP;
	}
	public void setUserIP(String userIP) {
		UserIP = userIP;
	}
	public int getInnerSchool() {
		return InnerSchool;
	}
	public void setInnerSchool(int innerSchool) {
		InnerSchool = innerSchool;
	}
	
}