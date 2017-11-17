package com.tesis.orca.laboratoriofisica1;

/*

Nombre archivo: PrefManager.java
Autor: Carlos Orrego, Jose Calderin,Felipe Duque.
Fecha de creacion: 2017/11/12.
Descripcion (Que es):     Clase encargada, de almancenar en memoria
Descripcion (Que hace):   Si es requerido almacenar la primera vez que se abre el menu
                          introductorio, se puede guardar la primera vez que se almacena

 */

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {

        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }



}
