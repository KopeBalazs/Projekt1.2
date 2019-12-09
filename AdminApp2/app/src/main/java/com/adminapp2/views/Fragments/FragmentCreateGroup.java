package com.adminapp2.views.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adminapp2.Classes.FireBaseAdapter;
import com.example.adminapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentCreateGroup extends Fragment {
    private static final String TAG = "FragmentCreateGroup";

    private EditText textGroupName;
    private Button createGroupButton;
    private FireBaseAdapter db;
    private Query mQuery;
    private DatabaseReference mDBRef;
    private ArrayList<String> mTableIDs =new ArrayList<>();
    private String nextID="ID0";
    private String inputGroupName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_group,container,false);
        init(view);

        createGroup();

        return view;
    }

    private void init(View view){
        textGroupName= (EditText)view.findViewById(R.id.editTextNewGroupName);
        createGroupButton= (Button)view.findViewById(R.id.buttonCreateGroup);
        db=new FireBaseAdapter();
        mDBRef = FirebaseDatabase.getInstance().getReference("Groups");
        mQuery = mDBRef.orderByChild("Groups");
        inputGroupName =textGroupName.getText().toString();
    }

    private void createGroup(){
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inputGroupName.length()>0){

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                            //System.out.println("tmpId= "+tmpId);
                            nextID="ID"+tmpId;

                            db.addStringValueToDatabase(inputGroupName,"Groups."+nextID+".Name");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d(TAG, "Cancelled");
                        }
                    });


                }
                else{
                    Toast toastInvalidGroupName=Toast.makeText(getContext(), "You need to add a group name first!", Toast.LENGTH_LONG);
                    toastInvalidGroupName.show();
                }
            }
        });
    }
}
