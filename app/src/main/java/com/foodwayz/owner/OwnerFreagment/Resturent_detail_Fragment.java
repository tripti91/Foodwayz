package com.foodwayz.owner.OwnerFreagment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.foodwayz.owner.HomeActivity;
import com.foodwayz.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 18/09/16.
 */


public class Resturent_detail_Fragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    //private MapView mapView;
  //  private GoogleMap mMap;
    double latitude;
    double longitude;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private ImageView background_image;
    TextView tv_get_direction,rating_write_review;
Button bt_Order_now;
    TextView bt_order_food_now;
    ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resturent_detail, container, false);
        HomeActivity.action_bar_share.setVisibility(View.VISIBLE);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView);
        HomeActivity.title.setVisibility(View.VISIBLE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);

        tv_get_direction = (TextView)view.findViewById(R.id.tv_get_direction);
        rating_write_review= (TextView)view.findViewById(R.id.rating_write_review);
        bt_Order_now= (Button)view.findViewById(R.id.bt_Order_now);
        bt_Order_now.setOnClickListener(this);
        bt_order_food_now= (TextView)view.findViewById(R.id.bt_order_food_now);
        bt_order_food_now.setOnClickListener(this);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        HomeActivity.title.setVisibility(View.VISIBLE);

        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        background_image = (ImageView) view.findViewById(R.id.background_image);
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),
                R.mipmap.splash);
        icon = processImage(icon);
        BitmapDrawable ob = new BitmapDrawable(getResources(), icon);
//        mapView = (MapView) view.findViewById(R.id.map);
//        mapView.onCreate(savedInstanceState);
//        mapView.onResume();
//        mapView.getMapAsync(this);
       tv_get_direction.setOnClickListener(this);
        rating_write_review.setOnClickListener(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new Review_fragment(), "Reviews");
        return view;

    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setTextColor(Color.parseColor("#333333"));
        tabOne.setGravity(Gravity.CENTER);
        tabOne.setText("Reviews");
        tabOne.setTextSize(12);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setTextColor(Color.parseColor("#333333"));
        tabTwo.setGravity(Gravity.CENTER);
        tabTwo.setText("About");
        tabLayout.getTabAt(1).setCustomView(tabTwo);


        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabThree.setTextColor(Color.parseColor("#333333"));
        tabThree.setGravity(Gravity.CENTER);
        tabThree.setText("Gallary");
        tabLayout.getTabAt(2).setCustomView(tabThree);


    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new Review_fragment(), "Reviews");
        adapter.addFrag(new About_ResturentDetail_Fragment(), "Abouts");
        adapter.addFrag(new Gallary_fragment(), "Gallarys");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_get_direction:
                //scrollView.fullScroll(View.FOCUS_DOWN);
                Bundle bundle = new Bundle();
                bundle.putString("Lat", "22.7196");
                bundle.putString("Log", "75.8577");
                Fragment fragments = new GetDirection_Fragment();
                fragments.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragments);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.rating_write_review:
                ShowReviewdialog();

                break;
            case R.id.btn_order:
                Fragment fragment = new OrderPlace_Fragment();
                FragmentManager fragmentManagers = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactions = fragmentManagers.beginTransaction();
                fragmentTransactions.replace(R.id.content_frame, fragment);
                fragmentTransactions.addToBackStack("back");
                fragmentTransactions.commit();

                break;
            case R.id.bt_order_food_now:
                Fragment fragment_ = new OrderPlace_Fragment();
                FragmentManager fragmentManager_ = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_ = fragmentManager_.beginTransaction();
                fragmentTransaction_.replace(R.id.content_frame, fragment_);
                fragmentTransaction_.addToBackStack("back");
                fragmentTransaction_.commit();

                break;


        }
    }

    private void ShowReviewdialog() {
        final Dialog mDialog = new Dialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.feedback);

       /* spinner = (Spinner) mDialog.findViewById(R.id.spinner);
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

                Bundle bundle =new Bundle();
                bundle.putString("City",City);
                bundle.putString("Area",Area);
                Fragment fragments = new Menu1();
                fragments.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragments);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                mDialog.dismiss();
                City ="";
                Area="";
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
                Area= adapter.getItem(position).getCity_name();
                System.out.println("countryName---->"+Area);
                tv_Address.setText(Area);
            }
        });*/
        mDialog.show();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            requestReadPhoneMapPermission();
            requestMap_access_fine_location();

            return;
        }
      //  mMap = googleMap;
        //  mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 2.6), 200, null);
        // mMap.getUiSettings().setMapToolbarEnabled(false);
        //  mMap.getUiSettings().setZoomControlsEnabled(false);
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //  mMap.setMyLocationEnabled(true);
//        LocationManager lm = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
//        List<String> providers = lm.getProviders(true);
//        Location l = null;
//
//        for (int i = 0; i < providers.size(); i++) {
//            l = lm.getLastKnownLocation(providers.get(i));
//            if (l != null) {
//                latitude = l.getLatitude();
//                longitude = l.getLongitude();
//                break;
//            }
//        }
//
//        if (mMap != null) {
//
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(new LatLng(22.7196, 75.8577)).zoom(15).build();
//            mMap.animateCamera(CameraUpdateFactory
//                    .newCameraPosition(cameraPosition));
//        }


    }

    private void requestReadPhoneMapPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)) {

            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Req")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                        }
                    })
                            //  .setIcon(R.drawable.arrow)
                    .show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }
    }

    private void requestMap_access_fine_location() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Req")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        }
                    })
                            // .setIcon(R.drawable.icon)
                    .show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private Bitmap processImage(Bitmap bitmap) {
        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap,
                BitmapShader.TileMode.CLAMP,
                BitmapShader.TileMode.CLAMP);

        float radius = bitmap.getWidth();
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        RectF rect = new RectF(-bitmap.getWidth() / 2f, 0,
                bitmap.getWidth() / 2f, bitmap.getHeight());
        canvas.drawOval(rect, paint);

        return bmp;
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
