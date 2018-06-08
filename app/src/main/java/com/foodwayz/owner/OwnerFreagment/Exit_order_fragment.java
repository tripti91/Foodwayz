package com.foodwayz.owner.OwnerFreagment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodwayz.R;
import com.foodwayz.owner.HomeActivity;

import java.util.ArrayList;

import Adapters.OrderList_Adapter;
import Adapters.OrderList_ownerAdapter;
import Modles.OrderList_Modal;
import utills.RecyclerItemClickListener;

/**
 * Created by Android on 23-Oct-17.
 */
public class Exit_order_fragment extends Fragment {

    private ArrayList<OrderList_Modal> list_orderlist;
    OrderList_ownerAdapter orderList_adapter;

    RecyclerView recycler_view;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View  view = inflater.inflate(R.layout.orderlist_fragment,container,false);
        HomeActivity.title.setText("Exit Orders");
        HomeActivity.action_bar_delete.setVisibility(view.GONE);

        list_orderlist = new ArrayList<>();
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recycler_view.setItemAnimator(new DefaultItemAnimator());

        OrderList_Modal orderList_modal = new OrderList_Modal();
        orderList_modal.setOrder_id("Tripti Sharma");
        orderList_modal.setPack_name("Package name which are selected by user ");
        orderList_modal.setTotal("11 nov 2017");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);
        orderList_modal.setOrder_id("Tripti Sharma");
        orderList_modal.setPack_name("Package name which are selected by user ");
        orderList_modal.setTotal("11 nov 2017");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);
        orderList_modal.setOrder_id("Tripti Sharma");
        orderList_modal.setPack_name("Package name which are selected by user ");
        orderList_modal.setTotal("11 nov 2017");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);
        orderList_modal.setOrder_id("Tripti Sharma");
        orderList_modal.setPack_name("Package name which are selected by user ");
        orderList_modal.setTotal("11 nov 2017");
        orderList_modal.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry");
        list_orderlist.add(orderList_modal);
        orderList_adapter = new OrderList_ownerAdapter (getActivity(),list_orderlist);
        recycler_view.setAdapter(orderList_adapter);
        recycler_view.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Fragment fragment = new Orderdetail_Fragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, fragment);
                        fragmentTransaction.addToBackStack("back");
                        fragmentTransaction.commit();
                    }
                })
        );
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
