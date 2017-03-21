package zxh.test;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;


import zxh.quartz.MulCollect;
public class TestSpring {
	public void run() throws Exception {
	    

		System.out.println("------- Initializing ----------------------");

	    // 1、工厂模式 构建Scheduler的Factory，其中STD为Quartz默认的Factory，开发者亦可自行实现自己的Factory;Job、Trigger等组件
	    SchedulerFactory sf = new StdSchedulerFactory();
	    System.out.println("------- Initializing ----------------------");
	    // 2、通过SchedulerFactory获得Scheduler对象
	    Scheduler sched = sf.getScheduler();

	    System.out.println("------- Initialization Complete -----------");

	    // 3、org.quartz.DateBuilder.evenMinuteDate <下一分钟>  -- 通过DateBuilder构建Date
	    Date runTime = evenMinuteDate(new Date());

	    System.out.println("------- Scheduling Job  -------------------");

	    // 4、org.quartz.JobBuilder.newJob --通过JobBuilder构建Job
	    JobDetail job = newJob(MulCollect.class).withIdentity("job1", "group1").build();

	    // 5、通过TriggerBuilder进行构建
	    Trigger trigger = newTrigger().withIdentity("trigger1" , "group1").startAt(runTime ).build();

	    // 6、工厂模式，组装各个组件<JOB，Trigger>
	    sched.scheduleJob(job, trigger);
	   
	    // [group1.job1] will run at:
	    System.out.println(job.getKey() + " will run at: " + runTime);

	    // 7、start
	    sched.start();

	    System.out.println("------- Started Scheduler -----------------");

	    System.out.println("------- Waiting 5 seconds... -------------");
	    try {
	      // wait 65 seconds to show job
	      Thread.sleep(5L * 1000L);
	      // executing...
	    } catch (Exception e) {
	      //
	    }

	    // shut down the scheduler
	    System.out.println("------- Shutting Down ---------------------");
	    // 8、通过Scheduler销毁内置的Trigger和Job
	    sched.shutdown(true);
	    System.out.println("------- Shutdown Complete -----------------");
	  }
	@Test
	public void test() throws Exception{
		/**
		 * 此Demo将演示如何启动和关闭Quartz调度器，以及如何运作
		 * @author weeks
		 *
		 */
		    run();

		  }

		
		
		
//		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{ "classpath:spring.xml"});
//		UserServiceI userService=(UserServiceI)ac.getBean("userService");
//		userService.test();	

}
