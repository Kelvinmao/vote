package db.pojo;

import java.util.Date;

public class UserData
{
	private String UserID;
	private String UserName;
	private String CardID;
	private String UserPassword;
	private Long   PollNumber;
	private Date   UserDatetime;
	private Long   UserLottery;
	private Long   UserPrize;
	private String UserTel;
	private String UserAddress;
	private Long   IsBUPT;
	private Long 	 LotteryNum;
	
	public  String getUserID() {
		      return UserID;
	}
	public  void   setUserID(String userID) {
		      UserID = userID;
	}

	public  String getUserName() {
		      return UserName;
	}
	public  void   setUserName(String userName) {
		      UserName = userName;
	}
	
	public  String getCardID() {
		      return CardID;
	}
	public  void   setCardID(String cardID) {
		      CardID = cardID;
	}
	
	public  String getUserPassword() {
		      return UserPassword;
	}
	public  void   setUserPassword(String userPassword) {
		      UserPassword = userPassword;
	}
	
	public  Long   getPollNumber() {
		      return PollNumber;
	}
	public  void   setPollNumber(Long pollNumber) {
		      PollNumber = pollNumber;
	}
	
	public  Date   getUserDatetime() {
		      return UserDatetime;
	}
	public  void   setUserDatetime(Date userDatetime) {
		      UserDatetime = userDatetime;
	}
	
	public  Long   getUserLottery() {
		      return UserLottery;
	}
	public  void   setUserLottery(Long userLottery) {
		      UserLottery = userLottery;
	}
	
	public  Long   getUserPrize() {
		      return UserPrize;
	}
	public  void   setUserPrize(Long userPrize) {
		      UserPrize = userPrize;
  }
  
  public  String getUserTel() {
		      return UserTel;
	}
	public  void   setUserTel(String userTel) {
		      UserTel = userTel;
	}
	
	public  String getUserAddress() {
		      return UserAddress;
	}
	public  void   setUserAddress(String userAddress) {
		      UserAddress = userAddress;
	}
	
	public  Long   getIsBUPT() {
		      return IsBUPT;
	}
	public  void   setIsBUPT(Long isBUPT) {
		      IsBUPT = isBUPT;
	}
	
	public Long getLotteryNum() {
		return LotteryNum;
	}
	public void setLotteryNum(Long lotteryNum) {
		LotteryNum=lotteryNum;
	}
}