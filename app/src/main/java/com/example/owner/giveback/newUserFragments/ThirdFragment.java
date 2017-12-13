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

}