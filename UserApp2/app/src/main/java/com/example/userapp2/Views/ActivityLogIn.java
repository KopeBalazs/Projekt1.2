package com.example.userapp2.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userapp2.R;


public class ActivityLogIn extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonLogIn;
    private Intent intentStartActivityJoinGroup;
    private Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
        logIn();
    }

    private void init(){
        editTextName=(EditText) findViewById(R.id.nameInputText);
        buttonLogIn=(Button) findViewById(R.id.signInButton);
        intentStartActivityJoinGroup=new Intent(ActivityLogIn.this, ActivityJoinGroup.class);
    }

    private void logIn(){
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editTextName.getText().toString();
                if(name.length()>0){
                    bundle.putSerializable("userName", name);
                    intentStartActivityJoinGroup.putExtras(bundle);
                    startActivity(intentStartActivityJoinGroup);
                }
            }
        });
    }
}