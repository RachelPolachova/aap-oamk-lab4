package com.rachel.polachova.aaplab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupButtons();
	}

	private void setupButtons() {
		setupHttpButton();
		setupStockMonitorButton();
		setupV2Monitor();
	}

	private void setupHttpButton() {
		Button goToHttp = findViewById(R.id.go_to_http);
		goToHttp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SimpleHTTPActivity.class);
				startActivity(i);
			}
		});
	}

	private void setupStockMonitorButton() {
		Button goToMonitor = findViewById(R.id.go_to_stock_monitor);
		goToMonitor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, StockMonitorActivity.class);
				startActivity(i);
			}
		});
	}

	private void setupV2Monitor() {
		Button goToMonitor = findViewById(R.id.go_to_stock_monitor_v2);
		goToMonitor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, StockMonitorV2Activity.class);
				startActivity(i);
			}
		});
	}
}
