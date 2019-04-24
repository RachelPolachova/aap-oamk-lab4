package com.rachel.polachova.aaplab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rachel.polachova.aaplab4.DataModel.Stock;
import com.rachel.polachova.aaplab4.DataModel.StockCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class StockMonitorV2Activity extends AppCompatActivity {

	final static String TAG = "StockMonitorActivity";
	RequestQueue queue;

	private ArrayList<Stock> stockArrayList = new ArrayList<Stock>();

	private RecyclerView recyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager layoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		setContentView(R.layout.activity_stock_monitor_v2);
		queue = Volley.newRequestQueue(this);
		recyclerView = findViewById(R.id.stock_monitor_v2_recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		mAdapter = new StockMonitorAdapter(stockArrayList);
		recyclerView.setAdapter(mAdapter);
		setupAddButton();
	}


	private void setupAddButton() {
		Button add = findViewById(R.id.add_button);
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView nameTv = findViewById(R.id.name_value);
				TextView idTv = findViewById(R.id.id_value);

				Stock s = new Stock(nameTv.getText().toString(), idTv.getText().toString(), "0");
				callRequests(s);

				nameTv.setText("");
				idTv.setText("");
			}
		});
	}

	private void callRequests(Stock newStock) {
		RequestManager manager = new RequestManager();
		queue.add(manager.getJsonRequest(getApplicationContext(), newStock, new StockCallback() {
			@Override
			public void onCallback(Stock stock) {
				stockArrayList.add(stock);
				mAdapter.notifyDataSetChanged();
			}
		}));
	}




}
