package com.tesis.orca.laboratoriofisica1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Activity_calc_experimental extends AppCompatActivity {

    public EditText txtLongitud;
    public EditText lblResultado,LblTiempo;
    SensorManager sm;
    Sensor sensor;

    private static Date tinicial = new Date();
    private static Date  tfinal  = new Date()  ;
    LinearLayout principalLayout;

    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_experimental);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        principalLayout = (LinearLayout)findViewById(R.id.layoutprincipal);



        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        txtLongitud=(EditText)findViewById(R.id.txtLongitud);
        lblResultado=(EditText) findViewById(R.id.LblResultado);
        LblTiempo=(EditText) findViewById(R.id.LblTiempo);
        sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void Calcular(){

        Float Longitud;
        if (txtLongitud.getText().length()==0){
            Longitud=Float.parseFloat("0");
        }
        else {
            Longitud = Float.parseFloat(txtLongitud.getText().toString());
        }


        long diffInMs = tfinal.getTime() - tinicial.getTime();
        Double tiempo = diffInMs / 1000.0;
        if (Longitud>0) {
            Double resultado = Longitud / tiempo;
            lblResultado.setText(String.format("%.5f", resultado) + " m/s");
            LblTiempo.setText(String.format("%.4f", tiempo) + " s");
        }
        else
        {
            Toast.makeText(this,"La longid debe ser mayor a 0 'cero'",Toast.LENGTH_SHORT).show();
        }


    }

    public void Iniciar(){

        this.tinicial = new Date();

        lblResultado.setText("0.00000 m/s");
        LblTiempo.setText("0.0000 s");
        principalLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));

    }

    public void Finalizar(){
        this.tfinal = new Date();

        this.Calcular();
        principalLayout.setBackgroundColor(getResources().getColor(R.color.normal));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        String tx = String.valueOf(event.values[0]);
        Float valor=Float.parseFloat(tx);
        if(valor==0) {
            Iniciar();
        }else {
            Finalizar();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
