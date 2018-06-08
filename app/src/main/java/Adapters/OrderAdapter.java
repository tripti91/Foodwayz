package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodwayz.R;

import java.util.ArrayList;

import Modles.OrderDetail_Modal;

/**
 * Created by Android on 20-Sep-17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private ArrayList<OrderDetail_Modal> orderDetail;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_quantity,tv_itemName,tv_Price;

        public ViewHolder(View view) {
            super(view);
            tv_quantity = (TextView) view.findViewById(R.id.tv_quantity);
            tv_itemName = (TextView)view.findViewById(R.id.tv_itemName);
            tv_Price = (TextView)view.findViewById(R.id.tv_Price);

        }
    }

    public OrderAdapter(Context mContext, ArrayList<OrderDetail_Modal> flatList) {
        this.mContext = mContext;
        this.orderDetail = flatList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_card, parent, false);
        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        OrderDetail_Modal album = orderDetail.get(position);
        holder.tv_itemName.setText(album.getItem_name());
        holder.tv_quantity.setText(album.getQuantity());
        holder.tv_Price.setText(album.getPrice());

    }


    @Override
    public int getItemCount() {
        return orderDetail.size();
    }


}