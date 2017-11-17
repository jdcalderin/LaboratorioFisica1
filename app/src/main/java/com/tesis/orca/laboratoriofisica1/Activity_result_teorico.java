package com.tesis.orca.laboratoriofisica1;


/*
Autor: Carlos orrego
Fecha de creacion: 2017/11/12
Descripcion: actividad donde se calcula la velocidad teorica y se muestra la exprecion

 */



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_result_teorico extends AppCompatActivity {

    public static final String strValorTeorico = "3";
    public String strResultado="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_teorico);
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

        calcular();

    }

    // funcion calcular velocidad
    public void calcular(){


        //se captura los parametros de radio y periodo ingresados en la actividad anterior

        Intent intent = getIntent();
        Intent intent2 = getIntent();

        String strRadio = intent.getStringExtra(MainActivity.strValorRadio); // varaible para capturar el parametro de radio
        String strPeriodo = intent2.getStringExtra(MainActivity.strValorPeriodo); //variable para capturar el parametro del periodo

        // convertimos los parametros a tipo float
        float fRadio=Float.parseFloat(strRadio); // variable para el radio numerica
        float fPeriodo=Float.parseFloat(strPeriodo); // variable para el perido numerica


        float p=Float.parseFloat("3.141592653589793"); // variable para almacenar el valor de PI
        float fResultado=(2*p*fRadio)/fPeriodo;//se calcula la velocidad
        strResultado= fResultado +""; //se combierte el resultado a un valor string

        //se muestra en pantalla la formula despejada para calcular la velocidad 2πr/p
        TextView tvRadio = (TextView) findViewById(R.id.tvRadio);
        TextView tvPeriodo = (TextView) findViewById(R.id.tvPeriodo);
        tvRadio.setText("2π*"+strRadio+ " m");
        tvPeriodo.setText(strPeriodo+ " s");

        //se muestra en pantalla el resultado
        TextView tvResultado= (TextView) findViewById(R.id.tvResultado);
        tvResultado.setText(String.format("%.5f", fResultado) + " m/s");

    }
    //se llama la actividad para medir la velocidad experimental
    public void IrVelExperimental(View view) {
        //se instancia el resultado para enviar como parametro para tener el valor en la actividad de error relativo
        Intent intent = new Intent(this, Activity_calc_experimental.class);
        intent.putExtra(strValorTeorico, strResultado);
        startActivity(intent);
    }

}
