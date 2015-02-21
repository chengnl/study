package netty.lesson6.Serializable;

import java.io.Serializable;

/**
 *@author chengnl
 *@date 2015年2月21日 下午11:05:55
 *@version 1.0
 *@Description:订阅响应
 */
public class SubscribeResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userID;
	
	private int respCode;
	
	private String desc;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String toString(){
		return "SubscribeResp[userID="+this.userID+",respCode="+this.respCode+",desc="+this.desc+"]";
	}

}
