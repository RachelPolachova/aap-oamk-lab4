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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class StockMonitorV2Activity extends AppCompatActivity {

	final static String TAG = "StockMonitorActivity";
	RequestQueue queue;

	private ArrayList<Stock> stockArrayList = new ArrayList<Stock>() {
		{
			add(new Stock("Apple","AAPL", "0"));
			add(new Stock("Google","GOOGL", "0"));
			add(new Stock("Facebook","FB", "0"));
			add(new Stock("Nokia","NOK", "0"));
		}
	};

	private RecyclerView recyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager layoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		setContentView(R.layout.activity_stock_monitor_v2);
		queue = Volley.newRequestQueue(this);
		callRequests();
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
				stockArrayList.add(s);
				queue.add(getRequest(s));

				nameTv.setText("");
				idTv.setText("");
			}
		});
	}

	private void callRequests() {
		for (Stock stock: stockArrayList) {
			queue.add(getRequest(stock));
		}
	}

	private JsonObjectRequest getRequest(final Stock stock) {
		String url = "https://financialmodelingprep.com/api/company/price/" + stock.getId() + "?datatype=json";
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, "onResponse: " + response.toString());
				try {
					int i = stockArrayList.indexOf(stock);
					stockArrayList.get(i).setPrice(response.getJSONObject(stock.getId()).getString("price"));
					Log.d(TAG, "onResponse: " + stockArrayList.size());
					mAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					Log.d(TAG, "onResponse: get price error " + e.getMessage());
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "onErrorResponse: " + error.getMessage());
			}
		});
		return jsonObjectRequest;
	}


}
