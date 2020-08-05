package com.aliendroid.covid17.Ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aliendroid.model.Global;
import com.aliendroid.model.Indonesia;
import com.aliendroid.covid17.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    private static String url = "https://api.kawalcorona.com/indonesia";
    private static String url2 = "https://api.kawalcorona.com/positif";
    private static String url3 = "https://api.kawalcorona.com/sembuh";
    private static String url4 = "https://api.kawalcorona.com/meninggal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        if (checkConnectivity()){
            loadUrlData();
            loadUrlDataGlobal();
            loadUrlDataSmGlobal();
            loadUrlDataMeGlobal();
        }
        new CountDownTimer(10000, 1000) {
            @Override
            public void onFinish() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onTick(long millisUntilFinished) {
            }
        }.start();
    }

    private void loadUrlData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray mJsonArray = new JSONArray(response);
                        for(int i=0;i<mJsonArray.length();i++) {
                            JSONObject jsonData = mJsonArray.getJSONObject(i);
                            Indonesia.name = jsonData.getString("name");
                            Indonesia.positif = jsonData.getString("positif");
                            Indonesia.sembuh = jsonData.getString("sembuh");
                            Indonesia.meninggal = jsonData.getString("meninggal");
                            Indonesia.dirawat = jsonData.getString("dirawat");
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        requestQueue.add(stringRequest);
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            // Toast.makeText(getApplicationContext(), "Sin conexión a Internet...", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void loadUrlDataGlobal() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonArray = new JSONObject(response);
                        Global.name = jsonArray.getString("name");
                        Global.positif = jsonArray.getString("value");
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        requestQueue.add(stringRequest);
    }

    private void loadUrlDataSmGlobal() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonArray = new JSONObject(response);
                        Global.namesem = jsonArray.getString("name");
                        Global.sembuh = jsonArray.getString("value");
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        requestQueue.add(stringRequest);
    }

    private void loadUrlDataMeGlobal() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonArray = new JSONObject(response);
                        Global.namemen = jsonArray.getString("name");
                        Global.meningal = jsonArray.getString("value");
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        requestQueue.add(stringRequest);
    }


}