package com.lishijian.hackathon2013.deliver.activity;

import com.lishijian.hackathon2013.deliver.R;
import com.lishijian.hackathon2013.deliver.R.layout;
import com.lishijian.hackathon2013.deliver.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class OrderDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_DeviceDefault);
		setContentView(R.layout.activity_order_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_order_detail, menu);
		return true;
	}

}
