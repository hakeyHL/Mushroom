package zxh.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import zxh.action.BaseAction;
import zxh.pageModel.Collector;
import zxh.service.CollectorServiceI;

import java.util.Date;
import java.util.UUID;

//
//public class MyJob {
//
//	public void work() {
//		System.out.println("date:" + new Date().toString());
//	}
//}
public class MyJob extends BaseAction implements Job {
    Collector collector = new Collector();

    public void execute(JobExecutionContext context) throws JobExecutionException {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        CollectorServiceI collectorService = (CollectorServiceI) webApplicationContext.getBean("collectorService");
        System.out.println(new Date() + ": myjob doing something...");
//        Date nextProcessTime = context.getNextFireTime();  
//        context.getJobDetail().getJobDataMap().get("scheduleJob");  
//        job.setNextProcessTime(nextProcessTime);  
//        jobTaskService.updateTaskByJobName(job);  
        try {
            collector.setId(UUID.randomUUID().toString());
            collector.setCollectorName("1233333334");
            collector.setCollectorIp("192");
            Collector u = collectorService.save(collector);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
