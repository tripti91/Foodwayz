package com.foodwayz.user.UserFreagment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.foodwayz.user.HomeActivity;
import com.foodwayz.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Abhi on 9/23/2017.
 */
public class OrderPlace_Fragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    TextView tv_name,startdate,enddate, tv_customer_id, tv_price, tv_note1, tv_note2, tv_address,tv_day;
    EditText et_Comment;
    Spinner sp_Month, sp_thali;
    Button btn_place_order;
    private int mYear, mMonth, mDay, mHour, mMinute;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_place, container, false);
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.title.setText("Order Place");
        findElement(view);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        return view;
    }

    private void findElement(View view) {
        startdate = (TextView) view.findViewById(R.id.startdate);
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                startdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }


        });
        enddate = (TextView) view.findViewById(R.id.enddate);
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                enddate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }


        });
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_customer_id = (TextView) view.findViewById(R.id.tv_customer_id);
        tv_day = (TextView)view.findViewById(R.id.tv_day);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_note1 = (TextView) view.findViewById(R.id.tv_note1);
        tv_note2 = (TextView) view.findViewById(R.id.tv_note2);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        sp_Month = (Spinner) view.findViewById(R.id.sp_Month);
        sp_thali = (Spinner) view.findViewById(R.id.sp_thali);
        et_Comment = (EditText) view.findViewById(R.id.et_Comment);
        btn_place_order = (Button) view.findViewById(R.id.btn_place_order);
        btn_place_order.setOnClickListener(this);

        // Spinner click listener
        sp_thali.setOnItemSelectedListener(this);
        sp_Month.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Special Thali");
        categories.add("Regular Thali");
        categories.add("Shahi Thali");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        sp_thali.setAdapter(dataAdapter);

        List<String> month = new ArrayList<String>();
        month.add("7 Days");
        month.add("15 Days");
        month.add("20 Days");
        month.add("1 Month");

        month.add("Enter Number of Days");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, month);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        sp_Month.setAdapter(dataAdapter2);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        if (sp_Month.getSelectedItem().toString().equals("Enter Number of Days")){
            Toast.makeText(parent.getContext(), "Enter Number of Days: " + item, Toast.LENGTH_LONG).show();
            tv_day.setVisibility(View.VISIBLE);
            tv_day.setText("31");
        }


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_place_order:

                PlaceOrder();
        }

    }

    private void PlaceOrder() {

        Fragment fragment = new Order_Fragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack("back");
        fragmentTransaction.commit();

    }
    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    onBackPressed();
                    return true;

                }

                return false;
            }
        });
    }
    public void onBackPressed() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getActivity().getSupportFragmentManager().popBackStack();
        } else {
            getActivity().finish();
        }
    }
}
