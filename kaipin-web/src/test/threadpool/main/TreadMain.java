package test.threadpool.main;

import java.util.concurrent.ThreadPoolExecutor;

import test.threadpool.pool.MyTheadListener;
import test.threadpool.pool.MyThreadPool;

/**
 * 初始化线程池
 * @author Mr-H
 *
 */
public class TreadMain {

	public static void main(String[] args) {
		ThreadPoolExecutor pool = MyThreadPool.getPoolInstance();
		Thread listener = new  MyTheadListener();
		listener.start();//启动监听
		//pool.shutdown();
	}
}
