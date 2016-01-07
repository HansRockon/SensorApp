package com.example.hansrajbissessur.sensorapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class Distance extends Activity implements LocationListener {

    private Button goMark;
    private TextView logSheet;

    private boolean isWalking = false;
    private Location start = null;
    private LocationManager lm;
    private Location mLoc = null;
    private String full = "";

    //display status stuff
    private Double distance = 0.0;
    private String status = "";

    private ArrayList<LocSet> rangesWalked = new ArrayList<LocSet>();

    public class LocSet {
        public Location startPt;
        public Location endPt;
    }

    private void updateDislay() {
        full = status + "\n";

        String s_dist = "";

        s_dist = Integer.toString(distance.intValue());

        full += "Distance: " + s_dist + " yards.\n";

        logSheet.setText(full);
    }

    private void setLocStatus(String s) {
        status = s;
    }

    private void doDistanceFromLoc(Location loc) {
        float meters = mLoc.distanceTo(start);
        Double dist = meters * 1.0936133;
        distance = dist;
    }

    private void logCurrLocsVector() {
        LocSet pts = new LocSet();
        pts.startPt = start;
        pts.endPt = mLoc;
        rangesWalked.add(pts);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        //get parts of view
        goMark = (Button) findViewById(R.id.go);
        logSheet = (TextView) findViewById(R.id.log);

        // set up the LocationManager
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        //adding a listener to the goMark button
        goMark.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // call the click function
                doLineWalking();
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_add_white_48dp);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .setBackgroundDrawable(R.drawable.selector_button)
                .build();

        ImageView iconSetting = new ImageView(this);
        iconSetting.setImageResource(R.drawable.ic_setting_black);
        ImageView iconBack = new ImageView(this);
        iconBack.setImageResource(R.drawable.ic_reply_black);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton buttonSetting = itemBuilder.setContentView(iconSetting).build();
        SubActionButton buttonBack = itemBuilder.setContentView(iconBack).build();


        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonSetting)
                .addSubActionView(buttonBack)
                .attachTo(actionButton)
                .build();
    }

    @Override
    protected void onDestroy() {
        stopListening();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        stopListening();
        super.onPause();
    }

    @Override
    protected void onResume() {
        startListening();
        super.onResume();
    }

    @SuppressLint("NewApi")
    private void startListening() {

        lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0,
                this
        );
    }

    @SuppressLint("NewApi")
    private void stopListening() {
        if (lm != null)

            lm.removeUpdates(this);
    }


    protected void doLineWalking() {
        //checking to see if this is the beginning or end of the walk.
        if(isWalking == false) {
            start = mLoc;
            isWalking = true;
            goMark.setText("Mark Done!");
        }
        else {
            if((mLoc == null) || (start == null)) {

            }
            else {
                doDistanceFromLoc(mLoc);
                logCurrLocsVector();
            }

            isWalking = false;
            goMark.setText("Go");

            updateDislay();
        }
    }

    public void onLocationChanged(Location location) {
        mLoc = location;

        // we got new location info. lets display it in the textview
        String s = "";
        s += "Latitude:  " + location.getLatitude()  + "\n";
        s += "Longitude: " + location.getLongitude() + "\n";
        s += "Accuracy:  " + location.getAccuracy()  + "\n";
        setLocStatus(s);

        if(isWalking) {
            doDistanceFromLoc(mLoc);
        }

        //logSheet.setText(s);
        updateDislay();
    }

    public void onProviderDisabled(String provider) {
        //nothing
    }

    public void onProviderEnabled(String provider) {
        //nothing
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        //nothing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_distance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
