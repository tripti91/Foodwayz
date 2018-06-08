package com.foodwayz.owner.OwnerFreagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodwayz.R;
import com.foodwayz.owner.HomeActivity;

/**
 * Created by Android on 06-Sep-17.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    ImageView iv_edit;
    EditText tv_contact_no, tv_email, tv_address, tv_email2;
    TextView tv_name, tv_detail;
boolean editabe= true;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);
        HomeActivity.title.setText("My Profile");
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        Find_element(view);
        disableViews();
        return view;
    }

    private void Find_element(View view) {

        iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
        tv_contact_no = (EditText) view.findViewById(R.id.tv_contact_no);
        tv_email = (EditText) view.findViewById(R.id.tv_email);
        tv_email2 = (EditText) view.findViewById(R.id.tv_email2);
        tv_address = (EditText) view.findViewById(R.id.tv_address);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_detail = (TextView) view.findViewById(R.id.tv_detail);
        iv_edit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_edit:
                if(editabe)
                {
                    enableViews();
                    tv_contact_no.requestFocus();
                    editabe =false;

                }else
                {
                    disableViews();
                    editabe =true;
                }

                break;
        }
    }

    private void disableViews() {
        tv_email.setEnabled(false);
        tv_address.setEnabled(false);
        tv_email2.setEnabled(false);
        tv_contact_no.setEnabled(false);

    }

    private void enableViews() {
        tv_email.setEnabled(true);
        tv_address.setEnabled(true);
        tv_email2.setEnabled(true);
        tv_contact_no.setEnabled(true);
    }
}
