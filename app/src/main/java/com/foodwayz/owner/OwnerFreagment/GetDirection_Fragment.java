package com.foodwayz.owner.OwnerFreagment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodwayz.owner.HomeActivity;
import com.foodwayz.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Android on 20-Sep-17.
 */
public class GetDirection_Fragment extends Fragment implements OnMapReadyCallback {


    private MapView mapView;
    private GoogleMap mMap;
    double latitude;
    double longitude;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    String Lat,Log;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_direction_fragment, container, false);
        HomeActivity.city.setVisibility(View.GONE);
        HomeActivity.location.setVisibility(View.GONE);
        HomeActivity.action_bar_delete.setVisibility(view.GONE);





        HomeActivity.title.setVisibility(View.VISIBLE);

        HomeActivity.title.setText("Direction");
        HomeActivity.drawable_icon = ResourcesCompat.getDrawable(getResources(), R.drawable.back, getActivity().getTheme());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            latitude = Double.parseDouble(bundle.getString("Lat"));
            longitude = Double.parseDouble(bundle.getString("Log"));

        }
        mapView = (MapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           // TODO: Consider calling
           // requestReadPhoneMapPermission();
           // requestMap_access_fine_location();
            return;
        }
        mMap = googleMap;
        //  mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 2.6), 200, null);
        // mMap.getUiSettings().setMapToolbarEnabled(false);
        //  mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //mMap.setMyLocationEnabled(true);
       /* LocationManager lm = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location l = null;
        for (int i = 0; i < providers.size(); i++) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) {
                latitude = l.getLatitude();
                longitude = l.getLongitude();
                break;
            }
        }*/

        if (mMap != null) {

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude)).zoom(8).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions().position(
                    new LatLng(latitude, longitude)).icon(
                    BitmapDescriptorFactory.defaultMarker()));

        }

    }
}
