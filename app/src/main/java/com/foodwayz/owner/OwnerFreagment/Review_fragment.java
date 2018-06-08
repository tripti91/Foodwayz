package com.foodwayz.owner.OwnerFreagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodwayz.R;
import com.foodwayz.owner.HomeActivity;

import java.util.ArrayList;

import Adapters.ResturentAdapter;
import Adapters.Review_adaptor;
import Modles.Resturent_detail;
import utills.RecyclerItemClickListener;

/**
 * Created by Belal on 18/09/16.
 */


public class Review_fragment extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ResturentAdapter mAdapter;
    private ArrayList<Resturent_detail> resturent_detail = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_lyt, container, false);
        resturent_detail.clear();
        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);

        HomeActivity.title.setText("Reviews");
        getResturentList();
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        RecyclerView.Adapter adapter = new Review_adaptor(resturent_detail);
        mRecyclerView.setAdapter(adapter);
        // 5. set item animator to DefaultAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                  /*      Fragment fragment = new Resturent_detail_Fragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();*/
                    }
                })
        );
        return view;
    }

    private void getResturentList() {
        Resturent_detail res_detail = new Resturent_detail();
        res_detail.setId("1");
        res_detail.setResturent_name("Apana Sweet's");
        res_detail.setArea("  Palasiya ");
        res_detail.setCity("Indore");
        res_detail.setRateing_count("4.5");
        res_detail.setRateing((float) 2.0);
        resturent_detail.add(res_detail);

        res_detail = new Resturent_detail();
        res_detail.setId("2");
        res_detail.setResturent_name("Vijay Chaat House ");
        res_detail.setArea("  56 ");
        res_detail.setCity("Indore");
        res_detail.setRateing_count("3.5");
        res_detail.setRateing((float) 5.0);
        resturent_detail.add(res_detail);

        res_detail = new Resturent_detail();
        res_detail.setId("3");
        res_detail.setArea("Palasiya");
        res_detail.setCity("Indore");
        res_detail.setRateing_count("3.5");
        res_detail.setResturent_name("Nafees Restaurant ");
        res_detail.setRateing((float) 3.0);
        resturent_detail.add(res_detail);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
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
