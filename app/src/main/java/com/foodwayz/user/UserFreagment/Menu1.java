package com.foodwayz.user.UserFreagment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.foodwayz.R;
import com.foodwayz.user.HomeActivity;
import com.network.CustomPostRequest;
import com.network.IHttpRequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapters.ResturentAdapter;
import Modles.Resturent_detail;
import jsonparser.JsonParser;
import utills.Constant;
import utills.RecyclerItemClickListener;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu1 extends Fragment implements IHttpRequestCallBack {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ResturentAdapter mAdapter;
    private ArrayList<Resturent_detail> resturent_detail = new ArrayList<>();
    private ProgressDialog progress_dialog = null;
    String City = "", Area = "";
ArrayList<String> location =new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.resturent_lyt, container, false);
      HomeActivity.action_bar_share.setVisibility(View.GONE);
        HomeActivity.title.setVisibility(View.GONE);

        if (progress_dialog == null) {
            progress_dialog = new ProgressDialog(getActivity());
        }
        progress_dialog.setMessage("Please wait...");
        progress_dialog.setCancelable(true);
        progress_dialog.setCanceledOnTouchOutside(false);
        resturent_detail.clear();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            City = bundle.getString("City");
            Area = bundle.getString("Area");
            GetResturent_list();

        }
        location.add("palasiya");
        location.add("56 squer");
        location.add("vijay Nagar");

       String Citys=","+City;
      HomeActivity.city.setVisibility(View.VISIBLE);
       HomeActivity.location.setVisibility(View.VISIBLE);
        if(City.length()!=0) {
//            HomeActivity.location.setText(Area + Citys);
        }
        else{
//            HomeActivity.location.setText(Area );

        }
        Resturent_detail dtl=new Resturent_detail();
        dtl.setRateing((float) 4.5);
        dtl.setResturent_name("Apna Sweets");
        dtl.setLocation(location);
        resturent_detail.add(dtl);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.Adapter adapter = new ResturentAdapter(getActivity(),resturent_detail);
        mRecyclerView.setAdapter(adapter);
        hideProgressDialog();
        adapter.notifyDataSetChanged();
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                      LinearLayout  linear_lyt_detail=(LinearLayout) view.findViewById(R.id.linear_lyt_detail);
                        linear_lyt_detail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HomeActivity.city.setVisibility(View.GONE);
                                HomeActivity.location.setVisibility(View.GONE);
                                Fragment fragment = new Resturent_detail_Fragment();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.content_frame, fragment);
                                fragmentTransaction.addToBackStack("back");
                                fragmentTransaction.commit();

                            }
                        });

                        // TODO Handle item click
                        Button btn_order = (Button) view.findViewById(R.id.btn_order);
                        LinearLayout  linear_rate_it= (LinearLayout) view.findViewById(R.id.linear_rate_it);
                        linear_rate_it.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        btn_order.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Fragment fragment = new OrderPlace_Fragment();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.content_frame, fragment);
                                fragmentTransaction.addToBackStack("back");
                                fragmentTransaction.commit();

                            }
                        });

                    }
                })
        );
        return view;
    }

    private void GetResturent_list() {
        if (City.length() > 0 && Area.length() > 0) {
            showProgressDialog();
            CustomPostRequest httpRequest = new CustomPostRequest(Menu1.this, getResources().getString(R.string.URL_RESTURENT_List) + Area.replace(" ","%20") , null);
            httpRequest.execute();

        } else if (City.length() > 0) {
            showProgressDialog();
            CustomPostRequest httpRequest = new CustomPostRequest(Menu1.this, getResources().getString(R.string.URL_RESTURENT_List) + City, null);
            httpRequest.execute();
        } else {
            showProgressDialog();
            CustomPostRequest httpRequest = new CustomPostRequest(Menu1.this, getResources().getString(R.string.URL_RESTURENT_List) + "null", null);
            httpRequest.execute();
        }
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }


    @Override
    public void requestSuccessful(String aDataStr) throws JSONException {
        System.out.println("aDataStr---------->" + aDataStr);
        int mId = 0;
        try {
            JSONObject jsonObject = new JSONObject(aDataStr);


            String status = jsonObject.getString(Constant.JSON_STATUS_TAG);
            if (status.equals("false")) {
                showFailedAlertBox(jsonObject.getString(Constant.JSON_MESSAGE_TAG));
            }
            mId = jsonObject.getInt(Constant.JSON_APP_ID_TAG);

            if (mId == 9) {
                resturent_detail = JsonParser.parsseResturentlist(aDataStr);
                RecyclerView.Adapter adapter = new ResturentAdapter(getActivity(),resturent_detail);
                mRecyclerView.setAdapter(adapter);
                hideProgressDialog();
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showFailedAlertBox(String aMsg) {
        new AlertDialog.Builder(getActivity())
                .setMessage(aMsg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void requestFailure() {

    }

    public void showProgressDialog() {

        if (!progress_dialog.isShowing()) {
            progress_dialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progress_dialog.isShowing()) {
            progress_dialog.dismiss();
        }
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
