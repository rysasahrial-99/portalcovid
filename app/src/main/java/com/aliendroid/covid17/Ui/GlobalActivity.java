package com.aliendroid.covid17.Ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aliendroid.model.DataGlobal;
import com.aliendroid.model.Global;
import com.aliendroid.covid17.R;
import com.aliendroid.covid17.adapter.GlobalAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GlobalActivity extends AppCompatActivity {
    private TextView positif, sembuh, meninggal;
    private String TAG = GlobalActivity .class.getSimpleName();
    private RecyclerView recyclerView;
    private GlobalAdapter adapter;
    ArrayList<DataGlobal> webLists;
    private static String url5 = "https://api.kawalcorona.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_global);

        recyclerView = (RecyclerView) findViewById(R.id.recwah2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        webLists = new ArrayList<>();


        positif = findViewById(R.id.txtTotal);
        positif.setText(Global.positif);

        sembuh = findViewById(R.id.txtSem);
        sembuh.setText(Global.sembuh);

        meninggal = findViewById(R.id.txtMen);
        meninggal.setText(Global.meningal);

        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyyy h:m:s a");
        //SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        String strdate1 = sdf1.format(c1.getTime());
        TextView txtdate1 = (TextView) findViewById(R.id.txttanggal);
        txtdate1.setText(strdate1);
        if (checkConnectivity()){
            loadUrlData();
        }
    }

    private void loadUrlData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url5, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonData = jsonArray.getJSONObject(i);
                            JSONObject j = jsonData.getJSONObject("attributes");
                            DataGlobal developers = new DataGlobal();
                            developers.OBJECTID = j.getInt("OBJECTID");
                            developers.Country_Region = j.getString("Country_Region");
                            developers.Confirmed = j.getString("Confirmed");
                            developers.Deaths = j.getString("Deaths");
                            developers.Recovered= j.getString("Recovered");
                            developers.Active = j.getString("Active");
                            webLists.add(developers);

                        }

                        adapter = new GlobalAdapter(webLists, GlobalActivity.this);
                        recyclerView.setAdapter(adapter);
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

        RequestQueue requestQueue = Volley.newRequestQueue(GlobalActivity.this);
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

    public void onResume(){
        super.onResume();
        if (checkConnectivity()){
            loadUrlData();
        }
    }
}