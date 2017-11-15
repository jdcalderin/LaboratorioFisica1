package com.tesis.orca.laboratoriofisica1;

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



    public void calcular(){

        Intent intent = getIntent();
        String strValorteorico = intent.getStringExtra(MainActivity.strValorTeorico);
        String strValorExperimental= intent.getStringExtra(MainActivity.strValorExperimental);


        float fValorTeorico=Float.parseFloat(strValorteorico);
        float fValorExperimental=Float.parseFloat(strValorExperimental);
        float fResultado=((fValorTeorico-fValorExperimental)/fValorTeorico)*100;

        if (fResultado<0){
            fResultado=fResultado*-1;
        }

        // Capture the layout's TextView and set the string as its text
        TextView tvDividendo = (TextView) findViewById(R.id.textView10);
        TextView tvCociente = (TextView) findViewById(R.id.textView11);
        tvDividendo.setText(strValorteorico+ " - " + strValorExperimental);
        tvCociente.setText(strValorteorico);

        TextView tvResultado= (TextView) findViewById(R.id.textView13);

        tvResultado.setText(String.format("%.5f", fResultado) + " %");

    }

}
