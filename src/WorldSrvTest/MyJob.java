package WorldSrvTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * ������ȷ����Job
 * 
 * ��������Ҫjob���ʲô���Ĺ��ܣ�������ֻ���ɿ������Լ���������дʵ�֣�
 * ����demo�е�MyJob������������ɡ���Ҫ�����Լ���job��quartzʶ��
 * �ͱ��밴��quartz�Ĺ��������£�����������jobʵ�������ʵ��Job�ӿڣ�����MyJob��ʵ����Job
 * 
 * �����ڷ����ʱ��Job��ʵ������Ҫ�����Public�ģ�������޷�ʵ���������԰�ʵ����͵��ȳ������ͬһ��.java�ļ������ǲ����е�
 * 
 * ��Jobʵ�������һ��������java�ļ��Ȼ���class�����public�ģ�Ȼ������ͽ���ˡ�
 * 
 * @author LiZhenhua
 *
 */
public class MyJob  implements Job{
	
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(new Date()));
	}

}
