package com.example.oscaradolfo.renap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscaradolfo.renap.serviciosrest.Retrofit;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private Button btn_sign_in;
    private TextView email, password;
    Call<JsonElement> callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        if (Preferences.obtenerPreferensBolean(SignInActivity.this,Preferences.ESTADO_PREFERENS)){
            Intent vnt = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(vnt);
            finish();
        }
        email = (TextView) findViewById(R.id.txt_email);
        password = (TextView) findViewById(R.id.txt_password);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_in.setEnabled(false);
        email.addTextChangedListener(logintext);
        password.addTextChangedListener(logintext);
        final Intent i = new Intent(this,MainActivity.class);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback = Retrofit.getUrls().wsLogin(email.getText().toString(),password.getText().toString());
                callback.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        JsonObject jsonObject = response.body().getAsJsonObject();
                        try {
                            if(jsonObject.get("result").getAsBoolean())
                            {
                                String id, dpi, first_name,last_name,birthdate,department,municipality,address,email,phone,photo,state;
                                id = jsonObject.get("records").getAsJsonObject().get("id").getAsString();
                                dpi = jsonObject.get("records").getAsJsonObject().get("dpi").getAsString();
                                first_name = jsonObject.get("records").getAsJsonObject().get("first_name").getAsString();
                                last_name = jsonObject.get("records").getAsJsonObject().get("last_name").getAsString();
                                email = jsonObject.get("records").getAsJsonObject().get("email").getAsString();
                                birthdate = jsonObject.get("records").getAsJsonObject().get("birthdate").getAsString();
                                department = jsonObject.get("records").getAsJsonObject().get("department").getAsString();
                                municipality = jsonObject.get("records").getAsJsonObject().get("municipality").getAsString();
                                address = jsonObject.get("records").getAsJsonObject().get("address").getAsString();
                                photo = jsonObject.get("records").getAsJsonObject().get("photo").getAsString();
                                phone = jsonObject.get("records").getAsJsonObject().get("phone").getAsString();
                                state = jsonObject.get("records").getAsJsonObject().get("solicitud").getAsJsonObject().get("state").getAsString();

                                Preferences.savePreferensString(SignInActivity.this,id,Preferences.STRING_ID_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,dpi,Preferences.STRING_DPI_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,first_name,Preferences.STRING_FIRST_NAME_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,last_name,Preferences.STRING_LAST_NAME_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,email,Preferences.STRING_EMAIL_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,birthdate,Preferences.STRING_BIRTHDATE_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,department,Preferences.STRING_DEPARTMENT_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,municipality,Preferences.STRING_MUNICIPALITY_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,address,Preferences.STRING_ADDRESS_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,phone,Preferences.STRING_PHONE_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,photo,Preferences.STRING_PHOTO_PREFERENS);
                                Preferences.savePreferensString(SignInActivity.this,state,Preferences.STRING_STATE_PREFERENS);

                                Preferences.savePreferensBoolean(SignInActivity.this,true,Preferences.ESTADO_PREFERENS);

                                Intent vnt = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(vnt);
                                finish();

                            }else{
                                Toast.makeText(getApplicationContext(),jsonObject.get("message").getAsString(), Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), jsonObject.get("message").getAsString(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Toast.makeText(SignInActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }private TextWatcher logintext = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String em = email.getText().toString();
            String pass = password.getText().toString();
            if(em.length() > 0 && pass.length() > 0)
            {
                btn_sign_in.setEnabled(true);
                btn_sign_in.setBackgroundResource(R.drawable.round_button_enabled);
            }else
            {
                btn_sign_in.setEnabled(false);
                btn_sign_in.setBackgroundResource(R.drawable.round_button_disabled);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
