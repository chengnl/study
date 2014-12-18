package concurrency.forkjoin.lesson5.canceltask;

import java.util.ArrayList;
import java.util.List;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月18日 下午3:05:32
 *@version 1.0
 *@Description:任务管理
 */
public class TaskManager {
      private  List<FolderProcessor> tasks;
      public  TaskManager(){
    	  tasks=new ArrayList<FolderProcessor>();
      }
      
      public void addTask(FolderProcessor task){
    	  tasks.add(task);
      }
      public  void  cancelTasks(FolderProcessor task){
    	  for(FolderProcessor folderProcessor:tasks){
    		  if(task!=folderProcessor)
    			  folderProcessor.cancel(true);
    		  	  folderProcessor.writeCancelMessage();
    	  }
      }
}
