package com.common.rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddServerImpl extends UnicastRemoteObject implements AddServer { 
	private static final long serialVersionUID = 1L;
	public AddServerImpl() throws RemoteException { 
		super(); 
	} 
	public int AddNumbers(int firstnumber,int secondnumber) throws RemoteException { 
		
		//TODO
		//推送
		
		/**
		 * 1 先和spring  整合
		 */
		return firstnumber + secondnumber; 
		
		
	} 
}

