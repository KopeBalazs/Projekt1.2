package com.adminapp2.views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.adminapp2.Classes.FireBaseAdapter;
import com.adminapp2.models.Question;
import com.adminapp2.models.User;
import com.example.adminapp2.R;

public class ViewTopicActivity extends AppCompatActivity {

    private Question actQuestion;
    private FireBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_topic);


        init();
        setQuestionVisibility();
    }

    private void init(){
        actQuestion= getQuestionFromIntent();
        db=new FireBaseAdapter();
        db.addBooleanValueToDatabase(true,"Question."+actQuestion.getId()+".State");
    }

    private Question getQuestionFromIntent() {
        Question questionTmp;
        questionTmp = (Question) getIntent().getSerializableExtra("question");
        return questionTmp;
    }

    private void setQuestionVisibility(){

    }

}
