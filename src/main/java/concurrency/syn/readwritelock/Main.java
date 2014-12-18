package concurrency.syn.readwritelock;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月13日 上午10:19:47
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Main {

	public static void main(String[] args) {
		PricesInfo   pricesInfo  = new  PricesInfo();
		Reader   readers[]  = new Reader[5];
		Thread  threadReaders[] = new Thread[5];
		for(int i=0;i<5;i++){
			readers[i] = new Reader(pricesInfo);
			 threadReaders[i]= new Thread(readers[i]); 
		}
        Writer  writer = new Writer(pricesInfo);
        Thread  threadWriter = new Thread(writer);
        for(int i=0;i<5;i++){
        	threadReaders[i].start();
        }
        threadWriter.start();
	}
}
