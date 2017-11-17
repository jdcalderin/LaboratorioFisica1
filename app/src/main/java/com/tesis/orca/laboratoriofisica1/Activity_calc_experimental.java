package com.tesis.orca.laboratoriofisica1;


/*

Nombre archivo: Activity_calc_experimental.java
Autor: Carlos Orrego, Jose Calderin,Felipe Duque.
Fecha de creacion: 2017/11/12.
Descripcion (Que es):    Clase encargada de calcular calcular la velocidad experimental.
Descripcion (Que hace):  Actividad donde se calcula la velocidad experimental con base a la longitud del objeto a medir
                         y el tiempo que tarda en pasar cerca del sensor de proximidad



 */


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

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

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorEventListener;
import android.os.SystemClock;

import java.util.Date;

public class Activity_calc_experimental extends AppCompatActivity implements SensorEventListener{

   //se instancian los componentes
    public EditText txtLongitud;
    public TextView lblResultado,LblTiempo;
    SensorManager sm;
    Sensor sensor;
    LinearLayout principalLayout;

    private static Date tinicial = new Date(); //varaible para capturar la hora en que se detecto un objeto cerca del sensor
    private static Date  tfinal  = new Date()  ;//varaible para capturar la hora en que se dejo de detectar un objeto cerca del sensor
    long time; //variable para almacenar el tiempo que se tardo un objeto cerca del sensor de proximidad






    public static final String strValorTeorico = "3"; //id para el parametro del valor teorico
    public static final String strValorExperimental = "4"; // id para el parametro del vlor experimental

    public String strValorteorico=""; //variable para almacenar el valor teorico
    public String strValorxperimental=""; //variable para almacenar el valor experimental

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


        //se instancian los componentes de la actividad
        principalLayout = (LinearLayout)findViewById(R.id.layoutprincipal);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        txtLongitud=(EditText)findViewById(R.id.txtLongitud);
        lblResultado=(TextView) findViewById(R.id.LblResultado);
        LblTiempo=(TextView) findViewById(R.id.LblTiempo);
        sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        //se captura el parametro del valor teorico de la actividad anterior
        Intent intent = getIntent();
        strValorteorico = intent.getStringExtra(MainActivity.strValorTeorico);
    }

    //funcion para calcular la velocidad del objeto
    public void Calcular(){
        //se valida que el usuario ingrese una longitud
        Float Longitud;
        if (txtLongitud.getText().length()==0){
            Longitud=Float.parseFloat("0");
        }
        else {
            Longitud = Float.parseFloat(txtLongitud.getText().toString());
        }

        //se calcula la diferencia entre la hora inicial y final en que se detecto un objeto sobre el sessor
        long diffInMs = tfinal.getTime() - tinicial.getTime();
        //se convierte la diferencia a segundo
        Double tiempo = diffInMs / 1000.0;

        //si la longitud del objeto es mayor a cero se procedo a realizar el calculo de velocidad
        if (Longitud>0) {
            Double resultado = Longitud / tiempo;
            //se muestra en pantalla el resultado y el tiempo
            lblResultado.setText(String.format("%.5f", resultado) + " m/s");
            LblTiempo.setText(String.format("%.4f", tiempo) + " s");

            strValorxperimental= resultado+ "";// se almacena el resultado en una variable de texto para enviarlo como parametro
        }
        else
        {
            //si no se ingreso una longitud se muestra un mensaje indicando que se debe ingresar una longitud
            Toast.makeText(this,"La longid debe ser mayor a 0 'cero'",Toast.LENGTH_SHORT).show();
        }


    }


    //metodo que se llama cuando se detecta un objeto sobre el sensor
    public void Iniciar(){
        //se captura el momento en que se detecta un objeto sobre el sensor
        this.tinicial = new Date();

        lblResultado.setText("0.00000 m/s");
        LblTiempo.setText("0.0000 s");
        principalLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));

    }
    //metodo que se llama cuando se deja de detectar un objeto sobre un objeto
    public void Finalizar(){

        //se captura el momento en que se deja de detectar el objeto
        this.tfinal = new Date();

        this.Calcular();
        principalLayout.setBackgroundColor(getResources().getColor(R.color.normal));
    }


    /**
     * se sobrecarga el metodo del sensor de proximidad para inbocar nuestros eventos
     */
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

    /**
     * metodo en el que se llama la actividad para calcular el error relativo
     */
    public void IrErrRelativo(View view) {
        Intent intent = new Intent(this, Activity_calc_error_relativo.class);


        intent.putExtra(strValorTeorico, strValorteorico);// se envia como parametro el valor teorico
        intent.putExtra(strValorExperimental, strValorxperimental); // se envia como parametro el valor teorico

        startActivity(intent);
    }
}
