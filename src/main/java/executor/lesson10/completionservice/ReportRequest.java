package executor.lesson10.completionservice;

import java.util.concurrent.CompletionService;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年12月5日 上午9:55:15
 *@version 1.0
 *@Description:报告请求
 */
public class ReportRequest implements Runnable {
    private String name;
    private CompletionService<String> service;
    public ReportRequest(String name,CompletionService<String> service) {
		this.name=name;
		this.service=service;
	}
	@Override
	public void run() {
		ReportGenerator  reportGenerator = new ReportGenerator(name,"report");
		this.service.submit(reportGenerator);
	}

}
