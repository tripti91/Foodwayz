package com.foodwayz.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodwayz.R;

/**
 * Created by Android on 06-Sep-17.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    ImageView iv_edit;
    TextView tv_contact_no,tv_email,tv_address,tv_name,tv_detail;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        HomeActivity.title.setVisibility(View.VISIBLE);

        HomeActivity.title.setText("My Profile");
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        Find_element(view);
        return view;
    }

    private void Find_element(View view) {

        iv_edit = (ImageView)view.findViewById(R.id.iv_edit);
        tv_contact_no = (TextView)view.findViewById(R.id.tv_contact_no);
        tv_email = (TextView)view.findViewById(R.id.tv_email);
        tv_address = (TextView)view.findViewById(R.id.tv_address);
        tv_name = (TextView)view.findViewById(R.id.tv_name);
        tv_detail = (TextView)view.findViewById(R.id.tv_detail);
        iv_edit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.iv_edit:

                break;
        }
    }
}
