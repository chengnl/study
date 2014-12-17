package syn.muticondition;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年11月7日 上午10:51:38
 *@version 1.0
 *@Description:TODO 类描述
 */
public class Producer implements Runnable{
    private FileMock fileMock;
    private  Buffer  buffer;
    public  Producer(FileMock fileMock,Buffer  buffer){
    	this.fileMock=fileMock;
    	this.buffer=buffer;
    }
	@Override
	public void run() {
		while(this.fileMock.hasMoreLines()){
			buffer.insert(this.fileMock.getLine());
		}
	}

}
