package com.lishijian.hackathon2013.deliver.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lishijian.hackathon2013.deliver.R;
import com.lishijian.hackathon2013.deliver.entity.Order;
import com.lishijian.hackathon2013.deliver.service.IDeliverService;
import com.lishijian.hackathon2013.deliver.service.ServiceFactory;

public class OrderDetailActivity extends ListActivity {

	private Order mOrder;

	private IDeliverService mService;

	private Toast mToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_DeviceDefault);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mOrder = this.getIntent().getParcelableExtra("order");
		mService = ServiceFactory.buildService();
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		setUpView();
	}

	private void setUpView() {
		TextView header = new TextView(this);
		header.setText("订单号: " + mOrder.id);
		header.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
		header.setTextSize(25);
		this.getListView().setHeaderDividersEnabled(true);
		this.getListView().addHeaderView(header);
		this.getListView().setClickable(false);
		this.getListView().setPadding(20, 20, 20, 20);
		freshData();
	}
	
	private void freshData() {
		this.setListAdapter(new SimpleAdapter(this, getData(),
				R.layout.order_detail, new String[] { "title", "summary" },
				new int[] { R.id.title, R.id.summary }));
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		pushItem(ret, "吃货", mOrder.eaterName);
		pushItem(ret, "状态", getStatus(mOrder.status));
		pushItem(ret, "电话", mOrder.phone);
		pushItem(ret, "地址", mOrder.address);
		pushItem(ret, "下单时间", mOrder.time);
		pushItem(ret, "订单内容", mOrder.desp);
		return ret;
	}

	private String getStatus(int status) {
		switch (status) {
		case Order.PENDING:
			return "递送中";
		case Order.CANCELED:
			return "已取消";
		case Order.SENT:
			return "已送达";
		default:
			return "咩";
		}
	}

	private void pushItem(List<Map<String, Object>> list, String title,
			String summary) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("summary", summary);
		list.add(map);
	}

	private boolean sent() {
		if (mOrder.status == Order.SENT)
			return false;

		if (mService.confirm(mOrder.id)) {
			mOrder.status = Order.SENT;
			freshData();
			for(Order o: Order.CurrentOrders)
				if (o.id == mOrder.id)
					o.status = Order.SENT;
			mToast.setText("递送成功！再接再厉噢~");
			mToast.show();
			return true;
		} else
			return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_sent:
			return sent();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_order_detail, menu);
		return true;
	}

}
