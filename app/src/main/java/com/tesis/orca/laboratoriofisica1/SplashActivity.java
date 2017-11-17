package com.tesis.orca.laboratoriofisica1;


/*

Nombre archivo: SplashActivity.java
Autor: Carlos Orrego, Jose Calderin,Felipe Duque.
Fecha de creacion: 2017/11/12.
Descripcion (Que es):     Clase encargada de crear la interfaz que de despliega al abrir el aplicativo
Descripcion (Que hace):   habilita un timer, que muestra una imagen por unos segundos, para luego
                          darle paso a la vista principal

 */
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);




        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);



    }




}
