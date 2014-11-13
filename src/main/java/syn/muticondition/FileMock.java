package syn.muticondition;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月13日 下午4:30:26
 *@version 1.0
 *@Description:模拟文件
 */
public class FileMock {
      private String  content[];
      private int index;
      public FileMock(int size , int length){
    	  content= new String[size];
    	  for(int i=0;i<size;i++){
    		  StringBuilder  buffer = new StringBuilder(length);
    		  for(int j=0;j<length;j++){
    			  int indice=(int)Math.random()*255;
    			  buffer.append((char)indice);
    		  }
    		  content[i]=buffer.toString();
    	  }
    	  index=0;
      }
      public boolean hasMoreLines(){
    	  return index<content.length;
      }
      public String getLine(){
    	  if(this.hasMoreLines()){
    		  System.out.println("Mock: "+(content.length-index));
    		  return content[index++];
    	  }
    	  return null;
      }
}
