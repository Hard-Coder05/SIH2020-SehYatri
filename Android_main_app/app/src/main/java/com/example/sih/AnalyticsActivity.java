package com.example.sih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mapmodule.MapsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AnalyticsActivity extends AppCompatActivity {

    public int ActivityNum = 1;
    Button sendSms,findPetrolPump,go;
    TextView checkPrice,txtPlan;
    EditText distance,mileage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        setUpBottomNavigationView();
        sendSms = findViewById(R.id.sendSms);
        checkPrice = findViewById(R.id.checkPrice);
        txtPlan = findViewById(R.id.txtPlan);
        checkPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(AnalyticsActivity.this,FuelActivity.class));
            }
        });
        findPetrolPump = findViewById(R.id.findPetrolPump);
        findPetrolPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=petrol pumps");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnalyticsActivity.this, MapsActivity.class));
            }
        });
        distance = findViewById(R.id.distance);
        mileage = findViewById(R.id.mileage);
        go=findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dist = Double.parseDouble(distance.getText().toString());
                double mile = Double.parseDouble(mileage.getText().toString());
                double ans = dist/mile;
                go.setText(String.format("%.3f",ans));
            }
        });
    }

    private void setUpBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavViewBar);
        BottomNavigationHelper.enableNavigation(AnalyticsActivity.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ActivityNum);
        menuItem.setChecked(true);
    }

}
