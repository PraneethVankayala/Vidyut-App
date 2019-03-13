package com.amrita.vidyut.BottomNavigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amrita.vidyut.ListAdapter;
import com.amrita.vidyut.NotificationData;
import com.amrita.vidyut.ObjectSerializer;
import com.amrita.vidyut.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class NotificationFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dash, container, false);
        recyclerView = view.findViewById(R.id.workshop_recycler1);
        view = inflater.inflate(R.layout.fragment_dash,container,false);
        recyclerView = view.findViewById(R.id.workshop_recycler1);
        try {
            Realm realm = Realm.getDefaultInstance();
            RealmQuery query = realm.where(NotificationData.class);
            RealmResults results = query.findAll();
            if(!results.isEmpty()){
                ArrayList<NotificationData> arrayList = new ArrayList<>(realm.copyFromRealm(results));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                ListAdapter listAdapter = new ListAdapter(getActivity(),  arrayList);
                recyclerView.setAdapter(listAdapter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
