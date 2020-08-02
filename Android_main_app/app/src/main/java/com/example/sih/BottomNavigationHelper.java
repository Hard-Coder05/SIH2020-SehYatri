package com.example.sih;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

class BottomNavigationHelper {
    public static void enableNavigation(final Context context , BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        Intent intent1 = new Intent(context,HomeActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.analytics:
                        Intent intent2 = new Intent(context,AnalyticsActivity.class);
                        context.startActivity(intent2);
                        break;
                }

                return false;
            }
        });
    }

}
