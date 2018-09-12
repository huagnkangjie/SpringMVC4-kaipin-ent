package test.threadpool.pool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

import com.util.StringUtil;

/**
 * 1、有新的任务，开一个新的线程
 * 2、监听到有任务需要断开，则销毁对应的任务对象
 * 
 * @author Mr-H
 *
 */
public class MyTheadListener extends Thread{
	
	private String fileName="/test/threadpool/pool/task.properties";
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(true){
			try {
				ThreadPoolExecutor pool = MyThreadPool.getPoolInstance();
				int activeCount = pool.getActiveCount();
				System.out.println("系统当前存活线程数   ==>" + activeCount );
				int count = Integer.valueOf(getValue("task.count"));
				System.out.println("需要创建的线程数   ==>" + count );
				if(activeCount < count){
					for (int i = 0; i < (count - activeCount); i++) {
						String taskId = StringUtil.getUuid();
						MyThreadTask task = new MyThreadTask(taskId+"Flag");
						pool.execute(task);//往线程池中添加任务
						MyTaskCache.getMyCacheInstance().addCahce(taskId, task);
					}
				}
				Thread.sleep(1000 * 10);
				//检查是否有线程需要关闭
//				String param[] = MyTaskCache.getMyCacheInstance().getAllKeys();
//				for (int i = 0; i < param.length; i++) {
//					
//				}
				String closeId = getValue("task.close");
				MyThreadTask o = (MyThreadTask) MyTaskCache.getMyCacheInstance().getCahce(closeId);
				if(o != null){
					System.out.println("需要关闭线程  id = " + closeId);
					o.destroy();
				}
			} catch (Exception e) {
				System.out.println("game over !");
				MyThreadPool.getPoolInstance().shutdown();
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 获取prop文件中的属性值
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getValue(String key) throws IOException{
		InputStream is=MyTheadListener.class.getResourceAsStream(fileName);
		BufferedReader br= new BufferedReader(new InputStreamReader(is));
		Properties props = new Properties();
		props.load(br);
		String value = String.valueOf(props.get(key));
		is.close();
		br.close();
		return value;
	}

	
}
