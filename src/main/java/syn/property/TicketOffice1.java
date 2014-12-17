package syn.property;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午9:39:35
 *@version 1.0
 *@Description:TODO 类描述
 */
public class TicketOffice1 implements Runnable{
    private Cinema cinema;
    public TicketOffice1(Cinema cinema){
    	this.cinema=cinema;
    }
	@Override
	public void run() {
		this.cinema.sellTIckets1(3);
		this.cinema.sellTIckets1(2);
		this.cinema.sellTIckets2(2);
		this.cinema.returnTickets1(3);
		this.cinema.sellTIckets1(5);
		this.cinema.sellTIckets2(2);
		this.cinema.sellTIckets2(2);
		this.cinema.sellTIckets2(2);
	}

}
