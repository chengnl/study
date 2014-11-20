package syntool.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月20日 下午2:29:52
 *@version 1.0
 *@Description:日志模拟
 */
public class LogMock {
       private  LogFile files[];
       private   int userNum;
       public LogMock(int fileNum,int userNum){
    	   this.files=new LogFile[fileNum];
    	   this.userNum=userNum;
    	   creatFile();
       }
      private void creatFile(){
    	  for(int i=0;i<this.files.length;i++){
    		  LogFile  file = new LogFile("file"+i);
    		  for(int j=0;j<100;j++){
    			  Random random = new Random();
    			  String userID ="userID"+ random.nextInt(this.userNum);
    			  file.getFileContent().add(userID);
    		  }
    		  files[i]=file;
    	  }
      }
      public LogFile[] getFiles(){
    	  return this.files;
      }
      
     public class LogFile{
    	private List<String> fileContent;
    	private  String fileName;
    	public LogFile(String fileName){
    		this.fileName=fileName;
    		this.fileContent= new ArrayList<String>();
    	}
		public List<String> getFileContent() {
			return fileContent;
		}
		public String getFileName() {
			return fileName;
		}
      }
}
