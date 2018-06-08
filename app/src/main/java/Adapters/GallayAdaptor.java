/**
 *
 */
package Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodwayz.R;

import java.util.ArrayList;

import Modles.GallaryData;

public class GallayAdaptor extends RecyclerView.Adapter<GallayAdaptor.MyViewHolder> {
    private LayoutInflater inflater;
    public static int seletedPosition = 0;
    private ArrayList<GallaryData> Imageid;
    Activity maActivity;

    public GallayAdaptor(FragmentActivity activity, ArrayList<GallaryData> foodId) {
        this.Imageid = foodId;
        maActivity = activity;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void deleteItem(int index) {

        Imageid.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout Rl;
        ImageView imageView1;
        CheckBox itemCheckBox;

        public MyViewHolder(View view) {
            super(view);
            Rl = (RelativeLayout) view.findViewById(R.id.relativeLayout);
            itemCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallary_rowdetail, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.itemCheckBox
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton view,
                                                 boolean isChecked) {
                        int getPosition = (Integer) view.getTag();
                        Imageid.get(getPosition).setIsSelected(view.isChecked());

                    }
                });


        holder.Rl.setBackground(maActivity.getResources().getDrawable(Imageid.get(position).getDrawable()));
        holder.Rl.setTag(position);
        holder.itemCheckBox.setTag(position);
        holder.itemCheckBox.setChecked(Imageid.get(position).isSelected());
    }

    @Override
    public int getItemCount() {
        return Imageid.size();
    }

    public ArrayList<GallaryData> getimage() {
        return Imageid;
    }
}


