package syntool.phaser.change;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月1日 上午11:50:57
 *@version 1.0
 *@Description:测试
 */
public class Main {

	public static void main(String[] args) {
		MyPhaser  phaser  = new MyPhaser();
		
		Thread[]  threads = new Thread[5];
		for(int i=0;i<threads.length;i++){
			MyPhaser.Student  student =  phaser.new Student(phaser);
			Thread  studentThread = new Thread(student,"student "+i);
			threads[i]=studentThread;
			phaser.register();
		}
		for(int i=0;i<threads.length;i++){
			threads[i].start();
		}
		
		for(int i=0;i<threads.length;i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main: the phaser has finished .");
	}

}
