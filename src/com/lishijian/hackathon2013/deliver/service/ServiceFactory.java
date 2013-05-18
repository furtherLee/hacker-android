package com.lishijian.hackathon2013.deliver.service;

public class ServiceFactory {
	
	private static IDeliverService instance;
	
	public synchronized static IDeliverService buildService() {
		if (instance == null)
			instance = new LocalDeliverService();
		return instance;
	}
}
