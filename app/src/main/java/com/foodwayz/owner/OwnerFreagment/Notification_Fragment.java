package com.foodwayz.owner.OwnerFreagment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.foodwayz.owner.HomeActivity;
import com.foodwayz.R;

import java.util.ArrayList;

/**
 * Created by Android on 23-Oct-17.
 */
public class Notification_Fragment extends Fragment {


    private SwipeMenuListView mListView;

    private ArrayList<String> mArrayList = new ArrayList<>();

    private ListDataAdapter mListDataAdapter;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.notification_fragment, container, false);
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);

        HomeActivity.location.setVisibility(View.GONE);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.title.setText("Notification");
        initControls(view);
        return view;
    }

    private void initControls(View view) {
        mListView = (SwipeMenuListView) view.findViewById(R.id.listView);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
        mListView.setPadding(0,10,0,10);
        for (int i = 0; i < 5; i++) {
            mArrayList.add("Apna Lunch New Thali Menu " + i);
        }
        // mListView.setCloseInterpolator(new BounceInterpolator());

        mListDataAdapter = new ListDataAdapter(R.color.colorPrimary,R.color.color_hint_light);
        mListView.setAdapter(mListDataAdapter);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
//                SwipeMenuItem goodItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                goodItem.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
//                        0xF5)));
//                // set item width
//                goodItem.setWidth(dp2px(90));
//                // set a icon
//                goodItem.setIcon(R.drawable.ic_action_good);
//                // add to menu
//                menu.addMenuItem(goodItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                // deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                deleteItem.setBackground(new ColorDrawable(getResources().getColor(R.color.red)));
                // set item width

                
                deleteItem.setWidth(dp2px(80));

                // set a icon
                deleteItem.setIcon(R.drawable.delete_icon);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        new AlertDialog.Builder(getActivity())
                                .setMessage("Do you to want to delete notification or not?")
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                       }
                                )
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                mArrayList.remove(position);
                                                mListDataAdapter.notifyDataSetChanged();
                                                Toast.makeText(

                                                        getActivity(),

                                                        "Item deleted", Toast.LENGTH_SHORT).

                                                        show();

                                                dialog.cancel();
                                            }
                                        }
                                )
                                           .
                                   show();

                                    break;
                                }
                        return true;
            }
        });

        //mListView

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });


    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    class ListDataAdapter extends BaseAdapter {
        int mOddBackground,mEvenBackground;
        ViewHolder holder;

        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        public ListDataAdapter(@DrawableRes int oddBackground, @DrawableRes int evenBackground) {
             mOddBackground = oddBackground;
             mEvenBackground = evenBackground;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {

                holder = new ViewHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item, null);
                holder.mTextview = (TextView) convertView.findViewById(R.id.textView);
                holder.background_image= (LinearLayout) convertView.findViewById(R.id.background_image);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.mTextview.setText(mArrayList.get(position));
            if ( position % 2 == 0) {
                holder.background_image.setBackgroundColor(Color.WHITE);
            } else {
                holder.background_image.setBackgroundColor(getResources().getColor(R.color.gray));
            }
            return convertView;
        }


        class ViewHolder {

            TextView mTextview;
           LinearLayout background_image;
        }
    }
}
