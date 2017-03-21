package zxh.quartz;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
public class CountDays extends QuartzJobBean{
	
	public void job() {
		System.out.println("CountDays:" + new Date().toString());
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
