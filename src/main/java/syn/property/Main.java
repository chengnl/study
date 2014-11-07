package syn.property;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午9:43:46
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {
      public static void main(String[] args) throws InterruptedException{
    	  Cinema  cinema = new Cinema();
    	  TicketOffice1  ticketOffice1 = new TicketOffice1(cinema);
    	  Thread  thread1 = new Thread(ticketOffice1);
    	  TicketOffice2  ticketOffice2 = new TicketOffice2(cinema);
    	  Thread  thread2 = new Thread(ticketOffice2);
    	  
    	  thread1.start();
    	  thread2.start();
    	  
    	  thread1.join();
    	  thread2.join();
    	  
    	  System.out.printf("room 1 vacancies :%d\n", cinema.getVacanciesCinema1());
    	  System.out.printf("room 1 vacancies :%d\n", cinema.getVacanciesCinema2());
      }
}
