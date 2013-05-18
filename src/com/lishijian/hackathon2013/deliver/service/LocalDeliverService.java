package com.lishijian.hackathon2013.deliver.service;

import java.util.ArrayList;
import java.util.List;

import com.lishijian.hackathon2013.deliver.entity.Order;

public class LocalDeliverService implements IDeliverService {

	List<Order> orders;
	
	public LocalDeliverService () {
		orders = new ArrayList<Order>();
		
		Order order1 = new Order();
		order1.id = 0;
		order1.gid = 0;
		order1.eaterName = "李诗剑";
		order1.time = "2013年5月18日 13:10";
		order1.desp = "酱排骨";
		order1.address = "东19-308";
		order1.phone = "15216788888";
		order1.status = Order.SENT;
		
		Order order2 = new Order();
		order2.id = 1;
		order2.gid = 0;
		order2.eaterName = "瞿钧";
		order2.time = "2013年5月18日 13:20";
		order2.desp = "农家小炒肉";
		order2.address = "东19-306";
		order2.phone = "15216722222";
		order2.status = Order.PENDING;
		
		Order order3 = new Order();
		order3.id = 2;
		order3.gid = 0;
		order3.eaterName = "王孟晖";
		order3.time = "2013年5月18日 13:30";
		order3.desp = "泡泡鸡排";
		order3.address = "东19-303";
		order3.phone = "15216766666";
		order3.status = Order.PENDING;
		
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
	}
	
	@Override
	public int login(String id, String password) {
		return 0;
	}

	@Override
	public List<Order> pullOrders() {
		return orders;
	}

	@Override
	public boolean confirm(int orderId) {
		for(Order o: orders)
			if (o.id == orderId)
				if (o.status != Order.SENT) {
					o.status = Order.SENT;
					return true;
				} else
					return false;
		return false;
	}

	@Override
	public boolean cancel(int orderId) {
		for(Order o: orders)
			if (o.id == orderId)
				if (o.status != Order.CANCELED) {
					o.status = Order.CANCELED;
					return true;
				} else
					return false;
					
		return false;
	}

}
