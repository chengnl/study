package concurrency.syn.property;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午9:31:22
 *@version 1.0
 *@Description:不同属性同步
 */
public class Cinema {
     private long vacanciesCinema1;
     private long vacanciesCinema2;
     private final Object controlCinema1,controlCinema2;
     public Cinema(){
    	 controlCinema1=new Object();
    	 controlCinema2=new Object();
    	 vacanciesCinema1=20;
    	 vacanciesCinema2=20;
     }
     public boolean sellTIckets1(int num){
    	 synchronized(controlCinema1){
    		 if(num<vacanciesCinema1){
    			 vacanciesCinema1-=num;
    			 return true;
    		 }else{
    			 return false;
    		 }
    	 }
     }
     public boolean sellTIckets2(int num){
    	 synchronized(controlCinema2){
    		 if(num<vacanciesCinema2){
    			 vacanciesCinema2-=num;
    			 return true;
    		 }else{
    			 return false;
    		 }
    	 }
     }
     public boolean returnTickets1(int num){
    	 synchronized(controlCinema1){
			 vacanciesCinema1+=num;
			 return true;
    	 }
     }
     public boolean returnTickets2(int num){
    	 synchronized(controlCinema2){
			 vacanciesCinema2+=num;
			 return true;
    	 }
     }
	public long getVacanciesCinema1() {
		return vacanciesCinema1;
	}
	public void setVacanciesCinema1(long vacanciesCinema1) {
		this.vacanciesCinema1 = vacanciesCinema1;
	}
	public long getVacanciesCinema2() {
		return vacanciesCinema2;
	}
	public void setVacanciesCinema2(long vacanciesCinema2) {
		this.vacanciesCinema2 = vacanciesCinema2;
	}
     
     
}
