package com.foodwayz.user;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.foodwayz.R;
import com.network.CustomPostRequest;
import com.network.IHttpRequestCallBack;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Adapters.AutocompleteAdapters;
import Adapters.SpinAdapter;
import Modles.Citydetail;
import com.foodwayz.user.UserFreagment.About_Fragment;
import com.foodwayz.user.UserFreagment.ContactUs_Fragment;
import com.foodwayz.user.UserFreagment.Menu1;
import com.foodwayz.user.UserFreagment.Notification_Fragment;
import com.foodwayz.user.UserFreagment.Offer_Fragment;
import com.foodwayz.user.UserFreagment.OrderPlace_Fragment;
import com.foodwayz.user.UserFreagment.Review_detail_fragment;
import jsonparser.JsonParser;
import utills.CircleTransform;
import utills.Constant;
import utills.LocationTrack;
import utills.SessionManager;

import static android.R.layout.simple_spinner_item;

/**
 * Created by Tripti on 08/07/2017.
 */
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IHttpRequestCallBack,LocationListener {
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyA9oyIQ1tHsEpD_s-xRU9eA4UC2GdcMCWQ";
    SessionManager session;
    int mId;
    public static TextView title, city, location;
    Fragment fragment;
    private static TextView logout;
    public static ImageView action_bar_share;
    ArrayList<Citydetail> city_detail = new ArrayList<>();
    public static ArrayList<Modles.Resturent_detail> Restutrnt_Detail = new ArrayList<>();
    String City = "";
    String Area = "";
    AutocompleteAdapters adapter;
    private ProgressDialog progress_dialog = null;
    public static Drawable drawable_icon;
    Spinner spinner;
    AutoCompleteTextView tv_Address;
    Button bt_Search_food;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationTrack locationTrack = new LocationTrack(HomeActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);
        if (progress_dialog == null) {
            progress_dialog = new ProgressDialog(this);
        }
        progress_dialog.setMessage("Please wait...");
        progress_dialog.setCancelable(true);
        progress_dialog.setCanceledOnTouchOutside(false);
        showProgressDialog();

        SearchDialog();

        Getcitylist();
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        Fragment fragment = new Menu1();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        drawable_icon = ResourcesCompat.getDrawable(getResources(), R.drawable.bar, getTheme());
        toggle.setHomeAsUpIndicator(drawable_icon);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(drawable_icon);
        logout = (TextView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.logoutUser();
                Intent in = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(in);
                finish();

            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(session.getRole().equals("admin"))
        {
            Menu menu = navigationView.getMenu();

            // Add items to the second group, and set to visible
            menu.add(R.id.nav_offer, 2, 200, "Existing Orders List");
            menu.add(R.id.nav_near_by_resturent, 3, 300, "Completed Orders List ");


        }


        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView txt_name = (TextView) headerView.findViewById(R.id.user_name);
        ImageView iv_profile = (ImageView) headerView.findViewById(R.id.imageView3);
        Picasso.with(getApplicationContext()).load(R.drawable.profile).transform(new CircleTransform()).into(iv_profile);
        txt_name.setText(session.getusername().get(Constant.SHARED_PREFERENCE_REGISTER_NAME_KEY));
        //add this line to display menu1 when the activity is loaded
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();
        displaySelectedScreen(R.id.nav_near_by_resturent);
        ImageView imageButton2 = (ImageView) view.findViewById(R.id.action_bar_forward);
        action_bar_share = (ImageView) view.findViewById(R.id.action_bar_share);
        title = (TextView) view.findViewById(R.id.title);
        city = (TextView) view.findViewById(R.id.city);
        location = (TextView) view.findViewById(R.id.location);
        location.setText("hello");
        action_bar_share.setVisibility(View.GONE);
        if (locationTrack.canGetLocation()) {


            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            if(!(latitude==0.0)) {
                String locations = getCompleteAddressString(latitude, longitude);
            }
        } else {

            locationTrack.showSettingsAlert();
        }


        if (locationTrack.canGetLocation()) {


            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            if (!(latitude==0.0)) {
                String locations = getCompleteAddressString(latitude, longitude);
            }


        } else {

            locationTrack.showSettingsAlert();
        }

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SearchDialog();
            }


        });

    }
       private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(HomeActivity.this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            location.setText(address);

            strAdd = address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strAdd;
    }

    private void SearchDialog() {
        hideProgressDialog();
        final Dialog mDialog = new Dialog(HomeActivity.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setContentView(R.layout.search_dialog);
        spinner = (Spinner) mDialog.findViewById(R.id.spinner);
        bt_Search_food = (Button) mDialog.findViewById(R.id.bt_Search_food);
        SpinAdapter dataAdapter = new SpinAdapter(HomeActivity.this,
                simple_spinner_item,
                city_detail);                // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                City = city_detail.get(arg2).getCity_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        bt_Search_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("City", City);
                bundle.putString("Area", Area);
                Fragment fragments = new Menu1();
                fragments.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragments);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                mDialog.dismiss();
                City = "";
                Area = "";
            }
        });


        tv_Address = (AutoCompleteTextView) mDialog.findViewById(R.id.tv_Address);
        tv_Address.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter = new AutocompleteAdapters(HomeActivity.this, android.R.layout.simple_dropdown_item_1line, City);
                tv_Address.setAdapter(adapter);
                tv_Address.setThreshold(1);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        tv_Address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Area = adapter.getItem(position).getCity_name();
                System.out.println("countryName---->" + Area);
                tv_Address.setText(Area);
            }
        });
        mDialog.show();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        fragment = null;
        String title = "";
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_notification:
                fragment = new Notification_Fragment();
                break;
            case R.id.nav_offer:
                title = "Offer Detail";
                fragment = new Offer_Fragment();
                break;
            case R.id.nav_near_by_resturent:
                title = "Offer Detail";
                fragment = new Menu1();
                break;
            case R.id.nav_order:
                fragment = new OrderPlace_Fragment();
                break;
            case R.id.nav_reviews:
                session.setmyreview(true);
                fragment = new Review_detail_fragment();
                break;
            case R.id.complet_order:
                fragment = new OderList_Fragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.setting:
                //  fragment = new Gallary_fragment();
                break;
            case R.id.about:
                fragment = new About_Fragment();
                break;
            case R.id.nav_contact:
                fragment = new ContactUs_Fragment();
                break;
           /* case R.id.nav_favorite_resturent:
                fragment = new Offer_Fragment();
                break;*/
            case R.id.nav_share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");
                startActivity(Intent.createChooser(share, "Share link!"));

                break;
            case R.id.nav_send:
                fragment = new Offer_Fragment();
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    @Override
    public void requestSuccessful(String aDataStr) {
        hideProgressDialog();
        System.out.println("aDataStr---------->" + aDataStr);
        try {
            JSONObject jsonObject = new JSONObject(aDataStr);

            if (jsonObject.has(Constant.JSON_MESSAGE_TAG))
                mId = jsonObject.getInt(Constant.JSON_APP_ID_TAG);
            if (mId == 5) {
                city_detail = JsonParser.parseCountry(aDataStr);
                SearchDialog();
            }
            if (mId == 9) {
                Restutrnt_Detail = JsonParser.parsseResturentlist(aDataStr);
                hideProgressDialog();


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestFailure() {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    class GooglePlacesAutocompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
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
            return filter;
        }
    }


    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
//			sb.append("&components=country:gr");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());

            System.out.println("URL: " + url);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e("11111", "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e("11111", "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e("11111", "Cannot process JSON results", e);
        }

        return resultList;
    }

    private void Getcitylist() {
        CustomPostRequest httpRequest = new CustomPostRequest(HomeActivity.this, getResources().getString(R.string.URL_GETCITY), null);
        httpRequest.execute();
    }

    public void showProgressDialog() {

        if (!progress_dialog.isShowing()) {
            progress_dialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progress_dialog.isShowing()) {
            progress_dialog.dismiss();
        }
    }
}
