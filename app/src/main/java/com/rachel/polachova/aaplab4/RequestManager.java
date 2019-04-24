package com.rachel.polachova.aaplab4;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rachel.polachova.aaplab4.DataModel.Stock;
import com.rachel.polachova.aaplab4.DataModel.StockCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RequestManager {

    private static final String TAG = "Request Manager";

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

    public JsonObjectRequest getJsonRequest(final Context context, final Stock stock, final StockCallback stockCallback) {
        String url = "https://financialmodelingprep.com/api/company/price/" + stock.getId() + "?datatype=json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: " + response.toString());
                try {
//                    int i = stockArrayList.indexOf(stock);
//                    stockArrayList.get(i).setPrice(response.getJSONObject(stock.getId()).getString("price"));
//                    Log.d(TAG, "onResponse: " + stockArrayList.size());
//                    mAdapter.notifyDataSetChanged();
                    Stock stock1 = new Stock(stock.getName(), stock.getId(), response.getJSONObject(stock.getId()).getString("price"));
                    stockCallback.onCallback(stock1);
                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return jsonObjectRequest;
    }

}
