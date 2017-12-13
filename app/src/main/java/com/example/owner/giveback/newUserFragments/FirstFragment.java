package com.example.owner.giveback.newUserFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.owner.giveback.NewUserActivity;
import com.example.owner.giveback.R;

//For admin or volunteer
//Change Username here too

public class FirstFragment extends Fragment {

    EditText etName;
    CheckBox cbAdmin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        etName = v.findViewById(R.id.etName);
        cbAdmin = v.findViewById(R.id.cbAdmin);
        adminChangedListener();
        nameChangeListener();

        return v;
    }


    public static FirstFragment newInstance(String text){

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }



    private void adminChangedListener() {
        cbAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ((NewUserActivity)getActivity()).cbOnChanged(b);
            }
        });
    }

    private void nameChangeListener() {
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                ((NewUserActivity)getActivity()).fragOneChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
