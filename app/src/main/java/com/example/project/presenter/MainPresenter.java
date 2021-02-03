package com.example.project.presenter;

import android.content.Context;

import androidx.lifecycle.Observer;

import com.example.project.model.Film;
import com.example.project.model.MainModel;

import java.util.ArrayList;

public class MainPresenter {
    Contract contract;
    Context context;
    MainModel mainModel=new MainModel(context);

    public MainPresenter(Contract contract, Context context) {
        this.contract = contract;
        this.context=context;
    }

    public void passDataToSportActivty()
    {
         mainModel.loadData(context).observeForever(new Observer<ArrayList<Film>>(){
             @Override
             public void onChanged(ArrayList<Film> films) {
                 contract.loadData(films);
             }
         });

    }
}
