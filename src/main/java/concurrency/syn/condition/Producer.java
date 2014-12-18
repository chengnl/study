package concurrency.syn.condition;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午10:51:38
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Producer implements Runnable{
    private EventStorage storage;
    public  Producer(EventStorage storage){
    	this.storage=storage;
    }
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			storage.set();
		}
	}

}
