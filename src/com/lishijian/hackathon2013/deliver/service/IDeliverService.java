package com.lishijian.hackathon2013.deliver.service;

import java.util.List;

import com.lishijian.hackathon2013.deliver.entity.Order;

public interface IDeliverService {

	public static final int FAILED_ID = -1;
	
	/**
	 *  Login to service
	 * @param id
	 * @param password
	 * @return user id
	 */
	public int login(String id, String password);
	
	/**
	 * Pull order groups from server
	 * @return
	 */
	public List<Order> pullOrders();
	
	/**
	 * Confirm an order
	 * @param orderId
	 * @return whether operation succeeds
	 */
	public boolean confirm(int orderId);
	
	/**
	 * Cancel an order
	 * @param orderId
	 * @return whether operation succeed
	 */
	public boolean cancel(int orderId);
}
