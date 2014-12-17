package syntool.phaser;

import java.util.concurrent.Phaser;

/**
 * @author chengnl
 * @E-mail:chengnengliang@vrvmail.com.cn
 * @date 2014年11月21日 下午4:11:09
 * @version 1.0
 * @Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);

		FileSearcher server_online = new FileSearcher(
				"/home/chengnl/workspace/eclipse/workspace/server-online",
				"java", phaser);
		FileSearcher server_iosnotice = new FileSearcher(
				"/home/chengnl/workspace/eclipse/workspace/server-iosnotice",
				"java", phaser);
		FileSearcher server_login = new FileSearcher(
				"/home/chengnl/workspace/eclipse/workspace/server-login",
				"java", phaser);

		Thread onlineThread = new Thread(server_online, "server_online");
		onlineThread.start();
		Thread iosnoticeThread = new Thread(server_iosnotice, "server_iosnotice");
		iosnoticeThread.start();
		Thread loginThread = new Thread(server_login, "server_login");
		loginThread.start();
		try {
			onlineThread.join();
			iosnoticeThread.join();
			loginThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 31. 使用isFinalized()方法把Phaser对象的结束标志值写入操控台。
		System.out.println("Terminated: " + phaser.isTerminated());

	}

}
