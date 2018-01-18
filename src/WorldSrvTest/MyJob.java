package WorldSrvTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 任务调度反射的Job
 * 
 * 开发者想要job完成什么样的功能，必须且只能由开发者自己动手来编写实现，
 * 比如demo中的MyJob，这点无容置疑。但要想让自己的job被quartz识别，
 * 就必须按照quartz的规则来办事，这个规则就是job实现类必须实现Job接口，比如MyJob就实现了Job
 * 
 * 发现在反射的时候，Job的实现类需要定义成Public的，否则就无法实例化，所以把实现类和调度程序放在同一个.java文件夹下是不可行的
 * 
 * 把Job实现类放在一个独立的java文件里，然后把class定义成public的，然后问题就解决了。
 * 
 * @author Administrator
 *
 */
public class MyJob  implements Job{
	
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(new Date()));
	}

}
