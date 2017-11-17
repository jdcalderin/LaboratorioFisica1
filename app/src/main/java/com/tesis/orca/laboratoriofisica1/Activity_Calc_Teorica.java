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
import android.widget.Toast;

public class Activity_Calc_Teorica extends AppCompatActivity {

    public static final String strValorRadio = "1"; //id para parametro con valor del radio
    public static final String strValorPeriodo = "2"; // id para parametro con valor del periodo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__calc__teorica);
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

        /*
        Intent intent = getIntent();
        String strValorteorico = intent.getStringExtra(MainActivity.strValorTeorico);
        */
    }

    //se capturan los valores del radio y perioo y se llama la actividad donde se calculara la velocidad teorica
    public void Ircalcular(View view) {

        //se capturan los valores ingresados por el usuario
        EditText etRadio = (EditText) findViewById(R.id.etRadio);
        EditText etPeriodo = (EditText) findViewById(R.id.etPeriodo);
        String strRadio = etRadio.getText().toString();
        String strPeriodo = etPeriodo.getText().toString();

        float fRadio=0;
        float fPerido=0;


        Intent intent = new Intent(this, Activity_result_teorico.class);

        if (etRadio.getText().length()==0){

            fRadio=Float.parseFloat("0");
        }
        else {
            fRadio = Float.parseFloat(etRadio.getText().toString());
        }

        if (etPeriodo.getText().length()==0){

            fPerido=Float.parseFloat("0");
        }
        else {
            fPerido = Float.parseFloat(etPeriodo.getText().toString());
        }

        if (fPerido>0 && fRadio>0 ) {
            //se envian como parametros
            intent.putExtra(strValorRadio, strRadio);
            intent.putExtra(strValorPeriodo, strPeriodo);
            //se llama la actividad para calcular la valocidad teorica
            startActivity(intent);
        }
        else
        {
            //si no se ingresa un periodo o un radio se muestra el siguiente mensaje
            Toast.makeText(this,"El radio y el periodo deben ser mayor a 0 'cero'",Toast.LENGTH_SHORT).show();
        }






    }


}
