package db.pojo;

import java.util.Date;

public class PollData
{
	private Long   PollID;
  private Date   PollTime;
	private String PollIP;
	private String ProjectID;
	private String UserID;
	private Long Innerschool;

	
	public  Long   getPollID() {
		      return PollID;
	}
	public  void   setPollID(Long pollID) {
		      PollID = pollID;
	}
	
	public  Date   getPollTime() {
		      return PollTime;
	}
	public  void   setPollTime(Date pollTime) {
	        PollTime = pollTime;
	}
	
	public  String getPollIP() {
		      return PollIP;
	}
	public  void   setPollIP(String pollIP) {
		      PollIP = pollIP;
	}
	
	public  String getProjectID() {
		      return ProjectID;
	}
	public  void   setProjectID(String projectID) {
		      ProjectID = projectID;
	}
	
	public  String getUserID() {
		      return UserID;
	}
	public  void   setUserID(String userID) {
		      UserID = userID;
	}

}