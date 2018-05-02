package com.example.misstank.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList markerPoints= new ArrayList();
    int numClicks=0;


    int red_color = Color.RED;
    int green_color = Color.GREEN;

    int globalColor= red_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        welcomeMessage();

    }


//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//
//
//
//        LatLng portugal1 = new LatLng(38.722540,  -9.139349);
//        LatLng portugal2 = new LatLng( 38.736828, -9.139021);
//
//        mMap.addMarker(new MarkerOptions().position(portugal1).title("Campo Mártires da Pátria"));
//        mMap.addMarker(new MarkerOptions().position(portugal2).title("Instituto Superior Técnico"));
//
//        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        float zoomLevel = 15.0f; //This goes up to 21
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(portugal1, zoomLevel));
//    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));

        System.out.println("You cliked on teh moap ************");
        LatLng portugal1 = new LatLng(38.722540,  -9.139349);
        LatLng portugal2 = new LatLng( 38.736828, -9.139021);
        LatLng portugal3 = new LatLng(  38.758308, -9.153932);
        LatLng portugal4 = new LatLng(  38.763315, -9.129625);

        LatLng centerFocus = new LatLng( 38.742804, -9.147020);


        mMap.addMarker(new MarkerOptions().position(portugal1).title("Campo Mártires da Pátria"));
        mMap.addMarker(new MarkerOptions().position(portugal2).title("Instituto Superior Técnico"));
        mMap.addMarker(new MarkerOptions().position(portugal3).title("Campo Grande Garden"));
        mMap.addMarker(new MarkerOptions().position(portugal4).title("Circle"));

        float zoomLevel = 13.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerFocus, zoomLevel));

        String url = getDirectionsUrl(portugal1, portugal2);
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(url);



        String url2 = getDirectionsUrl(portugal2, portugal4);
        DownloadTask downloadTask2 = new DownloadTask();
        downloadTask2.execute(url2);




        String url3 = getDirectionsUrl(portugal4, portugal3);
        DownloadTask downloadTask3 = new DownloadTask();
        downloadTask3.execute(url3);


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                LatLng portugal1 = new LatLng(38.722540,  -9.139349);
                LatLng portugal2 = new LatLng( 38.736828, -9.139021);
                LatLng portugal3 = new LatLng(  38.758308, -9.153932);
                LatLng portugal4 = new LatLng(  38.763315, -9.129625);
                LatLng centerFocus = new LatLng( 38.742804, -9.147020);

                numClicks++;



                if(numClicks==1)
                {
                    globalColor= green_color;
                    String url = getDirectionsUrl(portugal1, portugal2);
                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute(url);
                }


                if(numClicks==2)
                {
                    globalColor= green_color;
                    String url2 = getDirectionsUrl(portugal2, portugal4);
                    DownloadTask downloadTask2 = new DownloadTask();
                    downloadTask2.execute(url2);
                }


                if(numClicks==3)
                {
                    globalColor= green_color;
                    String url2 = getDirectionsUrl(portugal4, portugal3);
                    DownloadTask downloadTask2 = new DownloadTask();
                    downloadTask2.execute(url2);

                }

                if(numClicks==4)
                {
                    showExitMessage();
                }
            }
        });
    }



    public void welcomeMessage()
    {


        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage("YWelcome to Lisbon, Portugal! The city that is built on seven hills! You are currently at Hotel Lisboa Tejo. Make your way to the Lsibon Zoo using BMW Drivenow, and be sure to make some stops along the way! Tchau!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }


    public void showExitMessage()
    {


        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("You have Successfully completed this Journey!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Next",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }



    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();


            parserTask.execute(result);

        }
    }


    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);

                lineOptions.color(globalColor);
                lineOptions.geodesic(true);

            }

// Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }














































}
