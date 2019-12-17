package com.example.automatedsystem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

@SuppressWarnings("deprecation")
@TargetApi(Build.VERSION_CODES.M)
class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private final Context context;

    public FingerprintHandler(Context context){

        this.context = context;

    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        Toast.makeText(context, "Authentication error" + errString, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAuthenticationFailed() {

        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        Toast.makeText(context, "Error: " + helpString, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, User.class);
        context.startActivity(i);

    }

    private void update(String s, boolean b) {

        TextView paraLabel = ((Activity)context).findViewById(R.id.paraLabel);
        ImageView imageView = ((Activity)context).findViewById(R.id.fingerprintImage);

        paraLabel.setText(s);

        if(!b){

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.ic_launcher);

        }

    }
}