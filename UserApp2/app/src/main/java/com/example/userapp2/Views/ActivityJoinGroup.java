package com.example.userapp2.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.userapp2.Controllers.RecyclerViewAdapterGroup;
import com.example.userapp2.Models.Group;
import com.example.userapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityJoinGroup extends AppCompatActivity {

    private static final String TAG = "debug1";

    private Query mQuery;
    private DatabaseReference mDBRef;
    private ArrayList<Group> groups;
    private ArrayList<String> mTableIDs =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        init();
        uploadGroupsListAndRecyclerView();
    }

    private void init() {

        mDBRef = FirebaseDatabase.getInstance().getReference("Groups");
        mQuery = mDBRef.orderByChild("Groups");
        groups=new ArrayList<>();
    }

    private void uploadGroupsListAndRecyclerView() {
        mDBRef = FirebaseDatabase.getInstance().getReference("Groups");
        mQuery = mDBRef.orderByChild("Groups");

        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> groupNames=new ArrayList<>();
                for (DataSnapshot groupTmp : dataSnapshot.getChildren()) {

                        String groupId = groupTmp.getKey().toString();
                        String adminID = groupTmp.child("AID").getValue().toString();
                        String groupName = groupTmp.child("Name").getValue().toString();

                        Group tempGroup = new Group(groupId, adminID, groupName);
                        System.out.println(tempGroup);

                        groups.add(tempGroup);
                        System.out.println("after");
                }
                groupNames=uploadGroupNames(groups);
                initRecyclerView(groupNames, groups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private ArrayList<String> uploadGroupNames(ArrayList<Group> Groups){
        ArrayList<String> tempAdminsGroupNames=new ArrayList<String>();

        for(int i=0; i<Groups.size(); i++){
            tempAdminsGroupNames.add(Groups.get(i).getName());
        }

        return tempAdminsGroupNames;
    }

    private void initRecyclerView(List<String> nameList, ArrayList<Group> groups){
        // Log.d(TAG, "initRecyclerView: init recyclerview."+findViewById(R.id.recyclerView));
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        RecyclerViewAdapterGroup adapter=new RecyclerViewAdapterGroup(this, nameList, groups);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
