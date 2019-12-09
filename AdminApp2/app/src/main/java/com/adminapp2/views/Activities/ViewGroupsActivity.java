package com.adminapp2.views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.adminapp2.Classes.FireBaseAdapter;
import com.adminapp2.Classes.RecyclerViewAdapterGroup;
import com.adminapp2.models.Group;
import com.adminapp2.views.Fragments.FragmentCreateGroup;
import com.adminapp2.Classes.SectionsStatePagerAdapter;
import com.adminapp2.models.User;
import com.example.adminapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewGroupsActivity extends AppCompatActivity {

    private static final String TAG = "debug1";

    private TextView textHelloAdmin;
    private ImageButton buttonAddGroup;
    private ViewPager mViewPager;
    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private EditText inputGroupEditText;
    private String nextID="ID0";
    private ArrayList<String> mTableIDs =new ArrayList<>();
    private FireBaseAdapter db=new FireBaseAdapter();
    private Intent intentReloadActivity;
    private Bundle bundle=new Bundle();

    private Query mQuery;
    private DatabaseReference mDBRef;

    private User admin;
    private ArrayList<Group> groupsOfTheAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups);

        init();

        uploadGroupsListAndRecyclerView();
    }

    private void init() {
        textHelloAdmin = (TextView) findViewById(R.id.textHelloAdmin);
        mDBRef = FirebaseDatabase.getInstance().getReference("Groups");
        mQuery = mDBRef.orderByChild("Groups");
        buttonAddGroup = (ImageButton) findViewById(R.id.ButtonAddGroup);
        admin = getAdminFromIntent();
        textHelloAdmin.setText("Hello " + admin.getName() + "!");
        groupsOfTheAdmin= new ArrayList<>();
        inputGroupEditText = (EditText)findViewById(R.id.editTextNewGroupName);
        intentReloadActivity = new Intent(ViewGroupsActivity.this, ViewGroupsActivity.class);
    }

    private User getAdminFromIntent() {
        User adminTmp;
        adminTmp = (User) getIntent().getSerializableExtra("admin");
        return adminTmp;
    }

    private void uploadGroupsListAndRecyclerView() {
        mDBRef = FirebaseDatabase.getInstance().getReference("Groups");
        mQuery = mDBRef.orderByChild("Groups");

        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> adminsGroupNames=new ArrayList<>();
                for (DataSnapshot groupTmp : dataSnapshot.getChildren()) {
                    if (groupTmp.child("AID").getValue().toString().equals(admin.getId().toString())) {
                        String groupId = groupTmp.getKey().toString();
                        String adminID = groupTmp.child("AID").getValue().toString();
                        String groupName = groupTmp.child("Name").getValue().toString();

                        Group tempGroup = new Group(groupId, adminID, groupName);
                        System.out.println(tempGroup);

                        groupsOfTheAdmin.add(tempGroup);
                        System.out.println("after");
                    }
                }
                adminsGroupNames=uploadAdminsGroupNames(groupsOfTheAdmin);
                initRecyclerView(adminsGroupNames, groupsOfTheAdmin);

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

            }
        });
    }

    private ArrayList<String> uploadAdminsGroupNames(ArrayList<Group> Groups){
        ArrayList<String> tempAdminsGroupNames=new ArrayList<String>();

        for(int i=0; i<Groups.size(); i++){
            tempAdminsGroupNames.add(Groups.get(i).getName());
        }

        return tempAdminsGroupNames;
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void createGroup(){
        buttonAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inputGroupEditText.length()>0){

                    mDBRef = FirebaseDatabase.getInstance().getReference("Groups");
                    mQuery = mDBRef.orderByChild("Groups");

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                            //System.out.println("tmpId= "+tmpId);
                            nextID="ID"+tmpId;

                            String groupId = nextID;
                            String adminID = admin.getId();
                            String groupName = inputGroupEditText.getText().toString();

                            Group tempGroup = new Group(groupId, adminID, groupName);
                            groupsOfTheAdmin.add(tempGroup);

                            db.addStringValueToDatabase(inputGroupEditText.getText().toString(),"Groups."+nextID+".Name");
                            db.addStringValueToDatabase(admin.getId(),"Groups."+nextID+".AID");

                            bundle.putSerializable("admin", admin);
                            intentReloadActivity.putExtras(bundle);
                            startActivity(intentReloadActivity);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d(TAG, "Cancelled");
                        }
                    });


                }
                else{
                    Toast toastInvalidGroupName=Toast.makeText(getApplicationContext(), "You need to add a group name first!", Toast.LENGTH_LONG);
                    toastInvalidGroupName.show();
                }
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter=new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCreateGroup(), "FragmentCreateGroup");
        viewPager.setAdapter(adapter);
    }

    private void initRecyclerView(List<String> nameList, ArrayList<Group> groups){
        // Log.d(TAG, "initRecyclerView: init recyclerview."+findViewById(R.id.recyclerView));
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        RecyclerViewAdapterGroup adapter=new RecyclerViewAdapterGroup(this, nameList, groups);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}



















/*
    private void initGroupNamesFromDataBase(){
        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {

                   /* Object groupName = mGroupNames.add(mySnapshot.child("Name").getValue();
                    if (groupName != null) {

                    }*/
                   // mGroupNames.add(mySnapshot.child("Name").getValue().toString());
/*                    mGroupIDs.add(mySnapshot.getKey().toString());  //Group ID-k
                }

                Log.d(TAG, "Names" +mGroupNames.toString());
                Log.d(TAG, "IDs" +mGroupIDs.toString());

                initRecyclerView(mGroupNames);
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


    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
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

    private void initRecyclerView(List<String> nameList){
        // Log.d(TAG, "initRecyclerView: init recyclerview."+findViewById(R.id.recyclerView));
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        RecyclerViewAdapterGroup adapter=new RecyclerViewAdapterGroup(this, nameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
        */