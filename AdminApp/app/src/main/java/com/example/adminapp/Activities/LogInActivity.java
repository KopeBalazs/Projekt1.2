package com.example.adminapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.adminapp.R;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE= "com.example.adminapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
        openGroupViewActivity();
        if(nameValidation()){

        }
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
