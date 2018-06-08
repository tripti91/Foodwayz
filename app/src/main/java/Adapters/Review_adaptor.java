package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.foodwayz.R;

import java.util.ArrayList;

import Modles.Resturent_detail;

/**
 * Created by Prateek on 7/15/2017.
 */
public class Review_adaptor extends RecyclerView.Adapter<Review_adaptor.ViewHolder> {
    private ArrayList<Resturent_detail> countries;

    public Review_adaptor(ArrayList<Resturent_detail> countries) {
        this.countries = countries;
    }

    @Override
    public Review_adaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_row_lyt, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Review_adaptor.ViewHolder viewHolder, int position) {
        Resturent_detail res_detail = countries.get(position);
        viewHolder.txt_user_name.setText("Tripti");
      //  viewHolder.txt_discreption.setText(res_detail.get);
        viewHolder.rating_write_review.setRating(4);

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RatingBar rating_write_review;
        TextView txt_user_name,txt_review;
        public ViewHolder(View view) {
            super(view);

            txt_user_name = (TextView)view.findViewById(R.id.txt_name);
            txt_review = (TextView)view.findViewById(R.id.txt_discreption);
              rating_write_review= (RatingBar) view.findViewById(R.id.rating_write_review);

        }
    }

}
