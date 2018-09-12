package test.threadpool.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

	public static ThreadPoolExecutor executor = null;
	public static MyThreadPool pool;
	public int CORE_POOL_SIZE = 10 ;
	public int MAXINUM_POO_SIZE = 15; 
	public int KEEP_ALIVE_TIME = 60;
	public int WORK_QUEUE = 10 ;
	public boolean state;
	
	/**
	 * 初始化线程池
	 */
	public MyThreadPool(){
		if(pool == null){
			executor =  new ThreadPoolExecutor(CORE_POOL_SIZE, MAXINUM_POO_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
	                new ArrayBlockingQueue<Runnable>(WORK_QUEUE));
		}
		
	}
	
	public static ThreadPoolExecutor getPoolInstance(){
		if(pool == null){
			pool = new MyThreadPool();
		}
		return executor;
	}
	
	
}
