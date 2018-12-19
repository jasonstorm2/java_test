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
 * Quartz���Ȳ���
 * 
 * Quartz��һ����Դ��������ȿ�ܡ�
 * ���ڶ�ʱ�����ڵĲ�����ִ�����������ĺ��Ĺ��ܣ�
 * ����x��x�µ�ÿ������������8�㵽9�㣬ÿ��10����ִ��1�Ρ�
 * Quartz��3������Ҫ�أ�������(Scheduler)������(Job)��������(Trigger)��
 * Quartz��ȫʹ��Java���������Լ��ɵ����ֹ�ģ��Ӧ�ó����С�
 * ���ܹ����س�ǧ�����������ȣ�����֧�ּ�Ⱥ��
 * ��֧�ֽ����ݴ洢�����ݿ�����ʵ�ֳ־û�����֧�־�����������ݿ⡣
 * ���������봥�����Ϊ����ϣ���һ��������Զ�Ӧ����������������ܹ����ɹ������Ϊ���ӵĴ������ԡ�
 * @author LiZhenhua
 *
 */
public class QuartzTest {


	public static void main(String[] args) {
		// 1������JobDetial����
//		JobDetailImpl jobDetail = new JobDetailImpl();
//		jobDetail.setName("MyJobDetail");
//		jobDetail.setJobClass(MyJob.class);
		
		
		/*
		 * JobDetailImpl��ʵ����JobDetail�ӿڣ���������һ��job��������job�������Լ���get/set������
		 * �˽�jobӵ����Щ���ԣ�����֪��quartz���ṩʲô��������
		 * 
		 * .build()������ʵ����ʵ������һ��JobDetailImpl����
		 */
	
		
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        
		// 2������Trigger����
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(3).repeatForever())
				.build();


		// 3������Scheduler���󣬲�����JobDetail��Trigger����
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			scheduler = sf.getScheduler();
			scheduler.scheduleJob(jobDetail, trigger);
			// 4����ִ���������رյȲ���
			scheduler.start();

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		// try {
		// //�رյ�����
		// scheduler.shutdown(true);
		// } catch (SchedulerException e) {
		// e.printStackTrace();
		// }
	}

}
