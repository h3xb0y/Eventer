package xebia.ismail.e_learning.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import xebia.ismail.e_learning.CompanyActivity;
import xebia.ismail.e_learning.R;



public class TabMap extends Fragment implements OnMapReadyCallback  {

    public static ArrayList<Points> itemlist;



    @Override
     public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.geometry_cube, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
               CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(48.531711, 35.870047))
                   .zoom(15)
                    .bearing(45)
                   .tilt(20)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(cameraUpdate);
        googleMap.setMinZoomPreference(13f);


        for (int i =0; i<itemlist.size();i++){
            Points item = itemlist.get(i);
//            int mrk = R.drawable.museum;
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.valueOf(item.getPosx()), Double.valueOf(item.getPosy())))
                    .snippet(item.getDescription())
                    .icon(BitmapDescriptorFactory.fromResource(MarkerChange(item.getType())))
                    .title(item.getName()));

        }


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getActivity(), CompanyActivity.class);
                CompanyActivity.Info.name = marker.getTitle();
                CompanyActivity.Info.id = marker.getId();
                CompanyActivity.Info.info = marker.getSnippet();
                TabInfo.name = marker.getTitle();
                TabInfo.id = marker.getId();
                TabInfo.info = marker.getSnippet();
                for (int i = 0;i<24;i++ ){
                    if (marker.getId().equals("m"+i)){ CompanyActivity.Info.num=i;TabInfo.num=i;TabEvents.num=i;}
                }
                TabMap.this.startActivity(intent);


            }
        });
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this.getContext(), R.raw.style_json);
        googleMap.setMapStyle(style);


    }
    private int MarkerChange(String type){
        int a = R.drawable.museum;
        switch (type) {
            case "museum":
                a = R.drawable.museum;
            break;
            case "eat":
                a =  R.drawable.eat;
            break;
            case "dk":
                a =  R.drawable.dk;
            break;
            case "park":
                a =  R.drawable.park;
            break;
            case "gym":
                a =  R.drawable.gym;
            break;
            case "library":
                a =  R.drawable.library;
            break;
            case "market":
                a =  R.drawable.market;
            break;
            case "club":
                a =  R.drawable.club;
            break;
            case "cinema":
                a =  R.drawable.cinema;
            break;
        }
        return a;
    }




}

