package com.example.vidyut.BottomNavigation;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vidyut.R;

public class QrCode extends Fragment {

    String vid=Dashboard.vids;
    ImageView imageView;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_qrcode,null);
        imageView=view.findViewById(R.id.qrcode);
        textView=view.findViewById(R.id.vidqr);
        if(vid.length()==3){
            textView.setText("V190"+vid);
        }
        else{
            textView.setText("V19"+vid);
        }
        String url="https://devhub.amblygon.org/static/QR/"+vid+".png";
        Glide.with(view.getContext()).load(Uri.parse(url)).into(imageView);
        return view;
    }
}