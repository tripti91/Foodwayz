package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import Modles.Citydetail;

/**
 * Created by Tripti on 19/07/2017.
 */
public class SpinAdapter extends ArrayAdapter<Citydetail> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<Citydetail> values;



    public SpinAdapter(Context context, int simple_spinner_item, ArrayList<Citydetail> city_detail) {
        super(context, simple_spinner_item, city_detail);
        this.context = context;
        this.values=city_detail;
    }


    public int getCount(){
        return values.size();
    }

    public Citydetail getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getCity_name());
        label.setPadding(8, 8, 8, 8);
        label.setTextSize(14);
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setPadding(8,8,8,8);
        label.setTextSize(14);
        label.setText(values.get(position).getCity_name());

        return label;
    }
}