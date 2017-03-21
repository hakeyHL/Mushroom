package zxh.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import zxh.pageModel.Collector;
import zxh.quartz.MyJob;
import zxh.quartz.QuartzManager;
import zxh.service.CollectorServiceI;

import java.util.Date;
import java.util.UUID;

//import zxh.quartz.MyJob;

@Namespace("/")
@Action(value = "timerAction")
public class TimerAction<T> extends BaseAction implements Job, ModelDriven<Collector> {

    //	@Resource
//	IDataBaseManger dbm;
    Collector collector = new Collector();
    @Autowired
    private CollectorServiceI collectorService;

    // 	@Autowired
// 	public CollectorServiceI getCollectorService() {
// 		return collectorService;
// 	}
// 	public void setCollectorService(CollectorServiceI collectorService) {
// 		this.collectorService = collectorService;
// 	}
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + ": Timer doing something...");
//        ClassPathXmlApplicationContext context = ApplicationContextHelper.getGlobalContext();
//        CollectorServiceI monMileageService = (CollectorServiceI) context.getBean("collectorService");
        try {
            collector.setId(UUID.randomUUID().toString());
            collector.setCollectorName("1233333334");
            collector.setCollectorIp("192");
            Collector u = collectorService.save(collector);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String JOB_NAME = "动态任务调度";
    public static String TRIGGER_NAME = "动态任务触发器";
    public static String JOB_GROUP_NAME = "XLXXCC_JOB_GROUP";
    public static String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";

    public void timerStart() {

//    	 Json j=new Json();
//    	 try {
//// 			Collector u=collectorService.save(collector);
// 			j.setSuccess(true);
// 			j.setMsg("wwwwwww！");
//// 			j.setObj(u);
// 		} catch (Exception e) {
// 			j.setMsg("sssssssssssssss");
// 		}
// 		super.writeJson(j);
        try {
//            System.out.println(new Date()+"qqqqqqqq...");   
            System.out.println("【系统启动】开始(每1秒输出一次)...");
            QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, MyJob.class, "0/4 * * * * ?");
//            System.out.println("开始(每5秒输出一次)...");    
//            QuartzManager.addJob("ds", JOB_GROUP_NAME, "999", TRIGGER_GROUP_NAME, MyJob.class, "0/5 * * * * ?");    
//            Thread.sleep(2000);    
////            System.out.println("【修改时间】开始(每5秒输出一次)...");    
////            QuartzManager.modifyJobTime(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, "0/5 * * * * ?");    
            Thread.sleep(15000);
            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME);
            System.out.println("【移除定时】成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collector getModel() {
        return null;
    }

}
