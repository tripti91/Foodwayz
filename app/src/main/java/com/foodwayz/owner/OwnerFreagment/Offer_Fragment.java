package com.foodwayz.owner.OwnerFreagment;

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
import com.foodwayz.owner.HomeActivity;

import java.util.ArrayList;

import Adapters.Offer_Adapter;
import Modles.OfferDetail_Modal;

/**
 * Created by Android on 12-Sep-17.
 */
public class Offer_Fragment extends Fragment {

    RecyclerView recyclerView;
    Offer_Adapter offer_adapter;
    ArrayList<OfferDetail_Modal> list;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offers_fragment, container, false);
        list = new ArrayList<>();
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);

        HomeActivity.title.setText("Offers");
        for(int i=1; i<=5; i++) {
            OfferDetail_Modal offerDetail_modal = new OfferDetail_Modal();
            offerDetail_modal.setOffer("50%");
            list.add(offerDetail_modal);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        offer_adapter = new Offer_Adapter(getActivity(),list);
        recyclerView.setAdapter(offer_adapter);

        return view;
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


}
