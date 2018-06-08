package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodwayz.R;

import java.util.ArrayList;

import Modles.OrderList_Modal;

/**
 * Created by Android on 23-Oct-17.
 */
public class OrderList_ownerAdapter extends RecyclerView.Adapter<OrderList_ownerAdapter.ViewHolder> {


    private ArrayList<OrderList_Modal> orderList_modals;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_total,tv_order_id,tv_pack_name,tv_description,tv__name,tv_date;

        public ViewHolder(View view) {
            super(view);
            tv_total = (TextView)view.findViewById(R.id.tv_total);
            tv_order_id = (TextView)view.findViewById(R.id.tv_order_id);
            tv_pack_name = (TextView)view.findViewById(R.id.tv_pack_name);
            tv_description = (TextView)view.findViewById(R.id.tv_description);
            tv__name = (TextView)view.findViewById(R.id.name);
            tv_date = (TextView)view.findViewById(R.id.date);

        }
    }

    public OrderList_ownerAdapter(Context mContext, ArrayList<OrderList_Modal> flatList) {
        this.mContext = mContext;
        this.orderList_modals = flatList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_order_list, parent, false);
        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        OrderList_Modal album = orderList_modals.get(position);
        holder.tv_total.setText(album.getTotal());
        holder.tv_order_id.setText(album.getOrder_id());
        holder.tv_pack_name.setText(album.getPack_name());
        holder.tv__name.setText("Customer Name");
        holder.tv_date.setText("Date");

        // holder.tv_description.setText(album.getDescription());

    }

    @Override
    public int getItemCount() {
        return orderList_modals.size();
    }
}
