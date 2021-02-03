package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.R;
import com.example.project.adapter.Adapter;
import com.example.project.model.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Life extends AppCompatActivity implements View.OnClickListener {
    private static final String url="https://run.mocky.io/v3/b5adf54a-9e8a-47eb-a055-7ba3249aae9c";
    private RecyclerView recyclerView;
    private Adapter adapter;
    private RequestQueue requestQueue;
    private ArrayList<Film> array;
    ImageView imageFav,imageHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        imageHome=findViewById(R.id.Home);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Life.this,MainActivity.class);
                startActivity(intent);
            }
        });
        recyclerView=findViewById(R.id.recyclerView);
        imageFav=findViewById(R.id.fav);
        imageFav.setImageResource(R.drawable.faviourt);
        imageFav.setOnClickListener(this);
        requestQueue= Volley.newRequestQueue(this);
        array= new ArrayList<>();
        load();
        adapter=new Adapter(array,this);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new Adapter.onItemClickedListener() {
            @Override
            public void onIteamClicked(int postion) {
                String imgUrl=array.get(postion).getImageUrl();
                String title=array.get(postion).gettitle();
                String author=array.get(postion).getAuthor();
                String description=array.get(postion).getDescription();
                Intent intent=new Intent(Life.this, MoreInformation.class);
                ContentValues contentValues=new ContentValues();
                intent.putExtra("imgUrl",imgUrl);
                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("description",description);
                intent.putExtra("rate",array.get(postion).getRate());
                 startActivity(intent);
            }
        });
    }
    public  void load()
    {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray= response.getJSONArray("items");

                    float rate=2;

                    for(int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject iteam=jsonArray.getJSONObject(i);
                        JSONObject volume=iteam.getJSONObject("volumeInfo");
                        JSONArray authors=volume.getJSONArray("authors");
                        String name=authors.getString(0);
                        String title=volume.getString("title");
                        JSONObject img=volume.getJSONObject("imageLinks");
                        String uri=img.getString("thumbnail");
                        String description=new String();
                        if(i!=1) {
                            description = volume.getString("description");
                        }
                        array.add(new Film(uri,title,name,description,false,rate));
                        rate+=.5;
                        adapter.notifyDataSetChanged();
                        System.out.println(i);

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
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Life.this, Faviourt.class);

        imageFav.setImageResource(R.drawable.ic_baseline_favorite_24);
        startActivity(intent);
    }
}