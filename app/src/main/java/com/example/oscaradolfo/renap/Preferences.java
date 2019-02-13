package com.example.oscaradolfo.renap;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static final String STRING_PREFERENS = "oscaradolfo.renap";
    public static final String ESTADO_PREFERENS = "state.session";
    public static final String STRING_ID_PREFERENS = "user.id";
    public static final String STRING_DPI_PREFERENS = "user.dpi";
    public static final String STRING_FIRST_NAME_PREFERENS = "user.first_name";
    public static final String STRING_LAST_NAME_PREFERENS = "user.last_name";
    public static final String STRING_EMAIL_PREFERENS = "user.email";
    public static final String STRING_BIRTHDATE_PREFERENS = "user.birthdate";
    public static final String STRING_DEPARTMENT_PREFERENS = "user.department";
    public static final String STRING_MUNICIPALITY_PREFERENS = "user.municipality";
    public static final String STRING_ADDRESS_PREFERENS = "user.address";
    public static final String STRING_PHONE_PREFERENS = "user.phone";
    public static final String STRING_PHOTO_PREFERENS = "user.photo";
    public static final String STRING_STATE_PREFERENS = "solicitud.state";

    public static void savePreferensBoolean(Context c, boolean b,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENS,c.MODE_PRIVATE);
        preferences.edit().putBoolean(key,b).apply();
    }
    public static void savePreferensString(Context c, String b,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENS,c.MODE_PRIVATE);
        preferences.edit().putString(key,b).apply();
    }
    public static boolean obtenerPreferensBolean(Context c,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENS,c.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }
    public static String obtenerPreferensString(Context c,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENS,c.MODE_PRIVATE);
        return preferences.getString(key,"");
    }
}
