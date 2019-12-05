package com.example.adminapp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adminapp2.Classes.FireBaseAdapter;
import com.example.adminapp2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LogInActivity extends AppCompatActivity {

    //public static final String EXTRA_MESSAGE= "com.example.adminapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
        openGroupViewActivity();
        DatabaseReference dbSignup= FirebaseDatabase.getInstance().getReference("Groups");
        //dbSignup.child("1").child("Name").setValue("Kosar");   //Only for debugging reasons
        FireBaseAdapter db=new FireBaseAdapter();
        //db.addStringValueToDatabase("Kosar","Groups.1.Name");
    }

    private void init(){
        EditText nameText= (EditText) findViewById(R.id.nameInputText);
        Button button= (Button) findViewById(R.id.signInButton);

        Intent openGroupViewActivity= new Intent();

        String adminNameMessage= nameText.getText().toString();
    }

    private boolean nameValidation(){

        return false;
    }

    private void openGroupViewActivity(){

    }
}
