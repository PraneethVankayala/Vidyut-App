package com.example.vidyut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class frame extends Fragment implements AdapterView.OnItemSelectedListener{
    EditText editText,editText1,editText2,editText4;
    Spinner sp;
    String fname,lname,phno;
    int sex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.form_fragment,null);
        editText=view.findViewById(R.id.fname);
        sp = view.findViewById(R.id.sex);
        editText1=view.findViewById(R.id.lname);
        editText2=view.findViewById(R.id.phno);
        ((EditProfileActivity)getActivity()).next.setText("Next");
        ((EditProfileActivity)getActivity()).prev.setVisibility(View.GONE);

        String[] items = new String[]{"Select","Male","Female","Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);

        try {
            ((EditProfileActivity)getActivity()).next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fname = (editText.getText()).toString();
                    lname = editText1.getText().toString().trim();
                    phno = editText2.getText().toString().trim();
                    ((EditProfileActivity)getActivity()).retrivefrag(fname,lname,phno,sex);
                }
            });
        }catch (NullPointerException n){
            Toast.makeText(getContext(),"Error is"+n.getMessage(),Toast.LENGTH_LONG).show();
        }

        return  view;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
            case 1:
                sex = 0;
                break;
            case 2:
                sex = 1;
                break;
            case 3 :
                sex = 2;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
