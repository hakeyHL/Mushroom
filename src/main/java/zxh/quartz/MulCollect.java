package zxh.quartz;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MulCollect extends QuartzJobBean{
	
	public void job() {
		System.out.println("MulCollect:" + new Date().toString());
	}
	
	
	private MyTask myTask;

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		myTask.run();
	}

	public MyTask getMyTask() {
		return myTask;
	}

	public void setMyTask(MyTask myTask) {
		this.myTask = myTask;
	}
}
