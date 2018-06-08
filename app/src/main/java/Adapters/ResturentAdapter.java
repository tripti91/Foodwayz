package Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.foodwayz.R;

import java.util.ArrayList;

import Modles.Resturent_detail;

/**
 * Created by Prateek on 7/15/2017.
 */
public class ResturentAdapter extends RecyclerView.Adapter<ResturentAdapter.ViewHolder> {
    private ArrayList<Resturent_detail> countries;
    String ischecked = "true";
Context mcontext;

    public ResturentAdapter(FragmentActivity activity,ArrayList<Resturent_detail> countries) {
        this.countries = countries;
       this.mcontext=activity;
    }

    Resturent_detail res_detail;
    String location = "", locations = "";



    @Override
    public ResturentAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resturent_list_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResturentAdapter.ViewHolder viewHolder, int position) {
        res_detail = countries.get(position);
        viewHolder.txt_resturent_name.setText(res_detail.getResturent_name());
        viewHolder.txt_rateit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowReviewdialog();
            }
        });

        for (int i = 1; i < res_detail.getLocation().size(); i++) {
            if (i <= 3) {
                location = res_detail.getLocation().get(i);
                if (locations.length() <= 0) {
                    locations = location + locations;
                } else {
                    locations = locations + "," + location;
                }
            } else {
                viewHolder.txt_more.setVisibility(View.VISIBLE);
            }
        }
        viewHolder.txt_discreption.setText(res_detail.getArea());
        viewHolder.txt_area.setText(locations);
        viewHolder.txt_rateingcount.setText(res_detail.getRateing_count());
        viewHolder.rating_write_review.setRating(res_detail.getRateing());
        viewHolder.txt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ischecked.equals("true")) {
                    ischecked = "false";
                    locations = "";
                    location = "";
                    for (int i = 1; i < res_detail.getLocation().size(); i++) {

                        location = res_detail.getLocation().get(i);
                        if (locations.length() <= 0) {
                            locations = locations + location;
                        } else {
                            locations = locations + "," + location;
                        }
                    }
                    viewHolder.txt_area.setText(locations);

                } else if (ischecked.equals("false")) {
                    ischecked = "true";
                    locations = "";
                    location = "";
                    viewHolder.txt_more.setVisibility(View.VISIBLE);
                    for (int i = 1; i < res_detail.getLocation().size(); i++) {
                        if (i <= 3) {
                            location = res_detail.getLocation().get(i);
                            if (locations.length() <= 0) {
                                locations = locations + location;
                            } else {
                                locations = locations + "," + location;
                            }

                        }

                    }
                    viewHolder.txt_area.setText(locations);

                }
            }
        });


    }
    private void ShowReviewdialog() {
        final Dialog mDialog = new Dialog(mcontext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setContentView(R.layout.feedback);

        mDialog.show();
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RatingBar rating_write_review;
        TextView txt_resturent_name, txt_rateit, txt_discreption, txt_area, txt_rateingcount, txt_more;
        LinearLayout linear_rate_it;

        public ViewHolder(View view) {
            super(view);
            txt_rateit = (TextView) view.findViewById(R.id.rateit);
            txt_resturent_name = (TextView) view.findViewById(R.id.txt_resturent_name);
            txt_discreption = (TextView) view.findViewById(R.id.txt_discreption);
            txt_more = (TextView) view.findViewById(R.id.txt_more);
            txt_area = (TextView) view.findViewById(R.id.txt_area);
            txt_rateingcount = (TextView) view.findViewById(R.id.txt_rateingcount);
            rating_write_review = (RatingBar) view.findViewById(R.id.rating_write_review);
            linear_rate_it = (LinearLayout) view.findViewById(R.id.linear_rate_it);

        }
    }

}
