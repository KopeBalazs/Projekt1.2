package com.example.adminapp2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adminapp2.R;

public class ViewGroupsActivity extends AppCompatActivity {

    private static final String TAG="viewGroupsDebug";
    private TextView textHelloAdmin;
    private String adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups);

        init();

        setAdminNameFromToast();
    }

    private void init(){
        textHelloAdmin=(TextView) findViewById(R.id.textHelloAdmin);
    }

    private void setAdminNameFromToast(){
        Intent intentAdminName= getIntent();
        adminName= intentAdminName.getStringExtra("adminName");

        textHelloAdmin.setText("Hello "+adminName+"!");
    }
}
