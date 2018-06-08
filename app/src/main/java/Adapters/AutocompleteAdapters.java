package Adapters;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.foodwayz.user.HomeActivity;
import com.foodwayz.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import Modles.Citydetail;


public class AutocompleteAdapters extends ArrayAdapter<Citydetail> implements Filterable {
        public static ArrayList<Citydetail> mCountry;
    private String COUNTRY_URL;
    ArrayList<Citydetail> Resuturentlist;
    String City ;
    public AutocompleteAdapters(Context context, int resource) {
        super(context, resource);
        mCountry = new ArrayList<>();

    }

    public AutocompleteAdapters(Context homeActivity, int simple_dropdown_item_1line, String city) {
        super(homeActivity,simple_dropdown_item_1line);
        City =city;
        COUNTRY_URL ="http://api.proaspire.com/api/Location/GetLocations/"+city+"/" ;

    }

    @Override
    public int getCount() {
        return mCountry.size();
    }

    @Override
    public Citydetail getItem(int position) {
        return mCountry.get(position);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    try {
                        //get data from the web
                        String term = constraint.toString();
                        mCountry = new DownloadCountry().execute(term).get();
                    } catch (Exception e) {
                        Log.d("HUS", "EXCEPTION " + e);
                    }
                    filterResults.values = mCountry;
                    filterResults.count = mCountry.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };

        return myFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_item_city, parent, false);

        //get Country
        Citydetail contry = mCountry.get(position);

        TextView countryName = (TextView) view.findViewById(R.id.city);
        countryName.setText(contry.getCity_name());
        return view;
    }

    //download mCountry list
    private class DownloadCountry extends AsyncTask<String, Void, ArrayList<Citydetail>> {

        @Override
        protected ArrayList<Citydetail> doInBackground(String... params) {
            try {
                //Create a new COUNTRY SEARCH url Ex "search.php?term=india"
                String NEW_URL = COUNTRY_URL + URLEncoder.encode(params[0], "UTF-8");
                Log.d("HUS", "JSON RESPONSE URL " + NEW_URL);

                URL url = new URL(NEW_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                //parse JSON and store it in the list
                String jsonString = sb.toString();
                Resuturentlist = new ArrayList<>();
////                JSONArray jsonArray = new JSONArray(jsonString);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jo = jsonArray.getJSONObject(i);
//                    //store the country name
//                    Citydetail country = new Citydetail();
//                    country.setName(jo.getString("label"));
//                    Resuturentlist.add(country);
//                }

                JSONObject json = null;
                json = new JSONObject(sb.toString());


                Log.e("All Dish List", json.toString());
                if (json != null) {
//                                 if (json.has("sfsfArray")) {
//                                    if (json.getString("sfsfArray").equals("1")) {


                    JSONArray results = json.getJSONArray("Data");
                    if (results != null) {
                        if (results.length() > 0) {
                            int i;
                            i = 0;
                            Resuturentlist = new ArrayList<Citydetail>(results.length());
                            for (i = 0; i < results.length(); i++) {

                                JSONObject jsonR = results.getJSONObject(i);
                                Citydetail psearch = new Citydetail();

                                int cat_id = jsonR.getInt("Id");
                                String Reviewcnt = jsonR.getString("Name");

                                psearch.setId(cat_id);
                                psearch.setCity_name(Reviewcnt);

                                Resuturentlist.add(psearch);

                            }
                        }


                        }

                        Log.v("dish_list", "" + Resuturentlist.size());




                } else if (json.getString("success").equals("0")) {
//                            Toast.makeText(Context, "No Data found in your location", Toast.LENGTH_LONG).show();
                }


//                                }


            } catch (Exception e) {
                Log.d("HUS", "EXCEPTION " + e);

            }

            return Resuturentlist;
        }
    }
}