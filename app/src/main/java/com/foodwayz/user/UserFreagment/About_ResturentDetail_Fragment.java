package com.foodwayz.user.UserFreagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodwayz.user.HomeActivity;
import com.foodwayz.R;

/**
 * Created by Android on 15-Sep-17.
 */
public class About_ResturentDetail_Fragment extends Fragment
{
    TextView text_about;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about, container, false);
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        HomeActivity.title.setVisibility(View.GONE);
       findElement(view);
        return view;
    }

    private void findElement(View view) {
        text_about = (TextView)view.findViewById(R.id.about);
    }






}
