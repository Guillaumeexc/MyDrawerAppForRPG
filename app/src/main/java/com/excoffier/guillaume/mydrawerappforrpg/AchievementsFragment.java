package com.excoffier.guillaume.mydrawerappforrpg;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;

import static android.R.attr.fragment;


public class AchievementsFragment extends Fragment implements View.OnClickListener{
    Button restart;









    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_achievements, container, false);
        SetupControls(v);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.restartBtn:
                ClearData();


        }
    }

    public void SetupControls(View v){
        restart= (Button)v.findViewById(R.id.restartBtn);
        restart.setOnClickListener(this);
    }

    public void ClearData(){
        GameFragment gameFragment= new GameFragment();

        Bundle bundle= new Bundle();
        bundle.putInt("resetpos",0);
        bundle.putInt("resethealth",100);
        bundle.putInt("resethealthmax",100);
        bundle.putInt("resetdamage",15);
        gameFragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container,gameFragment).commit();












    }



}
