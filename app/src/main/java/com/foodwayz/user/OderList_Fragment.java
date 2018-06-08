package com.foodwayz.user;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodwayz.R;

import java.util.ArrayList;

import Adapters.OrderList_Adapter;
import Modles.OrderList_Modal;

/**
 * Created by Android on 23-Oct-17.
 */
public class OderList_Fragment extends Fragment {

    private ArrayList<OrderList_Modal> list_orderlist;
    OrderList_Adapter orderList_adapter;

    RecyclerView recycler_view;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View  view = inflater.inflate(R.layout.orderlist_fragment,container,false);
        HomeActivity.title.setText("Completed Orders");
        list_orderlist = new ArrayList<>();
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recycler_view.setItemAnimator(new DefaultItemAnimator());

        OrderList_Modal orderList_modal = new OrderList_Modal();
        orderList_modal.setOrder_id("101");
        orderList_modal.setPack_name("Monthly package of Apna");
        orderList_modal.setTotal("500");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);
        orderList_modal.setOrder_id("102");
        orderList_modal.setPack_name("Monthly package of Apna");
        orderList_modal.setTotal("800");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);
        orderList_modal.setOrder_id("103");
        orderList_modal.setPack_name("Monthly package of Apna");
        orderList_modal.setTotal("900");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);
        orderList_modal.setOrder_id("104");
        orderList_modal.setPack_name("Monthly package of Apna");
        orderList_modal.setTotal("300");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);

        orderList_adapter = new OrderList_Adapter(getActivity(),list_orderlist);
        recycler_view.setAdapter(orderList_adapter);
        return view;

    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
}
