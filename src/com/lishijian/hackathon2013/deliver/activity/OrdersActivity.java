package com.lishijian.hackathon2013.deliver.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lishijian.hackathon2013.deliver.R;
import com.lishijian.hackathon2013.deliver.activity.adapter.OrdersAdapter;
import com.lishijian.hackathon2013.deliver.entity.Order;
import com.lishijian.hackathon2013.deliver.service.IDeliverService;
import com.lishijian.hackathon2013.deliver.service.ServiceFactory;

public class OrdersActivity extends ListActivity {

	private int mGroupId;
	
	private List<Order> mOrders;
	
	private IDeliverService mService;
	
	private Toast mToast;
	
	private OrdersAdapter mOrdersAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_DeviceDefault);
		init();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mOrdersAdapter.changed();
	}
	
	private void init() {
		mService = ServiceFactory.buildService();
		mGroupId = -1;
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		mOrders = new ArrayList<Order>();
		mOrdersAdapter = new OrdersAdapter(this, mOrders);
		this.setListAdapter(mOrdersAdapter);
		this.getListView().setPadding(20, 20, 20, 20);
		freshData();
		
		Order.CurrentOrders = mOrders;
	}
	
	private void freshData() {
		List<Order> newOrders = mService.pullOrders();
		if (newOrders != null) {
			if (newOrders.isEmpty() || newOrders.get(0).gid == mGroupId) {
				mToast.setText("辛苦啦！没有新订单，休息会吧～");
				mToast.show();
				return;
			}
			mGroupId = newOrders.get(0).gid;
			mOrders.clear();
			mOrders.addAll(newOrders);
			mOrdersAdapter.changed();
			mToast.setText("新订单来啦～辛苦喽！");
		}
		else {
			mToast.setText("服务器连接失败，请重试～");
			mToast.show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_group_detail, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_pull:
			freshData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, OrderDetailActivity.class);
		intent.putExtra("order", mOrders.get(position));
		startActivity(intent);
	}
	
}
