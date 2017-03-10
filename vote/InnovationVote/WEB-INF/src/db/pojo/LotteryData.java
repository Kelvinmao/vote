package db.pojo;

import java.util.Date;

public class LotteryData
{
	private Long   LotteryID;
  private Date   LotteryTime;
	private String LotteryIP;
	private Long   UserPrize;
	private String UserID;
	private String UserName;
	private String UserAddress;
	private String UserTel;

	
	public  Long   getLotteryID() {
		      return LotteryID;
	}
	public  void   setLotteryID(Long lotteryID) {
		      LotteryID = lotteryID;
	}
	
	public  Date   getLotteryTime() {
		      return LotteryTime;
	}
	public  void   setLotteryTime(Date lotteryTime) {
	        LotteryTime =lotteryTime;
	}
	
	public  String getLotteryIP() {
		      return LotteryIP;
	}
	public  void   setLotteryIP(String lotteryIP) {
		      LotteryIP =lotteryIP;
	}
	
	public  Long   getUserPrize() {
		      return UserPrize;
	}
	public  void   setUserPrize(Long userPrize) {
		      UserPrize = userPrize;
	}
	
	public  String getUserID() {
		      return UserID;
	}
	public  void   setUserID(String userID) {
		      UserID = userID;
	}
	
	public  String getUserName()
	{
		      return UserName;
	}
	
	public  void   setUserName(String userName) {
		      UserName = userName;
	}
			
	
	public  String getUserAddress() {
		      return UserAddress;
	}
	public  void   setUserAddress(String userAddress) {
		      UserAddress = userAddress;
	}
	
	public  String getUserTel() {
		      return UserTel;
	}
	public  void   setUserTel(String userTel) {
		      UserTel = userTel;
	}
	
}