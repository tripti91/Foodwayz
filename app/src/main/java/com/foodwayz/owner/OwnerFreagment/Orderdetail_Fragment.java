package com.foodwayz.owner.OwnerFreagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.foodwayz.R;
import com.foodwayz.owner.HomeActivity;

/**
 * Created by Android on 13-Sep-17.
 */
public class Orderdetail_Fragment extends Fragment implements View.OnClickListener {

    EditText et_enter_name,et_message;
    Button bt_submit;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_detail_fragment, container, false);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);

        HomeActivity.title.setText("Order Details");
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
//        Find_element(view);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bt_submit:

                break;
        }

    }
}
