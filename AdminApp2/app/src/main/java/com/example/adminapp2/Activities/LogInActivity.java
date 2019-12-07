package com.example.adminapp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminapp2.Classes.FireBaseAdapter;
import com.example.adminapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LogInActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.adminapp2.MESSAGE";
    private static final String TAG = "myLogs";
    private EditText nameText;
    private Button signInButton;
    private String adminName;
    private FireBaseAdapter db = new FireBaseAdapter();
    private Intent viewGroupsIntent;
    private Query mQuery;
    private DatabaseReference mDBRef;
    Toast nameIsTakenToast;
    Toast nameIsTooShortToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
        nameValidationAndGroupsViewStarter();
    }

    private void init() {
        nameText = (EditText) findViewById(R.id.nameInputText);
        signInButton = (Button) findViewById(R.id.signInButton);
        viewGroupsIntent = new Intent(this, ViewGroupsActivity.class);
        mDBRef = FirebaseDatabase.getInstance().getReference("Admins");;
        mQuery= mDBRef.orderByChild("Admins");
        nameIsTakenToast=Toast.makeText(getApplicationContext(), "This name is already taken. Please choose another one!", Toast.LENGTH_LONG);
        nameIsTooShortToast=Toast.makeText(getApplicationContext(), "This name is too short! It needs to be at least 3 characters!", Toast.LENGTH_LONG);

    }

    private void nameValidationAndGroupsViewStarter() {

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminName = nameText.getText().toString();

                Log.d("valid", adminName);
                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean nameIsNotTaken=true;
                        for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                            if(mySnapshot.getKey().toString().equals(adminName)){

                                //nameIsTakenToast.show();
                                nameIsNotTaken=false;
                                break;
                            }
                        }
                        if(adminName.length()<3){
                            nameIsTooShortToast.show();
                        }
                        else
                        {
                            if(nameIsNotTaken)
                                db.addStringValueToDatabase("", "Admins."+adminName);

                            viewGroupsIntent.putExtra("adminName", adminName);
                            startActivity(viewGroupsIntent);
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "Cancelled");
                    }
                });
            }
        });

    }
}
