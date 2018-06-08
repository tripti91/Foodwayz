package Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.foodwayz.R;

import java.util.ArrayList;

import Modles.OfferDetail_Modal;


/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class Offer_Adapter extends RecyclerView.Adapter<Offer_Adapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<OfferDetail_Modal> flatList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_offer;

        public MyViewHolder(View view) {
            super(view);
            tv_offer = (TextView) view.findViewById(R.id.tv_offer);

        }
    }

    public Offer_Adapter(Context mContext, ArrayList<OfferDetail_Modal> flatList) {
        this.mContext = mContext;
        this.flatList = flatList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offers_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        OfferDetail_Modal album = flatList.get(position);
        holder.tv_offer.setText(album.getOffer());

    }


    @Override
    public int getItemCount() {
        return flatList.size();
    }
}
