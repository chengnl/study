package syntool.countdownlatch.waittodo;


/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月19日 上午11:04:08
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		int persons =10;
		Meet   meet  = new Meet(persons);
		Thread  meetThread = new Thread(meet);
		meetThread.start();
		
		for(int i=0;i<persons;i++){
			Person  person = new Person(meet,i);
			Thread  personThread = new Thread(person);
			personThread.start();
		}
	}

}
