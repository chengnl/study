package reflection.lesson4.field;
/**
 *@author chengnl
 *@date 2015年2月7日 上午10:38:20
 *@version 1.0
 *@Description:演示实例
 */
public class Book {
   private String name;
   private String author;
   public  String pub; 
   public static String pubDate;
   public Book() {
     this.name="名字：构造空";
     this.author="作者：构造空";
   }
   public Book(String name){
	   this.name=name;
	   this.author="作者：构造空";
   }
   public Book(String name,String author){
	   this.name=name;
	   this.author=author;
   }
   
   public String toString(){
	   return "[book:name="+this.name+";author="+this.author+";pub="+this.pub+";"
	   		+ "pubDate="+this.pubDate+"]";
   }
}
