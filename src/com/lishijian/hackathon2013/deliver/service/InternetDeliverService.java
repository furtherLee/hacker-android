package com.lishijian.hackathon2013.deliver.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.StrictMode;

import com.lishijian.hackathon2013.deliver.Configure;
import com.lishijian.hackathon2013.deliver.entity.Order;

public class InternetDeliverService implements IDeliverService {

	boolean strictModeSeted = false;

	private void setStrictMode() {
		if (strictModeSeted)
			return;

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());

		strictModeSeted = true;
	}

	@Override
	public int login(String id, String password) {
		try {
			HttpPost request = new HttpPost(Configure.getHostUrl()
					+ "deliver/login/");

			List<NameValuePair> params = new ArrayList<NameValuePair>(2);

			params.add(new BasicNameValuePair("email", id));
			params.add(new BasicNameValuePair("password", password));

			request.setEntity(new UrlEncodedFormEntity(params));

			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(request);

			String retSrc = EntityUtils.toString(httpResponse.getEntity());

			JSONObject result = new JSONObject(retSrc);
			if (result.getString("status").trim().equals("ok"))
				return result.getJSONObject("result").getInt("id");
			else
				return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Order> pullOrders() {
		setStrictMode();
		try {
			HttpPost request = new HttpPost(Configure.getHostUrl() + "deliver/"
					+ Configure.getUserId() + "/pull/");

			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(request);

			String retSrc = EntityUtils.toString(httpResponse.getEntity());

			JSONObject result = new JSONObject(retSrc);
			if (result.getString("status").trim().equals("ok")) {
				List<Order> ret = new ArrayList<Order>();
				JSONArray array = result.getJSONArray("result");
				for (int i = 0; i < array.length(); ++i)
					ret.add(Order.fromJson(array.getJSONObject(i)));
				return ret;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean confirm(int orderId) {
		setStrictMode();
		try {
			HttpPost request = new HttpPost(Configure.getHostUrl() + "deliver/"
					+ Configure.getUserId() + "/order/" + orderId + "/confirm/");

			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(request);

			String retSrc = EntityUtils.toString(httpResponse.getEntity());

			JSONObject result = new JSONObject(retSrc);
			if (result.getString("status").trim().equals("ok"))
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cancel(int orderId) {
		// TODO
		return false;
	}

}
