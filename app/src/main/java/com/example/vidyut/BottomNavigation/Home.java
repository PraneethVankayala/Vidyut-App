package com.example.vidyut.BottomNavigation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vidyut.HomeAdapter;
import com.example.vidyut.HomeView;
import com.example.vidyut.R;
import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    List<com.example.vidyut.Home> arrayList;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<HomeView> madapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container,false);
        layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView= view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        data();
        madapter=new HomeAdapter(arrayList);
        recyclerView.setAdapter(madapter);
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    public List<com.example.vidyut.Home> data(){
        arrayList = new ArrayList<com.example.vidyut.Home>();
        arrayList.add(new com.example.vidyut.Home("Contests",R.drawable.contests));
        arrayList.add(new com.example.vidyut.Home("Workshops",R.drawable.workshops));
        arrayList.add(new com.example.vidyut.Home("Concerts",R.drawable.concerts));
        arrayList.add(new com.example.vidyut.Home("Talks",R.drawable.tedtalks));
        arrayList.add(new com.example.vidyut.Home("Exhibition",R.drawable.exhibition));
        arrayList.add(new com.example.vidyut.Home("Sponsers",R.drawable.sponsers));

        return arrayList;
    }

}