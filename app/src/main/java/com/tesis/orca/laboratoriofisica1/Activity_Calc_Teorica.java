package com.tesis.orca.laboratoriofisica1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Activity_Calc_Teorica extends AppCompatActivity {

    public static final String strValorRadio = "1";
    public static final String strValorPeriodo = "2";

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

        Intent intent = getIntent();


        String strValorteorico = intent.getStringExtra(MainActivity.strValorTeorico);
    }

    public void Ircalcular(View view) {
        Intent intent = new Intent(this, Activity_result_teorico.class);

        EditText etRadio = (EditText) findViewById(R.id.etRadio);
        EditText etPeriodo = (EditText) findViewById(R.id.etPeriodo);
        String strRadio = etRadio.getText().toString();

        String strPeriodo = etPeriodo.getText().toString();
        intent.putExtra(strValorRadio, strRadio);
        intent.putExtra(strValorPeriodo, strPeriodo);

        startActivity(intent);
    }


}
