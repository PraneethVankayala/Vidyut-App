package com.amrita.vidyut;

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

public class edu extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText editText1,editText2,editText3,editText4,editText5;
    TextView textView;
    String cour,maj,institution;
    int year,college;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.edu_fragment,null);
        Spinner sp = view.findViewById(R.id.college);
        Spinner course=view.findViewById(R.id.spinner);
        Spinner major=view.findViewById(R.id.spinner2);
        String[] items = new String[]{"Select","AmritaViswaVidhyapeetham","Donbosko"};
        String[] courses=new String[]{"BTech","BArch","BEng","BSc","BDes","BBA","BBA","BCA","MTech","MArch","MEng","MSc","MBA","MCA","MEd","MBBS","BDS","BA","MA","Others"};
        String[] branch=new String[]{"Computer Science","Computer Engineering","Mathematics","Biology","Physics","Chemistry","Civil Engineering","Mechanical Engineering","Electrical Engineering","Electrical and Electronics Engineering","Electrical and Communications Engineering","Biotechnology","Business","Social Work","Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> courseadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, courses);
        ArrayAdapter<String> majoradapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, branch);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        course.setAdapter(courseadapter);
        major.setAdapter(majoradapter);
        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cour=courses[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maj=branch[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp.setOnItemSelectedListener(this);
        CheckBox ch = view.findViewById(R.id.checkBox);
        editText4 = view.findViewById(R.id.institution);
        textView = view.findViewById(R.id.textView3);
        editText5 = view.findViewById(R.id.year);
        ((EditProfileActivity)getActivity()).prev.setVisibility(View.VISIBLE);
        ((EditProfileActivity)getActivity()).next.setText("Done");

        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                editText4.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                }else {
                    editText4.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        ((EditProfileActivity)getActivity()).next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                institution = editText4.getText().toString().trim();
                try {
                    year = Integer.valueOf(editText5.getText().toString().trim());
                    ((EditProfileActivity)getActivity()).details(cour,maj,college,institution,year);
                }catch (Exception e){
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
        switch (position) {
            case 0:
                break;
            case 1:
                college =1;
                break;
            case 2:
                college = 2;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
