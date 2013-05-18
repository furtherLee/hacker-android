package com.lishijian.hackathon2013.deliver.activity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lishijian.hackathon2013.deliver.R;
import com.lishijian.hackathon2013.deliver.entity.Order;

public class OrdersAdapter implements ListAdapter {

	protected List<DataSetObserver> mObservers;
	
	protected List<Order> mOrders;
	
	protected LayoutInflater mInflater;
	
	protected Context mContext;
	
	public OrdersAdapter(Context context, List<Order> orders) {
		this.mOrders = orders;
		this.mObservers = new ArrayList<DataSetObserver> ();
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
	}
	
	public void changed() {
		for(DataSetObserver obs: mObservers)
			obs.onChanged();
	}

	@Override
	public int getCount() {
		return mOrders.size();
	}

	@Override
	public Object getItem(int index) {
		return mOrders.get(index);
	}

	@Override
	public long getItemId(int index) {
		return mOrders.get(index).id;
	}

	@Override
	public int getItemViewType(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) 
			convertView = mInflater.inflate(R.layout.order_item, null);
		
		TextView eaterNameView = (TextView)convertView.findViewById(R.id.eaterName);
		TextView phoneView = (TextView)convertView.findViewById(R.id.phone);
		TextView addressView = (TextView)convertView.findViewById(R.id.address);
		TextView arrowView = (TextView)convertView.findViewById(R.id.arrow);
		ImageView imgView = (ImageView)convertView.findViewById(R.id.img);
		Order order = mOrders.get(position);
		
		eaterNameView.setText(order.eaterName);
		phoneView.setText(order.phone);
		addressView.setText(order.address);
		
		if (order.status == Order.SENT)
			imgView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sent));
		
		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return mOrders.isEmpty();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver item) {
		mObservers.add(item);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver item) {
		mObservers.remove(item);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int arg0) {
		return true;
	}

}
