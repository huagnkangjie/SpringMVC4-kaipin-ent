package com.kaipin.search.manager;

import com.kaipin.search.core.index.IndexTask;
import com.kaipin.search.core.index.Searchable;
import com.kaipin.search.repository.dao.IndexBuildDao;

public class IndexUpdaterTask implements IndexTask {

	private byte opt;
	private String id;
	
	private String taskId;
	
	private IndexBuildDao dao;
	
	
	public  IndexUpdaterTask(byte opt,String id,String taskId,IndexBuildDao dao){
		this.opt=opt;
		this.id=id;
		this.taskId=taskId;
		this.dao=dao;
	}
	
	@Override
	public byte getOpt() {
		return opt;
	}

	@Override
	public Searchable object() {
		 
		
		
		
		return null;
	}

	@Override
	public void afterBuild() {
 
		
	}

}
