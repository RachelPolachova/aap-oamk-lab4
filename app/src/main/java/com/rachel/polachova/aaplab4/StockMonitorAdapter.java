package com.rachel.polachova.aaplab4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rachel.polachova.aaplab4.DataModel.Stock;

import java.util.ArrayList;

public class StockMonitorAdapter extends RecyclerView.Adapter<StockMonitorAdapter.MyViewHolder> {

	private ArrayList<Stock> mDataSet;

	public StockMonitorAdapter(ArrayList<Stock> mDataSet) {
		this.mDataSet = mDataSet;
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.stock_monitor_item, viewGroup, false);

		MyViewHolder vh = new MyViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
		Stock stock = mDataSet.get(i);
		myViewHolder.bindStocks(stock);
	}

	@Override
	public int getItemCount() {
		return mDataSet.size();
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder {

		TextView name;
		TextView price;

		View view;

		public MyViewHolder(View v) {
			super(v);
			name = v.findViewById(R.id.name_text_view);
			price = v.findViewById(R.id.price_text_view);
			this.view = v;
		}

		public void bindStocks(Stock stock) {
			name.setText(stock.getName());
			price.setText(stock.getPrice());
		}
	}


}
