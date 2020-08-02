package com.example.sih;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin,btnChngelang,login,register,btnRegister;
    public View customview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_login);
        initialiseWidgets();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginPopup();
            }
        });
        btnChngelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguage();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistrationPopup();
            }
        });
    }

    private void showChangeLanguage() {
        final String[] languages = {"English","हिन्दी"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        mBuilder.setTitle("SELECT LANGUAGE");
        mBuilder.setSingleChoiceItems(languages, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                 if(i==0) {
                     setAppLocale("en");
                     recreate();
                 } else//{
                     if(i==1){
                         setAppLocale("hi-rIN");
                         recreate();
                     }
                // }
                 dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    private void setAppLocale(String en) {
        /*Locale locale = new Locale(en);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",en);
        editor.apply();*/
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            conf.setLocale(new Locale(en));
        }else{
            conf.locale = new Locale(en);
        }
        res.updateConfiguration(conf,dm);
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setAppLocale(language);
    }

    private void showLoginPopup() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        customview = inflater.inflate(R.layout.popup_user_login,null);
        mBuilder.setView(customview);
        AlertDialog mDialog = mBuilder.create();
        login = customview.findViewById(R.id.login);
        mDialog.show();
        final EditText phonenum = customview.findViewById(R.id.phone);
        final EditText pass = customview.findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginApiCall(phonenum.getText().toString(),pass.getText().toString());
            }
        });
    }


    private void loginApiCall(String phone, String password) {
        LoginDetails details = new LoginDetails(phone,password);
        Call<AuthResponse> call = AppClient.getInstance().createService(APIServices.class).postLoginUser(details);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    if (getApplicationContext() != null) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                AuthResponse authResponse = response.body();
                                 /*pref.setSharedPref(LoginActivity.this, authResponse.getToken(), authResponse.getFirstName(),
                                       authResponse.getLastName(), loginEmail.getText().toString());
                                pref.setIsLoggedIn(LoginActivity.this, true);
                                 Log.e("LoginActivity Login", response.body().getMessage());*/
                                Toast.makeText(LoginActivity.this, "Welcome To Sehyatri!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(LoginActivity.this, "Couldn't log you in. Please try again.",Toast.LENGTH_SHORT).show();
                        } else if(response.code() == 401){
                            Toast.makeText(LoginActivity.this, "Invalid Credentials!",Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(LoginActivity.this, "Couldn't log you in. Please try again later.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An Error occurred while logging you in. Please try again in a while!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateLogin(String phone, String password) {
        if(phone.length()==0 || phone.trim().length()==0){
            Toast.makeText(LoginActivity.this,"Phone must be atleast 10 characters long",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<8 || password.trim().length()<8){
            Toast.makeText(LoginActivity.this,"Password must be atleast 8 characters long",Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(LoginActivity.this,"Validated!",Toast.LENGTH_SHORT).show();
        return true;
    }

    private  void showRegistrationPopup(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View customview = inflater.inflate(R.layout.popup_user_reg,null);
        mBuilder.setView(customview);
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
        final EditText user = customview.findViewById(R.id.name);
        final EditText phonenum = customview.findViewById(R.id.phone);
        final EditText pass = customview.findViewById(R.id.password);
        register = customview.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRegister(user.getText().toString(),phonenum.getText().toString(),pass.getText().toString())){
                    //api call
                    registerApiCall(user.getText().toString(),phonenum.getText().toString(),pass.getText().toString());
                }
            }
        });
    }

    private void registerApiCall(String name, String phone, String password) {
        RegisterDetails details = new RegisterDetails(name,phone,password);
        Call<AuthResponse> call = AppClient.getInstance().createService(APIServices.class).postRegisterUser(details);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    if (getApplicationContext() != null) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                AuthResponse authResponse = response.body();
                                Toast.makeText(LoginActivity.this, "Successfully registered!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Sorry! Couldn't Register You. Please try again.",Toast.LENGTH_SHORT).show();
                                Log.e("LoginActivity Register", "Response Successful, Response Body NULL");
                            }
                        } else if (response.code() == 400) {
                            Toast.makeText(getApplicationContext(), "User with this email already exists.",Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(LoginActivity.this, "Sorry! Couldn't Register You. Please try again in some time.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Registration failed!."+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateRegister(String name, String phone, String password) {
        if(phone.length()==0 || phone.trim().length()==0){
            return false;
        }
        if(name.length()==0 || name.trim().length()==0){
            return false;
        }
        if(password.length()<8 || password.trim().length()<8){
            Toast.makeText(LoginActivity.this,"Password must be atleast 8 characters long",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initialiseWidgets() {
        btnLogin = findViewById(R.id.login_button);
        btnChngelang = findViewById(R.id.changeLanguage);
        btnRegister = (Button)findViewById(R.id.register_button);

    }
}
