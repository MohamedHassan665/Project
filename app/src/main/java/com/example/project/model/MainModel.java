package com.example.project.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.adapter.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainModel {
    private static final String url="https://run.mocky.io/v3/f393cb45-4081-4e33-a45e-c2966a90f2f8";
     ArrayList<Film> array=new ArrayList<>();
     Context context;
    RequestQueue requestQueue;
    public MainModel(Context context) {
        this.context = context;
    }
public ArrayList<Film>loadData(Context context)
{
           requestQueue= Volley.newRequestQueue(context.getApplicationContext());
           JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    float rate=2;
                    JSONArray jsonArray= response.getJSONArray("items");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject iteam=jsonArray.getJSONObject(i);

                        JSONObject volume=iteam.getJSONObject("volumeInfo");
                        JSONArray authors=volume.getJSONArray("authors");
                        String name=authors.getString(0);
                        String title=volume.getString("title");

                        JSONObject img=volume.getJSONObject("imageLinks");
                        String uri=img.getString("thumbnail");
                        String description=volume.getString("description");
                        array.add(new Film(uri,title,name,description,false,rate));
                        rate+=.5;

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);


return array;
}
}
