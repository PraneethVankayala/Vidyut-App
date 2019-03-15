package com.amrita.vidyut.BottomNavigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

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
        TextView textView=new TextView(getActivity().getApplicationContext());
        Typeface typeface = ResourcesCompat.getFont(getActivity(), R.font.frontage_bold);
        textView.setTypeface(typeface);
        textView.setText("ABOUT");
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(ab!=null) {
            ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ab.setCustomView(textView);
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
            ab.setBackgroundDrawable(colorDrawable);
            ab.setDisplayShowTitleEnabled(false);
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
            upArrow.setColorFilter(getResources().getColor(R.color.lightBlack), PorterDuff.Mode.SRC_ATOP);
            ab.setHomeAsUpIndicator(upArrow);
        }
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
