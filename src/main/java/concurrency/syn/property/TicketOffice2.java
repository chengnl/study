package concurrency.syn.property;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午9:39:35
 *@version 1.0
 *@Description:TODO 类描述
 */
public class TicketOffice2 implements Runnable{
    private Cinema cinema;
    public TicketOffice2(Cinema cinema){
    	this.cinema=cinema;
    }
	@Override
	public void run() {
		this.cinema.sellTIckets2(2);
		this.cinema.sellTIckets2(4);
		this.cinema.sellTIckets1(2);
		this.cinema.sellTIckets1(1);
		this.cinema.returnTickets2(2);
		this.cinema.sellTIckets1(3);
		this.cinema.sellTIckets2(2);
		this.cinema.sellTIckets1(2);
	}

}
