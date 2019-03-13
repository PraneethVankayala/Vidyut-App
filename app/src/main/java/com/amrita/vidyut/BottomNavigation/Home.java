package com.amrita.vidyut.BottomNavigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.amrita.vidyut.HomeAdapter;
import com.amrita.vidyut.HomeView;
import com.amrita.vidyut.MainActivity;
import com.amrita.vidyut.NotificationData;
import com.amrita.vidyut.ObjectSerializer;
import com.amrita.vidyut.R;
import com.amrita.vidyut.SignInActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

import static android.support.constraint.Constraints.TAG;

public class Home extends Fragment {

    List<com.amrita.vidyut.Home> arrayList;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<HomeView> madapter;
    SliderLayout sliderLayout;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container,false);
        layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView= view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        sliderLayout=view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();
        data();
<<<<<<< HEAD:app/src/main/java/com/example/vidyut/BottomNavigation/Home.java
        FirebaseMessaging.getInstance().subscribeToTopic("Co")
=======
        NotificationData data = new NotificationData();
        data.setText("hi");
        data.setDesc("ji");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        NotificationData realmmessage = realm.createObject(NotificationData.class);
        realmmessage.setDesc("hub");
        realmmessage.setImage("https://vidyut.amrita.edu/static/images/workshops/8a.jpg");
        realmmessage.setText("hhjs");
        realmmessage.setTimeago(System.currentTimeMillis());
        realm.commitTransaction();
        data.setImage("https://vidyut.amrita.edu/static/images/workshops/8a.jpg");
        data.setTimeago(System.currentTimeMillis());
        addTask(data);

        Toast.makeText(getActivity().getApplicationContext(), String.valueOf(getNotificatonData()),Toast.LENGTH_LONG);
        FirebaseMessaging.getInstance().subscribeToTopic("Cont")
>>>>>>> 4a39436b2109cbef43dacc5619888f7250553e95:app/src/main/java/com/amrita/vidyut/BottomNavigation/Home.java
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "Finished");
                        } else {
                            Log.d(TAG, "Error Subscribing Topic");
                        }
                    }
                });

        madapter=new HomeAdapter(arrayList);
        recyclerView.setAdapter(madapter);
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    private void setSliderViews() {
        for(int i = 0; i<=3; i++) {
            SliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.imagenew);
                    sliderView.setDescription("Breaking The Stereotypes");
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.workshops);
                    sliderView.setDescription("25+ Workshops");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.compettion);
                    sliderView.setDescription("30+ Competitions");
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.proshownew);
                    sliderView.setDescription("Proshows");
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;

            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    //Toast.makeText(getActivity(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

                }
            });


            sliderLayout.addSliderView(sliderView);

        }
    }

    public List<com.amrita.vidyut.Home> data(){
        arrayList = new ArrayList<com.amrita.vidyut.Home>();
        arrayList.add(new com.amrita.vidyut.Home("Contests",R.drawable.contests1));
        arrayList.add(new com.amrita.vidyut.Home("Workshops",R.drawable.workshops1));
//        arrayList.add(new com.amrita.vidyut.Home("Schedule",R.drawable.schedule));
//        arrayList.add(new com.amrita.vidyut.Home("Map",R.drawable.maps));

        return arrayList;
    }


    public ArrayList<NotificationData> getNotificatonData(){
        SharedPreferences prefs = getActivity().getSharedPreferences("Vidyut", Context.MODE_PRIVATE);

        try {
            return  (ArrayList<NotificationData>) ObjectSerializer.deserialize(prefs.getString("Notification", ObjectSerializer.serialize(new ArrayList<NotificationData>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void addTask(NotificationData t) {
        ArrayList<NotificationData> data ;
        data = getNotificatonData();
        if (null == getNotificatonData()) {
            data = new ArrayList<NotificationData>();
        }
        data.add(t);

        // save the task list to preference
        SharedPreferences prefs = getActivity().getSharedPreferences("Vidyut", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString("Notification", ObjectSerializer.serialize(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.commit();
        editor.apply();
    }


}