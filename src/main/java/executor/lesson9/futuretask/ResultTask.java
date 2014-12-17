package executor.lesson9.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月4日 下午3:21:37
 *@version 1.0
 *@Description:FutureTask
 */
public class ResultTask extends FutureTask<String>{
	private String name;
	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name=((Task)callable).getName();
	}

	@Override
	protected void done() {
		if (isCancelled()) {
		System.out.printf("%s: Has been canceled\n",name);
		} else {
		System.out.printf("%s: Has finished\n",name);
		}
	}
}
