package com.foodwayz.user.UserFreagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.foodwayz.R;
import com.foodwayz.user.HomeActivity;

import utills.Constant;
import utills.SessionManager;

/**
 * Created by Android on 13-Sep-17.
 */
public class ContactUs_Fragment extends Fragment implements View.OnClickListener {

    EditText et_enter_name, et_message;
    Button bt_submit, bt_send_sms, bt_cancle, bt_send_email;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us, container, false);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.title.setText("Contact Us");
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        Find_element(view);
        return view;
    }

    private void Find_element(View view) {

        et_enter_name = (EditText) view.findViewById(R.id.et_enter_name);
        et_message = (EditText) view.findViewById(R.id.et_message);
        bt_submit = (Button) view.findViewById(R.id.bt_submit);
        bt_send_sms = (Button) view.findViewById(R.id.bt_sendsms);
        bt_cancle = (Button) view.findViewById(R.id.bt_cancle);
        bt_send_email = (Button) view.findViewById(R.id.bt_email);

        bt_submit.setOnClickListener(this);
        if (SessionManager.getuserRole().get(Constant.SHARED_PREFERENCE_ROLE).equals("user")) {
            bt_submit.setVisibility(view.VISIBLE);
            bt_send_sms.setVisibility(view.GONE);
            bt_cancle.setVisibility(view.GONE);
            bt_send_email.setVisibility(view.GONE);

        } else {
            bt_submit.setVisibility(view.GONE);
            bt_send_sms.setVisibility(view.VISIBLE);
            bt_cancle.setVisibility(view.VISIBLE);
            bt_send_email.setVisibility(view.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_submit:

                break;
        }

    }
}
