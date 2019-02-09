package com.davidrodillo.barcodetemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button scanButton;
    private TextView scanResult;

    private final int BARCODE_ACTIVITY_ID = 5478; //The numeric code is just a identification code that I create and give to the new activity. Any is ok.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //First I set the main interface as the interface for this app

        // I pick up the button from the view, that is in the xml file: activity_main.xml
        scanButton = (Button) findViewById(R.id.scanButton);
        scanResult = (TextView) findViewById(R.id.scanResult);

        final Intent barcodeIntent = new Intent(this, BarcodeActivity.class);

        //I add a listener. The listener is the one who decides what to do on click
        scanButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                // Here I call the Barcode reader
                startActivityForResult(barcodeIntent, BARCODE_ACTIVITY_ID);
            }
        });
    }

    @Override //I listen for activities returning values. Checking the id we can know if it is the desired one.
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent data) {

        if (aResultCode == Activity.RESULT_OK) {
            switch (aRequestCode) {
                case BARCODE_ACTIVITY_ID: // In the case of the id that I gave to the barcode reader.

                    //I put the value of the barcode in the text on the screen
                    scanResult.setText("Barcode: " + data.getDataString());

                    // TODO Here you can do anything with this data. Example: Send it to the database and check that everything is working properly.

                    break;

                //            case SOME_OTHER_REQUEST:
                //                handleSomethingElse(aData);
                //                break;
            }
        }

        super.onActivityResult(aRequestCode, aResultCode, data);
    }
}
