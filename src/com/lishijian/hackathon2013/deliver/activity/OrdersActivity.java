package com.lishijian.hackathon2013.deliver.activity;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.lishijian.hackathon2013.deliver.R;
import com.lishijian.hackathon2013.deliver.entity.Order;
import com.lishijian.hackathon2013.deliver.service.IDeliverService;
import com.lishijian.hackathon2013.deliver.service.ServiceFactory;

public class OrdersActivity extends ListActivity {

	private int mGroupId;
	
	private List<Order> mOrders;
	
	private IDeliverService mService;
	
	private Toast mToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_DeviceDefault);
		init();
	}
	
	private void init() {
		mService = ServiceFactory.buildService();
		mGroupId = -1;
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		// TODO
		
		freshData();
		setUpViews();
	}
	
	private void freshData() {
		List<Order> newOrders = mService.pullOrders();
		if (newOrders != null) {
			if (newOrders.get(0).gid == mGroupId) {
				mToast.setText("辛苦啦！没有新订单，休息会吧～");
				mToast.show();
				return;
			}
			mOrders.clear();
			mOrders.addAll(newOrders);
		}
		else {
			mToast.setText("服务器连接失败，请重试～");
			mToast.show();
		}
	}
	
	private void setUpViews() {
		// TODO
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_group_detail, menu);
		return true;
	}

}
