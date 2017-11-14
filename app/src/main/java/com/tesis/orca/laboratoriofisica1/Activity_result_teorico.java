package com.tesis.orca.laboratoriofisica1;

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


    public void calcular(){
        Intent intent = getIntent();
        Intent intent2 = getIntent();

        String strRadio = intent.getStringExtra(MainActivity.strValorRadio);
        String strPeriodo = intent2.getStringExtra(MainActivity.strValorPeriodo);


        float fRadio=Float.parseFloat(strRadio);;
        float fPeriodo=Float.parseFloat(strPeriodo);
        float p=Float.parseFloat("3.141592653589793");
        float fResultado=(2*p*fRadio)/fPeriodo;

        // Capture the layout's TextView and set the string as its text
        TextView tvRadio = (TextView) findViewById(R.id.tvRadio);
        TextView tvPeriodo = (TextView) findViewById(R.id.tvPeriodo);
        tvRadio.setText("2π*"+strRadio+ " m");
        tvPeriodo.setText(strPeriodo+ " s");

        TextView tvResultado= (TextView) findViewById(R.id.tvResultado);

        tvResultado.setText(String.format("%.5f", fResultado) + " m/s");

    }

    public void IrVelExperimental(View view) {
        Intent intent = new Intent(this, Activity_calc_experimental.class);

        TextView tvResultado= (TextView) findViewById(R.id.tvResultado);
        String strValorteorico = tvResultado.getText().toString();


        intent.putExtra(strValorTeorico, strValorteorico);


        startActivity(intent);
    }

}
