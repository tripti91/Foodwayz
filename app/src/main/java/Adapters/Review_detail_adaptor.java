package Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.foodwayz.R;

import java.util.ArrayList;

import Modles.Resturent_detail;
import utills.SessionManager;

/**
 * Created by Prateek on 7/15/2017.
 */
public class Review_detail_adaptor extends RecyclerView.Adapter<Review_detail_adaptor.ViewHolder> {
    private ArrayList<Resturent_detail> resturent_details;
    SessionManager sessionManager;
    Context mcontext;

    public Review_detail_adaptor(ArrayList<Resturent_detail> countries) {
    }

    public Review_detail_adaptor(FragmentActivity activity, ArrayList<Resturent_detail> resturent_detail) {
        this.mcontext = activity;
        this.resturent_details = resturent_detail;

    }

    @Override
    public Review_detail_adaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_detail_lyt, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Review_detail_adaptor.ViewHolder viewHolder, int position) {
        Resturent_detail res_detail = resturent_details.get(position);
        sessionManager = new SessionManager(mcontext);
        sessionManager.getmyreview();
        if (sessionManager.getmyreview()) {
            viewHolder.txt_user_name.setText("Apna Resturent");
            viewHolder.editview.setVisibility(View.VISIBLE);
            viewHolder.rating_write_review.setRating(4);
        } else {
            viewHolder.txt_user_name.setText("Tripti");
            viewHolder.editview.setVisibility(View.GONE);
            viewHolder.rating_write_review.setRating(4);
        }
        viewHolder.editview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ShowReviewdialog();
                return false;
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
        return resturent_details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RatingBar rating_write_review;
        TextView txt_user_name, txt_review;
        ImageView editview;

        public ViewHolder(View view) {
            super(view);

            txt_user_name = (TextView) view.findViewById(R.id.txt_name);
            txt_review = (TextView) view.findViewById(R.id.txt_discreption);
            editview = (ImageView) view.findViewById(R.id.edit_id);


            rating_write_review = (RatingBar) view.findViewById(R.id.rating_write_review);

        }
    }

}
