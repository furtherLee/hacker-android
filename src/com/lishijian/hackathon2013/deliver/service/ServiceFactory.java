package com.lishijian.hackathon2013.deliver.service;

public class ServiceFactory {
	
	public static IDeliverService buildService() {
		return new LocalDeliverService();
	}
}
