package zxh.quartz;

import java.util.Date;

//
//public class MyJob {
//
//	public void work() {
//		System.out.println("date:" + new Date().toString());
//	}
//}
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import zxh.action.BaseAction;
import zxh.pageModel.Json;

public class DaysJob extends BaseAction implements Job{

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + ": daysjob doing something...");
        Json j=new Json();
        try {
// 			Collector u=collectorService.save(collector);
 			j.setSuccess(true);
 			j.setMsg("days！");
// 			j.setObj(u);
 		} catch (Exception e) {
 			j.setMsg("daysex");
 		}
 		super.writeJson(j);
    }
}
