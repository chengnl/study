package forkjoin.lesson5.canceltask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author chengnl
 * @E-mail: chengnengliang@vrvmail.com.cn
 * @date 2014年12月17日 下午9:59:19
 * @version V1.0
 * @Description: 文件夹搜索
 */
public class FolderProcessor extends RecursiveTask<String> {
	private static final long serialVersionUID = 1L;
	private String path;
	private String searchFile;
	private  TaskManager taskManager;

	public FolderProcessor(String path, String searchFile,TaskManager taskManager) {
		this.path = path;
		this.searchFile = searchFile;
		this.taskManager=taskManager;
	}

	@Override
	protected String compute() {
		System.out.printf(" %s: task start .\n", this.path);
		File file = new File(path);
		File content[] = file.listFiles();
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(
							content[i].getAbsolutePath(), searchFile,this.taskManager);
					task.fork();
					this.taskManager.addTask(task);
					String result=task.join();
					if(result!=null)
						return result;
				} else {
					if (checkFile(content[i].getName())) {
						this.taskManager.cancelTasks(this);
						System.out.printf("find  filePath =%s.\n", content[i].getAbsolutePath());
						return content[i].getAbsolutePath();
					}
				}
			}
		}
		return null;
	}

	public void  writeCancelMessage(){
		System.out.printf("%s: task cancel.\n", this.path);
	}

	private boolean checkFile(String name) {
		return name.equals(searchFile);
	}

}
