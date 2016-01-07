package com.example.hansrajbissessur.sensorapp;


import android.content.Intent;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class Intro extends FragmentActivity {

    private ViewPager pager;
    private PagerTabStrip tab_strp;
    private TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ma_pager_adapter mapager=new ma_pager_adapter(getSupportFragmentManager());
        pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(mapager);
        tab_strp=(PagerTabStrip)findViewById(R.id.tab_strip);
        tab_strp.setTextColor(Color.WHITE);
        tab_strp.setElevation(16);
        tab_strp.setTextSize(5, 5);
        tab_strp.setTabIndicatorColor(Color.WHITE);

        TermsDialog terms = new TermsDialog(this);
        terms.show();

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit ( int status){
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);

                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MenuNavigation.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Enter The Main Menu", Toast.LENGTH_LONG).show();
                t1.speak("Enter The Main Menu", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}