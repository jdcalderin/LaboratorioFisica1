package com.tesis.orca.laboratoriofisica1;
/*
Autor: Carlos orrego
Fecha de creacion: 2017/11/12
Descripcion: actividad donde se calcula el porcentaje de error relativo

 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Activity_calc_error_relativo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_error_relativo);
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


    //metodo que calcula el error relativo
    public void calcular(){
        //se capturan los parametros de valor teorico y valor experimental
        Intent intent = getIntent();
        String strValorteorico = intent.getStringExtra(MainActivity.strValorTeorico);
        String strValorExperimental= intent.getStringExtra(MainActivity.strValorExperimental);

        //se convierten los valores tipo texto a numerico
        float fValorTeorico=Float.parseFloat(strValorteorico);
        float fValorExperimental=Float.parseFloat(strValorExperimental);

        //se realiza el calculo del procentade de error
        float fResultado=((fValorTeorico-fValorExperimental)/fValorTeorico)*100;

        //como es valor absoluto y el resultado es negativo se convierte a positivo
        if (fResultado<0){
            fResultado=fResultado*-1;
        }



        //se muestra en pantalla la formula despejada y el resultado del valor teorico
        TextView tvDividendo = (TextView) findViewById(R.id.textView10);
        TextView tvCociente = (TextView) findViewById(R.id.textView11);
        tvDividendo.setText(String.format("%.5f", fValorTeorico)+ " - " + String.format("%.5f", fValorExperimental));
        tvCociente.setText(String.format("%.5f", fValorTeorico));

        TextView tvResultado= (TextView) findViewById(R.id.textView13);

        tvResultado.setText(String.format("%.5f", fResultado) + " %");

    }

}
