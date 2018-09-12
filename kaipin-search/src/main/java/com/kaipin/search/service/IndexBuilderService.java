package com.kaipin.search.service;

import java.io.IOException;

import com.kaipin.search.service.impl.IndexBuilderServiceImpl.IndexCount;

public interface IndexBuilderService {



	public int buildPosition() throws IOException;

	public int buildCompany() throws IOException ;

	public int buildLive() throws IOException;
	
	public int buildStuUser() throws IOException;
	
	public int buildSchInfo() throws IOException;

	public IndexCount  buildAll() throws IOException;
	
	public IndexCount reBuildAll() throws IOException;
	
	

}
