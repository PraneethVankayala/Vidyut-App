package com.example.vidyut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class edu extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText editText1,editText2,editText3,editText4,editText5;
    TextView textView;
    String course,major,institution;
    int year,college;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.edu_fragment,null);
        Spinner sp = view.findViewById(R.id.college);
        List<ClgList> list =  ((EditProfileActivity)getActivity()).clg;
        ArrayList<String> clgs = new ArrayList<String>();
        clgs.add("Add a new College");
        for(int i=0;i<list.size();i++){
            clgs.add(list.get(i).getName().trim());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, clgs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
        editText1 = view.findViewById(R.id.courses);
        editText2 = view.findViewById(R.id.major);
        editText4 = view.findViewById(R.id.institution);
        textView = view.findViewById(R.id.textView3);
        editText5 = view.findViewById(R.id.year);
        ((EditProfileActivity)getActivity()).prev.setVisibility(View.VISIBLE);
        ((EditProfileActivity)getActivity()).next.setText("Done");

        ((EditProfileActivity)getActivity()).next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course = editText1.getText().toString();
                major = editText2.getText().toString().trim();
                institution = editText4.getText().toString().trim();
                try {
                    year = Integer.valueOf(editText5.getText().toString().trim());
                }catch (Exception e){
                    Toast.makeText(getContext(),"Input all details",Toast.LENGTH_LONG).show();
                }
                if((!course.isEmpty()) && (!major.isEmpty()) && college != 0 && year != 0){
                    ((EditProfileActivity)getActivity()).details(course,major,college,institution,year);
                }else{
                    Toast.makeText(getContext(),"Input all details",Toast.LENGTH_LONG).show();
                }
            }
        });

        ((EditProfileActivity)getActivity()).prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditProfileActivity)getActivity()).loadFragment(new frame());
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
           editText4.setVisibility(View.VISIBLE);
           textView.setVisibility(View.VISIBLE);
           college = 0;
       }else{
        college = position;
            editText4.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
       }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
