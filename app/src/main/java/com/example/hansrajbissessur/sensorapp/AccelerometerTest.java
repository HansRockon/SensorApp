package com.example.hansrajbissessur.sensorapp;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * @author HansRaj Bissessur
 *
 */
public class AccelerometerTest extends Activity implements SensorEventListener {

    private SensorManager sensorManager;

    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);

        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void getMenuActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuNavigation.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register a listener for the sensor.
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // get reading from the sensor
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("X axis: " + x + " m/s(2)\n" +
                "Y axis: " + y +  " m/s(2)\n" +
                "Z axis: " + z +  " m/s(2)\n");
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // to do something
    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregister the sensor to prevent battery draining
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
