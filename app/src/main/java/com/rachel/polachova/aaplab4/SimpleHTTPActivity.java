package com.rachel.polachova.aaplab4;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SimpleHTTPActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_http);
		setupButtons();
	}

	private void setupButtons() {
		setupSubmitButton();
	}

	private void setupSubmitButton() {
		Button submit = findViewById(R.id.submit_url_button);
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendRequest();
			}
		});
	}

	private void sendRequest() {


		RequestQueue queue = Volley.newRequestQueue(this);
		try {
			TextView urlTextView = findViewById(R.id.url_text_view);
			String url = urlValidation(urlTextView.getText().toString());
			queue.add(request(url));
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}

	}

	private String urlValidation(String url) throws Exception {

		if (!url.contains("http") || !url.contains(".")) {
			throw new Exception("URL is not valid.");
		}

		return url;
	}

	private StringRequest request(String url) {
		final TextView respTextView = findViewById(R.id.response_text_view);
		respTextView.setMovementMethod(new ScrollingMovementMethod());
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {


				respTextView.setText(response);

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				respTextView.setText("Something went wrong.");
			}
		});
		return stringRequest;
	}


}
