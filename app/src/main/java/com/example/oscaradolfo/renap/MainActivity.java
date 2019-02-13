package com.example.oscaradolfo.renap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscaradolfo.renap.serviciosrest.Retrofit;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import static com.example.oscaradolfo.renap.serviciosrest.Retrofit.*;

public class MainActivity extends AppCompatActivity {
    private Button logout;
    private TextView state,info;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = (Button) findViewById(R.id.btn_logout);
        state = (TextView) findViewById(R.id.state);
        info = (TextView) findViewById(R.id.info);
        photo = (ImageView) findViewById(R.id.photo);
        String request,name,status;
        status = Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_STATE_PREFERENS);
        Toast.makeText(getApplicationContext(),status, Toast.LENGTH_LONG).show();
        if(status.equals("Solicitado")){
            request = getResources().getString(R.string.process);
            name = Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_FIRST_NAME_PREFERENS)+" "+Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_LAST_NAME_PREFERENS);
            state.setText(name+" "+request+" "+status);
            photo.setVisibility(View.GONE);
            state.setVisibility(View.VISIBLE);
            info.setVisibility(View.GONE);
        }else if(status.equals("En Proceso")){
            request = getResources().getString(R.string.process);
            name = Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_FIRST_NAME_PREFERENS)+" "+Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_LAST_NAME_PREFERENS);
            state.setText(name+" "+request+" "+status);
            state.setBackgroundResource(R.drawable.warning);
            state.setTextColor(getResources().getColor(R.color.colorWarningText));
            photo.setVisibility(View.GONE);
            state.setVisibility(View.VISIBLE);
            info.setVisibility(View.GONE);
        }else{
            String dpi, first_name,last_name,birthdate,department,municipality,address,email,url;
            photo.setVisibility(View.VISIBLE);
            state.setVisibility(View.GONE);
            info.setVisibility(View.VISIBLE);

            dpi = getResources().getString(R.string.dpi);
            first_name = getResources().getString(R.string.first_name);
            last_name = getResources().getString(R.string.last_name);
            email = getResources().getString(R.string.email);
            birthdate = getResources().getString(R.string.birthdate);
            department = getResources().getString(R.string.department);
            municipality = getResources().getString(R.string.municipality);
            address = getResources().getString(R.string.address);

            info.setText(dpi+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_DPI_PREFERENS)+"\n"+
                    first_name+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_FIRST_NAME_PREFERENS)+"\n"+
                    last_name+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_LAST_NAME_PREFERENS)+"\n"+
                    email+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_EMAIL_PREFERENS)+"\n"+
                    birthdate+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_BIRTHDATE_PREFERENS)+"\n"+
                    department+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_DEPARTMENT_PREFERENS)+"\n"+
                    municipality+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_MUNICIPALITY_PREFERENS)+"\n"+
                    address+": "+ Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_ADDRESS_PREFERENS));
            url = Retrofit.ip+"/img/profile/"+Preferences.obtenerPreferensString(MainActivity.this,Preferences.STRING_PHOTO_PREFERENS);
            Picasso.get().load(url).into(photo);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.savePreferensBoolean(MainActivity.this,false,Preferences.ESTADO_PREFERENS);
                Intent i = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
