package syntool.cyclicbarrier;

import java.util.Map;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月20日 下午2:54:00
 *@version 1.0
 *@Description:结果
 */
public class Result {
     private String  fileName;
     private Map<String,Integer> result;
	 public String getFileName() {
		return fileName;
	 }
	 public void setFileName(String fileName) {
		this.fileName = fileName;
	 }
	 public Map<String, Integer> getResult() {
		return result;
	 }
	 public void setResult(Map<String, Integer> result) {
		this.result = result;
	 }
      
}
