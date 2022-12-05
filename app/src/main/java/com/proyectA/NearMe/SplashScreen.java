package com.proyectA.NearMe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class SplashScreen extends AppCompatActivity {
    final int SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        getSupportActionBar().hide();
        /*Pasado la cantidad de 4 segundos, se genera
         la clase de navigation drawer.
         */
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, Nav_drawer.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);
    }
}
