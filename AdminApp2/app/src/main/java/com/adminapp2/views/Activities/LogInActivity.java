package com.adminapp2.views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adminapp2.Classes.FireBaseAdapter;
import com.adminapp2.models.Role;
import com.adminapp2.models.User;
import com.example.adminapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    private EditText nameText;
    private Button signInButton;
    private FireBaseAdapter db = new FireBaseAdapter();
    private Intent intentViewGroups;
    private Query mQuery;
    private DatabaseReference mDBRef;
    private Bundle bundle=new Bundle();
    private long forCounter;
    private ArrayList<String> mTableIDs =new ArrayList<>();
    private String nextID="ID0";
    String textInputName;

    private static User user;
    private static Role role = new Role();



    Toast nameIsTakenToast;
    Toast nameIsTooShortToast;
    Toast basicUserLoggingInAsAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
        login();
    }

    private void init() {
        nameText = (EditText) findViewById(R.id.nameInputText);
        signInButton = (Button) findViewById(R.id.signInButton);
        intentViewGroups = new Intent(this, ViewGroupsActivity.class);
        nameIsTakenToast=Toast.makeText(getApplicationContext(), "This name is already taken. Please choose another one!", Toast.LENGTH_LONG);
        nameIsTooShortToast=Toast.makeText(getApplicationContext(), "This name is too short! It needs to be at least 3 characters!", Toast.LENGTH_LONG);

    }

    private void login() {
        this.signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                mDBRef = FirebaseDatabase.getInstance().getReference("User");
                mQuery= mDBRef.orderByChild("User");

                if (nameText.getText().toString().length() != 0) {


                    /** Reading Users to user object */
                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            textInputName = nameText.getText().toString();
                            boolean userExists=false;
                            forCounter=0;

                            long numberOfChildren=dataSnapshot.getChildrenCount();


                            for (DataSnapshot tmp : dataSnapshot.getChildren()) {

                                String id = tmp.getKey();
                                String name = null;
                                String usrRoleId = null;


                                if (tmp.child("Name") != null || tmp.child("Name").toString().length() != 0) {
                                    name = tmp.child("Name").getValue().toString();
                                    usrRoleId = tmp.child("RID").getValue().toString();

                                    if (textInputName.equals(name)) {
                                        userExists=true;
                                        user = new User();
                                        user.setRole(role);
                                        user.setName(name);
                                        user.setId(id);
                                        user.getRole().setId(usrRoleId);

                                        /** Reading Role to user object */

                                        mDBRef = FirebaseDatabase.getInstance().getReference("Role");
                                        mQuery= mDBRef.orderByChild("Role");

                                        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot roleTmp : dataSnapshot.getChildren()) {

                                                    String roleId = roleTmp.getKey();
                                                    String roleType = roleTmp.getValue().toString();
                                                    System.out.println(user.getRole().getType());

                                                    if (roleId.equals(user.getRole().getId())) {
                                                        switch(roleType) {
                                                            case "Admin" : {user.getRole().setType(Role.Type.ADMIN);
                                                                break;}
                                                            case "Basic" : {user.getRole().setType(Role.Type.BASIC);}
                                                            default : {user.getRole().setType(Role.Type.BASIC);}
                                                        }
                                                    }

                                                }

                                                //System.out.println(user);

                                                if(user.getRole().getType() == Role.Type.ADMIN){
                                                    bundle.putSerializable("admin", user);
                                                    intentViewGroups.putExtras(bundle);
                                                    startActivity(intentViewGroups);
                                                }
                                                else{
                                                    basicUserLoggingInAsAdmin.makeText(getApplicationContext(), "You are trying to log in as an admin, but you are a user. Please log in in the UserApp!", Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        break;
                                    }
                                    else{
                                        forCounter++;
                                        //System.out.println(forCounter+ " " +numberOfChildren);
                                        if(forCounter >= numberOfChildren && userExists==false){
                                            addUserToDatabaseAndUserObj();
                                            //System.out.println("Nincs ilyen felhasznalo!");
                                            break;
                                        }
                                        continue;
                                    }
                                }
                                break;
                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "No username entered!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void addUserToDatabaseAndUserObj(){
        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                    mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                }
                int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                //System.out.println("tmpId= "+tmpId);
                nextID="ID"+tmpId;

                Role adminRole=new Role("ID1", Role.Type.ADMIN);

                user=new User(nextID, textInputName, adminRole);
                //System.out.println(user);

                db.addStringValueToDatabase(textInputName, "User."+nextID+".Name");
                db.addStringValueToDatabase("ID1", "User."+nextID+".RID");

                bundle.putSerializable("admin", user);
                intentViewGroups.putExtras(bundle);
                startActivity(intentViewGroups);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Cancelled");
            }
        });
    }

}
