package reflection.lesson6.getset;
/**
 *@author chengnl
 *@date 2015年2月7日 上午11:40:42
 *@version 1.0
 *@Description:演示
 */
public class Student {
    private String lastName;
    private String firstName;
    private int age;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	private int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
    
	public String getFullname(){
		return this.firstName+this.lastName;
	}
}
