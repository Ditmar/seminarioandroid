package com.example.ditmar.bienesyraicesgoogle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ditmar.bienesyraicesgoogle.DATA.DataApp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private View ROOT;
    private GoogleMap mMap;
    private ListFragment lista;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ROOT =inflater.inflate(R.layout.activity_maps, container, false);
        //SupportMapFragment mapFragment = (SupportMapFragment)this.getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);


        return ROOT;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void setListFragment() {
        if (DataApp.LISTDATA != null && DataApp.LISTDATA.size() > 0) {
            for (int i = 0; i < DataApp.LISTDATA.size(); i++) {
                LatLng position = new LatLng(DataApp.LISTDATA.get(i).getLat(), DataApp.LISTDATA.get(i).getLon());

                mMap.addMarker(new MarkerOptions().position(position).title(DataApp.LISTDATA.get(i).getStreet()));

            }
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView map =  (MapView) ROOT.findViewById(R.id.map);
        if (map != null) {
            map.onCreate(null);
            map.onResume();
            // Set the map ready callback to receive the GoogleMap object
            map.getMapAsync(this);
        }
        //map.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng potosi = new LatLng(-19.578297, -65.758633);

        //LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 14));


    }
}
