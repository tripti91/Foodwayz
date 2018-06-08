package com.foodwayz.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodwayz.R;

/**
 * Created by Android on 20-Sep-17.
 */
public class TermCondition_Fragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.term_condition_fragment, container, false);

        HomeActivity.title.setText("Term & Condition");
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        return view;
    }
}
