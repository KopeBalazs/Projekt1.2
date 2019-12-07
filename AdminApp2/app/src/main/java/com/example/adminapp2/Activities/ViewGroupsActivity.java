package com.example.adminapp2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adminapp2.Classes.FragmentCreateGroup;
import com.example.adminapp2.Classes.RecyclerViewAdapter;
import com.example.adminapp2.Classes.SectionsStatePagerAdapter;
import com.example.adminapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewGroupsActivity extends AppCompatActivity {

    private static final String TAG = "debug1";

    private TextView textHelloAdmin;
    private String adminName;
    private Query mQuery;
    private DatabaseReference mDBRef;
    private ArrayList<String> mGroupNames=new ArrayList<>();
    private ImageButton buttonAddGroup;
    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

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
        mDBRef = FirebaseDatabase.getInstance().getReference("Groups");;
        mQuery= mDBRef.orderByChild("Groups");
        buttonAddGroup=(ImageButton) findViewById(R.id.ButtonAddGroup);
    }

    private void setAdminNameFromToast(){
        Intent intentAdminName= getIntent();
        adminName= intentAdminName.getStringExtra("adminName");

        textHelloAdmin.setText("Hello "+adminName+"!");
    }

    private void initGroupNamesFromDataBase(){
        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                    mGroupNames.add(mySnapshot.child("Name").getValue().toString());
                }
                initRecyclerView();
                Log.d(TAG, "initRecyclerViewExited");
                buttonAddGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "Arrived in onClick()");
                        createGroup();
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Cancelled");
            }
        });
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this, mGroupNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void createGroup(){
        mSectionsStatePagerAdapter=new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter=new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCreateGroup(), "FragmentCreateGroup");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
