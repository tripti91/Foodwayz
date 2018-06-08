package com.foodwayz.owner.OwnerFreagment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.foodwayz.owner.HomeActivity;
import com.foodwayz.R;

import java.util.ArrayList;

import Adapters.OrderAdapter;
import Modles.OrderDetail_Modal;

/**
 * Created by Android on 20-Sep-17.
 */
public class Order_Fragment extends Fragment implements View.OnClickListener {

    TextView tv_name,tv_Balance,tv_Fw_code;
    RecyclerView recycler_view;
    Button btn_Pay;
    OrderAdapter order_adapter;
    private ArrayList<OrderDetail_Modal> order_list;
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        order_list = new ArrayList<>();
        findElemrnt(view);
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);

        HomeActivity.title.setText("Order");
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        OrderDetail_Modal orderDetail_modal = new OrderDetail_Modal();

        orderDetail_modal.setItem_name("Muttr Paneer");
        orderDetail_modal.setPrice("220");
        orderDetail_modal.setQuantity("1");
        order_list.add(orderDetail_modal);
        orderDetail_modal.setItem_name("Muttr Paneer");
        orderDetail_modal.setPrice("440");
        orderDetail_modal.setQuantity("2");
        order_list.add(orderDetail_modal);
        orderDetail_modal.setItem_name("Muttr Paneer");
        orderDetail_modal.setPrice("220");
        orderDetail_modal.setQuantity("1");
        order_list.add(orderDetail_modal);
        orderDetail_modal.setItem_name("Muttr Paneer");
        orderDetail_modal.setPrice("220");
        orderDetail_modal.setQuantity("1");
        order_list.add(orderDetail_modal);
        orderDetail_modal.setItem_name("Muttr Paneer");
        orderDetail_modal.setPrice("440");
        orderDetail_modal.setQuantity("2");
        order_list.add(orderDetail_modal);
        orderDetail_modal.setItem_name("Muttr Paneer");
        orderDetail_modal.setPrice("440");
        orderDetail_modal.setQuantity("2");
        order_list.add(orderDetail_modal);

        order_adapter = new OrderAdapter(getActivity(),order_list);
        recycler_view.setAdapter(order_adapter);
        return view;

       
    }

  

    private void findElemrnt(View view) {

       
        tv_name = (TextView)view.findViewById(R.id.tv_name);
        tv_Balance = (TextView)view.findViewById(R.id.tv_Balance);
        tv_Fw_code = (TextView)view.findViewById(R.id.tv_Fw_code);
        btn_Pay = (Button)view.findViewById(R.id.btn_Pay);
        btn_Pay.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        
        switch (v.getId()){
            
            case R.id.btn_Pay:                
                break;
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }


    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
