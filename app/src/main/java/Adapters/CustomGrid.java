package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodwayz.R;

import java.util.ArrayList;

/**
 * Created by Tripti on 03/08/2017.
 */
public class CustomGrid extends RecyclerView.Adapter<CustomGrid.MyViewHolder> {
    private Context mContext;

    private final int[] Imageid;

    public CustomGrid(Context c, int[] Imageid) {
        mContext = c;
        this.Imageid = Imageid;

    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return Imageid.length;
    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
      public    ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.grid_image);
        }
    }

    public static String[] removeElement(String[] array, String key)
    {
        int found = 0;
        for (String s : array)
        {
            if (s.equals(key))
                found++;
        }
        if (found == 0) return array;
        String[] temp = new String[array.length-found];
        int x = 0;
        for (String s : array)
        {
            if (s.equals(key))
                continue;
            temp[x++] = s;
        }

        return temp;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallary_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.imageView.setBackgroundResource(Imageid[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg1) {
                // list.remove(position);
            }
        });
    }



}
// RecyclerView.Adapter<CustomGrid.MyViewHolder> {
//    private Context mContext;
//
//    private final int[] Imageid;
//
//    public CustomGrid(Context c,int[] Imageid ) {
//        mContext = c;
//        this.Imageid = Imageid;
//
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return Imageid.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        View grid;
//        LayoutInflater inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null) {
//
//            grid = new View(mContext);
//            grid = inflater.inflate(R.layout.gallary_row, null);
//            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
//            imageView.setImageResource(Imageid[position]);
//        } else {
//            grid = (View) convertView;
//        }
//
//        return grid;
//    }
//}