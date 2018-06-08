package com.foodwayz.user.UserFreagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.foodwayz.user.HomeActivity;
import com.foodwayz.R;

/**
 * Created by Android on 15-Sep-17.
 */
public class About_Fragment extends Fragment implements View.OnClickListener {

    ImageView iv_facebook,iv_insta,iv_twitter,iv_google;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us, container, false);
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        HomeActivity.title.setVisibility(View.VISIBLE);

        HomeActivity.title.setText("About");
        findElement(view);
        return view;
    }

    private void findElement(View view) {

        iv_facebook = (ImageView)view.findViewById(R.id.iv_facebook);
        iv_insta = (ImageView)view.findViewById(R.id.iv_insta);
        iv_twitter = (ImageView)view.findViewById(R.id.iv_twitter);
        iv_google = (ImageView)view.findViewById(R.id.iv_google);

        iv_facebook.setOnClickListener(this);
        iv_insta.setOnClickListener(this);
        iv_twitter.setOnClickListener(this);
        iv_google.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.iv_facebook:
                break;

            case R.id.iv_insta:
                break;

            case R.id.iv_twitter:
                break;

            case R.id.iv_google:
                break;
        }
    }
    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    onBackPressed();
                    return true;

                }

                return false;
            }
        });
    }
    public void onBackPressed() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getActivity().getSupportFragmentManager().popBackStack();
        } else {
            getActivity().finish();
        }
    }
}
