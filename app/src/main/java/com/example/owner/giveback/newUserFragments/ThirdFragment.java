package com.example.owner.giveback.newUserFragments;

import android.app.Activity;
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


public class ThirdFragment extends Fragment {

    EditText etName;
    CheckBox cbAdmin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        etName = v.findViewById(R.id.etName);
        cbAdmin = v.findViewById(R.id.cbAdmin);
        NewUserActivity activity = (NewUserActivity) getActivity();
        etName.setText(activity.getUserName());
        cbAdmin.setChecked(activity.isAdmin());
        adminChangedListener();
        nameChangeListener();
        return v;
    }

    public static ThirdFragment newInstance(String text){

        Bundle UserInfo = new Bundle();

        ThirdFragment f = new ThirdFragment();
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