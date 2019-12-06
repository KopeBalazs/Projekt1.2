package com.example.adminapp2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adminapp2.Classes.RecyclerViewAdapter;
import com.example.adminapp2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ViewGroupsActivity extends AppCompatActivity {

    private static final String TAG = "debug1";

    private TextView textHelloAdmin;
    private String adminName;
    private Query mQuery;
    private DatabaseReference mDBRef;
    private ArrayList<String> mGroupNames=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups);

        init();

        setAdminNameFromToast();

        Log.d(TAG, "onCreate: started.");

        initGroupNamesFromDataBase();


    }

    private void init(){
        textHelloAdmin=(TextView) findViewById(R.id.textHelloAdmin);
        mDBRef = FirebaseDatabase.getInstance().getReference("Admins");;
        mQuery= mDBRef.orderByChild("Admin");
    }

    private void setAdminNameFromToast(){
        Intent intentAdminName= getIntent();
        adminName= intentAdminName.getStringExtra("adminName");

        textHelloAdmin.setText("Hello "+adminName+"!");
    }

    private void initGroupNamesFromDataBase(){

        
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this, mGroupNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
