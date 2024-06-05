package com.sunna.lgmviptask1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    List<CovidData> cdata = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private String[] states = {"State Unassigned", "Andaman and Nicobar Islands",
            "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Chhattisgarh",
            "Delhi", "Dadra and Nagar Haveli and Daman and Diu", "Goa", "Gujarat", "Himachal Pradesh",
            "Haryana", "Jharkhand", "Jammu and Kashmir", "Karnataka", "Kerala", "Ladakh", "Lakshadweep",
            "Maharashtra", "Meghalaya", "Manipur", "Madhya Pradesh", "Mizoram", "Nagaland", "Odisha",
            "Punjab", "Puducherry", "Rajasthan", "Sikkim", "Telangana", "Tamil Nadu", "Tripura",
            "Uttar Pradesh", "Uttarakhand", "West Bengal"};

    private CustomAdapter madapter;
    private String jsonResponse;
    ListView listView;
    CovidData covidData;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        getJson();
        autoCompleteTextView = findViewById(R.id.autocomplete_text_view);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, states);
        autoCompleteTextView.setAdapter(adapter);
        listView = findViewById(R.id.listView);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cdata.clear();
                String stateName = adapter.getItem(position);
                extractcovidData(stateName);
            }
        });


    }

    private void getJson() {
        String JSON_URL = "https://data.covid19india.org/state_district_wise.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            jsonResponse = response;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(stringRequest);
    }

    private void extractcovidData(String stateName) {
        try {
            Log.d("response", "extractcovidData: "+jsonResponse);
            JSONObject jsonobject = new JSONObject(jsonResponse);
            Iterator<String> keys = jsonobject.keys();
            while(keys.hasNext()){
                String key = keys.next();
                if(key.equals(stateName)){
                    JSONObject jsonob = jsonobject.getJSONObject(key);
                    JSONObject jsonobb = jsonob.getJSONObject("districtData");
                    Iterator<String> superkeys = jsonobb.keys();
                    while (superkeys.hasNext()) {
                        String superkey = superkeys.next();
                        JSONObject jsonobbb = jsonobb.getJSONObject(superkey);
                        String active = jsonobbb.getString("active");
                        String confirmed = jsonobbb.getString("confirmed");
                        String deceased = jsonobbb.getString("deceased");
                        String recovered = jsonobbb.getString("recovered");
                        covidData = new CovidData(superkey, active, confirmed, recovered, deceased);
                        cdata.add(covidData);
                        Log.d("data", "extractcovidData: "+superkey + active);
                    }

                }
            }
            madapter = new CustomAdapter(MainActivity.this, cdata);
            listView.setAdapter(madapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}