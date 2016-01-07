package com.example.hansrajbissessur.sensorapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

public class Welcome extends Activity {

    private static Button button_skip;
    private static Button button_continue;
    private TextToSpeech t1;
    private String ed1 = "Continue button Selected";
    private String ed2 = "Skip button Selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        OnClickButtonListener();

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                @Override
                public void onInit ( int status){
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);

                }
            }

        });
    }

    public void OnClickButtonListener(){


        button_continue = (Button) findViewById(R.id.continue1);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toSpeak = ed1.toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                Intent intent = new Intent(getApplicationContext(),Intro.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.menu_red:
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));
                return true;
            case R.id.menu_blue:
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
                return true;

            case R.id.menu_green:
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4CAF50")));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
