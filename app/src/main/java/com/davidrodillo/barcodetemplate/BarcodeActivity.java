package com.davidrodillo.barcodetemplate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class BarcodeActivity extends AppCompatActivity {

    private BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);

        barcodeReader.setListener(new BarcodeReader.BarcodeReaderListener() {
            @Override
            public void onScanned(Barcode barcode) {
                Log.e("BARCODE", "The barcode is " + barcode.displayValue);

                Intent result = new Intent();
                result.setData(Uri.parse(barcode.displayValue)); // Put the data into a intent to give it back

                setResult(Activity.RESULT_OK, result); //Store the result
                finish(); //Finish the activity
            }

            @Override //More than one barcode at the same time
            public void onScannedMultiple(List<Barcode> barcodes) {

                //In that case I pick up only the first. You can change this if you don't like it, and return both of them at once
                Log.e("BARCODE", "The barcode is " + barcodes.get(0).displayValue);

                Intent result = new Intent();
                result.setData(Uri.parse(barcodes.get(0).displayValue)); // Put the data into a intent to give it back

                setResult(Activity.RESULT_OK, result); //Store the result
                finish();

            }

            @Override
            public void onBitmapScanned(SparseArray<Barcode> sparseArray) { }
            @Override
            public void onScanError(String errorMessage) { }
            @Override
            public void onCameraPermissionDenied() { }
        });
    }

}
