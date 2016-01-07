package com.example.hansrajbissessur.sensorapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.Locale;
import java.awt.font.*;

public class ma_pager_adapter extends FragmentPagerAdapter {

    private String intro1 = "Introduction";
    private String intro2 = "Sensors";
    private String intro3 = "Settings";


    public ma_pager_adapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                tab1 t1 = new tab1();
                return t1;
            case 1:
                tab2 t2 = new tab2();
                return t2;
            case 2:
                tab3 t3 = new tab3();
                return t3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }//set the number of tabs

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return intro1;
            case 1:

                return intro2;
            case 2:

                return intro3;
        }
        return null;
    }



}
