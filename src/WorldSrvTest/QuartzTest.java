package WorldSrvTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


/**
 * Quartz调度测试
 * 
 * Quartz是一个开源的任务调度框架。
 * 基于定时、定期的策略来执行任务是它的核心功能，
 * 比如x年x月的每个星期五上午8点到9点，每隔10分钟执行1次。
 * Quartz有3个核心要素：调度器(Scheduler)、任务(Job)、触发器(Trigger)。
 * Quartz完全使用Java开发，可以集成到各种规模的应用程序中。
 * 它能够承载成千上万的任务调度，并且支持集群。
 * 它支持将数据存储到数据库中以实现持久化，并支持绝大多数的数据库。
 * 它将任务与触发设计为松耦合，即一个任务可以对应多个触发器，这样能够轻松构造出极为复杂的触发策略。
 * @author Administrator
 *
 */
public class QuartzTest {


	public static void main(String[] args) {
		// 1、创建JobDetial对象
//		JobDetailImpl jobDetail = new JobDetailImpl();
//		jobDetail.setName("MyJobDetail");
//		jobDetail.setJobClass(MyJob.class);
		
		
		/*
		 * JobDetailImpl类实现了JobDetail接口，用来描述一个job，定义了job所有属性及其get/set方法。
		 * 了解job拥有哪些属性，就能知道quartz能提供什么样的能力
		 * 
		 * .build()方法其实就是实例化了一个JobDetailImpl对象
		 */
	
		
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        
		// 2、创建Trigger对象
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(3).repeatForever())
				.build();


		// 3、创建Scheduler对象，并配置JobDetail和Trigger对象
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			scheduler = sf.getScheduler();
			scheduler.scheduleJob(jobDetail, trigger);
			// 4、并执行启动、关闭等操作
			scheduler.start();

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		// try {
		// //关闭调度器
		// scheduler.shutdown(true);
		// } catch (SchedulerException e) {
		// e.printStackTrace();
		// }
	}

}
