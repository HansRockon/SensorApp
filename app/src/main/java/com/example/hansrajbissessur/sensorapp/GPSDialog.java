package com.example.hansrajbissessur.sensorapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class GPSDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.gps_dialog);
        builder.setMessage(R.string.gps_dialog_message)
                .setPositiveButton(R.string.gps_agree, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(".Intro");
                        startActivity(intent);

                    }
                })
                .setNegativeButton(R.string.gps_disagree, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GPSDialog.this.getDialog().cancel();
                    }
                });


        // Create the AlertDialog object and return it
        return builder.create();
    }

}
