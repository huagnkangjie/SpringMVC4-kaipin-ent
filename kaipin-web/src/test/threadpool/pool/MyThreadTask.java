package test.threadpool.pool;

public class MyThreadTask extends Thread{
	
	private String taskId;
	
	public MyThreadTask(String taskId){
		this.taskId = taskId;
	}
	
	@Override
	public void run() {
		while(true){
			System.out.println("执行任务 id = " + taskId);
			try {
				Thread.sleep(1000 * 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Object o = MyTaskCache.getMyCacheInstance().getCahce(taskId + "Flag");
			if( o != null && String.valueOf(o).equals("false")){
				System.out.println("监控到要停止 任务id = " + taskId);
				break;
			}
		}
	}

}
