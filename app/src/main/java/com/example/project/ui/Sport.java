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
import com.example.project.presenter.Contract;
import com.example.project.presenter.MainPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sport extends AppCompatActivity implements Contract {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Film> array;
    ImageView imageFav,imageHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        recyclerView=findViewById(R.id.recyclerView);
        imageFav=findViewById(R.id.fav);
        imageHome=findViewById(R.id.Home);
        array= new ArrayList<>();
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sport.this,MainActivity.class);
                startActivity(intent);
            }
        });
        imageFav.setImageResource(R.drawable.faviourt);
        imageFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sport.this, Faviourt.class);
                imageFav.setImageResource(R.drawable.ic_baseline_favorite_24);
                startActivity(intent);
            }
        });
        MainPresenter mainPresenter=new MainPresenter(this::loadData,this);
        mainPresenter.passDataToSportActivty();
        adapter=new Adapter(array,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new Adapter.onItemClickedListener() {
            @Override
            public void onIteamClicked(int postion) {
                String imgUrl=array.get(postion).getImageUrl();
                String title=array.get(postion).gettitle();
                String author=array.get(postion).getAuthor();
                String description=array.get(postion).getDescription();
                Intent intent=new Intent(Sport.this, MoreInformation.class);
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

    @Override
    public void loadData(ArrayList<Film> information) {

        array.addAll(information);
        adapter.notifyDataSetChanged();

    }
}