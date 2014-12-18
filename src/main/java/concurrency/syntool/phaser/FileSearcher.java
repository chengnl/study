package concurrency.syntool.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月21日 下午3:47:00
 *@version 1.0
 *@Description:文件搜索
 */
public class FileSearcher implements Runnable{
    private String end;
    private String filePath;
    private List<String> results;
    private Phaser  phaser;
    public FileSearcher(String filePath,String end,Phaser  phaser){
    	this.filePath=filePath;
    	this.end=end;
    	this.phaser=phaser;
    	this.results=new ArrayList<String>();
    }
    private void searchFiles(String searchFilePath){
    	File file = new File(searchFilePath);
    	if(file.exists()){
    		if(file.isDirectory()){
    			File[] files=file.listFiles();
    			if(files!=null){
    				for(int i=0;i<files.length;i++){
    					File  childFile = files[i];
    					if(childFile.isDirectory())
    						searchFiles(childFile.getAbsolutePath());
    					else
    						fileProcess(childFile);
    				}
    			}
    		}else{
    			fileProcess(file);
    		}
    	}
    }
    private void fileProcess(File file){
    	String fileName = file.getName();
    	if(fileName.endsWith(this.end)){
    		this.results.add(file.getAbsolutePath());
    	}
    }
    private void filterFiles(){
    	List<String> newResults = new ArrayList<String>();
    	long actualDate = new Date().getTime();
    	for(String filePath:this.results){
    		File file = new File(filePath);
    		long fileDate = file.lastModified();
             // 11. 然后， 对比与真实日期对比，如果相差小于3天，把文件的路径加入到新的结果列表。
    	     if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(3,TimeUnit.DAYS)) {
                            newResults.add(filePath);
    	     }
    	}
    	this.results=newResults;
    }
    private boolean checkResults(){
    	if(this.results.isEmpty()){
    		System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), 
    				phaser.getPhase());
    		System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), 
    				phaser.getPhase());
    		 // arriveAndDeregister() 方法通知此线程已经结束actual phase，并离开phased操作。
    		this.phaser.arriveAndDeregister();
    		return false;
    	}else{
    		 System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(),
    				 phaser.getPhase(), results.size());
    		 //arriveAndAwaitAdvance() 方法并通知 actual phase，它会被阻塞直到phased
    		// 操作的全部参与线程结束actual phase。
    		this.phaser.arriveAndAwaitAdvance();
    		return true;
    	}
    }
    private void showInfo(){
    	for (int i = 0; i < results.size(); i++) {
    		File file = new File(results.get(i));
            System.out.printf("%s: %s\n", Thread.currentThread().getName(),file.getAbsolutePath());
    	}
    	phaser.arriveAndAwaitAdvance();
    }
    
	@Override
	public void run() {
		this.phaser.arriveAndAwaitAdvance();//等待同时开始
		System.out.printf("%s: Starting.\n", Thread.currentThread().getName());
		searchFiles(this.filePath);
		if(!checkResults())
			return;
		filterFiles();
		if(!checkResults())
			return;
		showInfo();
		phaser.arriveAndDeregister();//表示结束
		System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
		
		
		
	}

}
