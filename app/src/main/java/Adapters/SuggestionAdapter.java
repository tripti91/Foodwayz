package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.foodwayz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import Modles.Citydetail;

/**
 * Created by Tripti on 21/07/2017.
 */
public class SuggestionAdapter extends ArrayAdapter<Citydetail> {
    private String apikey;
    Context context;
    int layoutResourceId;
    private URLConnection ucon;
    private String mshost;
    ArrayList<Citydetail> data = new ArrayList<Citydetail>();
    ArrayList<Citydetail> resultList = null;
    String city = "";
    String userid = "";
    String srchmem = "";
    private String prev_last_cat_id = "";

    public SuggestionAdapter(Context context, int layoutResourceId, String category) {
        super(context, layoutResourceId);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.city = category;
    }
    @Override
    public int getCount() {
        if (resultList == null) {
            return 0;
        } else {
            return resultList.size();
        }
    }

    @Override
    public Citydetail getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return resultList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_city, null);
        }
        Citydetail o = resultList.get(position);
        if (o != null) {
            TextView catID = (TextView) v.findViewById(R.id.city);
            catID.setText(o.getCity_name());
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    try {

                        resultList = autocomplete(constraint.toString());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    if (resultList == null) {
                        filterResults.count = 0;
                    } else {
                        filterResults.count = resultList.size();
                    }
                }
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;
    }

    @SuppressWarnings("deprecation")
    private ArrayList<Citydetail> autocomplete(String input) throws IOException {
        ArrayList<Citydetail> resultList = null;
            String url1 = "http://abc.itractechnology.com/api/Location/GetLocations/"+city+"/" + input;//append the input string with URL
            try {
                URL myURL = new URL(url1);

                ucon = myURL.openConnection();
                ucon.setRequestProperty("Content-Language", "en-US");
                ucon.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                ucon.setRequestProperty("Connection", "Keep-Alive");

    /* Define InputStreams to read  * from the URLConnection. */
                InputStream is = ucon.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                if (sb != null) {
                    if (!sb.toString().contains("failure") || !sb.toString().equalsIgnoreCase("")) {
                        JSONObject jsonResponse;

                        jsonResponse = new JSONObject(sb.toString());

                        JSONArray results = jsonResponse.getJSONArray("Data");
                        if (results != null) {
                            if (results.length() > 0) {
                                int i;
                                i = 0;
                                resultList = new ArrayList<Citydetail>(results.length());
                                for (i = 0; i < results.length(); i++) {

                                    JSONObject jsonR = results.getJSONObject(i);
                                    Citydetail psearch = new Citydetail();

                                    int cat_id = jsonR.getInt("Id");
                                    String Reviewcnt = jsonR.getString("Name");

                                    psearch.setId(cat_id);
                                    psearch.setCity_name(Reviewcnt);

                                    resultList.add(psearch);

                                }
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        return resultList;
    }


}