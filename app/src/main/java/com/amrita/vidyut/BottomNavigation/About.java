package com.amrita.vidyut.BottomNavigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.amrita.vidyut.ListAdapter;
import com.amrita.vidyut.ViewPagerAdapter;
import com.amrita.vidyut.NotificationData;
import com.amrita.vidyut.ObjectSerializer;
import com.amrita.vidyut.R;

import java.util.ArrayList;

public class About extends Fragment {
    private TabLayout tabLayout;
    private View view;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about,null);
        tabLayout = view.findViewById(R.id.account_tablayout);
        viewPager = view.findViewById(R.id.account_viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());


        viewPagerAdapter.addFragment(new AboutVidyut(),"About");
        viewPagerAdapter.addFragment(new NotificationFragment(),"Notification");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
