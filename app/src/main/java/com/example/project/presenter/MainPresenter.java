package com.example.project.presenter;

import android.content.Context;

import com.example.project.model.MainModel;

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
        contract.loadData(mainModel.loadData(context));
    }
}
