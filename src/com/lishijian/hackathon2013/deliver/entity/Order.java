package com.lishijian.hackathon2013.deliver.entity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;

public class Order extends Binder implements Parcelable {

	public static final int PENDING = 0, SENT = 1, CANCELED = 2;
	
	public static List<Order> CurrentOrders;
	
	public static Order fromJson(JSONObject json) throws JSONException {
		Order order = new Order();
		order.id = json.getInt("id");
		order.gid = json.getInt("gid");
		order.eaterName = json.getString("name");
		order.time = json.getString("time");
		order.desp = json.getString("description");
		order.address = json.getString("address");
		order.phone = json.getString("phone");
		String status = json.getString("status");
		
		if (status.trim().equalsIgnoreCase("sent"))
			order.status = SENT;
		else if (status.trim().equalsIgnoreCase("pending"))
			order.status = PENDING;
		else
			order.status = CANCELED;
		
		return order;
	}
	
	public int id;
	
	public int gid;

	public String eaterName;
	
	public String time;
	
	public String desp;
	
	public String address;
	
	public int status;
	
	public String phone;
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStrongBinder(this);
	}
	
	public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {

		@Override
		public Order createFromParcel(Parcel source) {
			return (Order)source.readStrongBinder();
		}

		@Override
		public Order[] newArray(int size) {
			return new Order[size];
		}
		
	};

}
