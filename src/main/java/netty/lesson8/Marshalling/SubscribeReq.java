package netty.lesson8.Marshalling;

import java.io.Serializable;

/**
 *@author chengnl
 *@date 2015年2月21日 下午11:02:09
 *@version 1.0
 *@Description:订阅请求
 */
public class SubscribeReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userID;
	private String userName;
	private String phoneNumber;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String toString(){
		return "SubscribeReq[userID="+this.userID+",userName="+this.userName+",phoneNumber="+this.phoneNumber+"]";
	}

}
