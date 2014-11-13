package syn.readwritelock;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月13日 上午10:12:32
 *@version 1.0
 *@Description:读线程
 */
public class Reader implements Runnable{
      private PricesInfo pricesInfo;
      public Reader (PricesInfo pricesInfo){
    	  this.pricesInfo=pricesInfo;
      }
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			this.pricesInfo.getPrice1();
			this.pricesInfo.getPrice2();
		}
	}
      
}
