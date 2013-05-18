package com.lishijian.hackathon2013.deliver.service;

import java.util.List;

import com.lishijian.hackathon2013.deliver.entity.Order;

public class InternetDeliverService implements IDeliverService {

	@Override
	public int login(String id, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> pullOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean confirm(int orderId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancel(int orderId) {
		// TODO Auto-generated method stub
		return false;
	}

}
