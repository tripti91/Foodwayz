package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.foodwayz.R;
import com.foodwayz.owner.OwnerFreagment.Gallary_Management_fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by ADMIN on 1/2/2018.
 */
public class ImageListAdapter extends  RecyclerView.Adapter<ImageListAdapter.MyViewHolder>  {

    private Context context;
    private List<String> imgPic;
    public ImageListAdapter(Context c, List<String> thePic)
    {
        context = c;
        imgPic = thePic;
    }

    //---returns the ID of an item---
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }



    @Override
    public int getItemCount() {
        if(imgPic != null)
            return imgPic.size();
        else
            return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.grid_image);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallary_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BitmapFactory.Options bfOptions=new BitmapFactory.Options();
        bfOptions.inDither=false;                     //Disable Dithering mode
        bfOptions.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
        bfOptions.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
        bfOptions.inTempStorage=new byte[32 * 1024];

        FileInputStream fs = null;
        Bitmap bm;
        try {
            fs = new FileInputStream(new File(imgPic.get(position).toString()));

            if(fs!=null) {
                bm=BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
                holder.imageView.setImageBitmap(bm);
                holder.imageView.setId(position);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(fs!=null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
