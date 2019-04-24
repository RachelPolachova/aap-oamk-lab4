package com.rachel.polachova.aaplab4;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestManager {

    public void sendRequest(Context context, String url, final StringResponse stringResponse) {

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request(context, url, new StringResponse() {
            @Override
            public void onCallback(String response) {
                stringResponse.onCallback(response);
            }
        }));

    }

    private StringRequest request(final Context context, String url, final StringResponse stringResponse) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                stringResponse.onCallback(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
        return stringRequest;
    }

}
