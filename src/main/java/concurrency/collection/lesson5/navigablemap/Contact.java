package concurrency.collection.lesson5.navigablemap;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月25日 上午9:39:20
 *@version 1.0
 *@Description:联系人
 */
public class Contact {
      private String name;
      private String phone;
      public Contact(String name,String phone) {
	        this.name=name;
	        this.phone=phone;
      }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
      
}
