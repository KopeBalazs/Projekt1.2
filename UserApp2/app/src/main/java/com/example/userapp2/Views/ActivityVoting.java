package com.example.userapp2.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userapp2.Controllers.FireBaseAdapter;
import com.example.userapp2.Models.Group;
import com.example.userapp2.Models.Question;
import com.example.userapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityVoting extends AppCompatActivity {

    private TextView textViewQuestion;

    private Query mQuery;
    private DatabaseReference mDBRef;
    private ArrayList<String> mTableIDs =new ArrayList<>();
    private String nextID="ID0";
    private String userName;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private Question question;

    private boolean voted=false;

    private FireBaseAdapter db=new FireBaseAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        init();
        vote();
    }
    private String getUserNameFromIntent() {
        String userName;
        userName= (String) getIntent().getSerializableExtra("userName");
        return userName;
    }
    private void init(){
        textViewQuestion=findViewById(R.id.textQuestion);
        button1=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        button5=(Button) findViewById(R.id.button5);
        textViewQuestion.setText(getQuestionFromIntent().getValue().toString());
        question=getQuestionFromIntent();
        userName=getUserNameFromIntent();
    }

    private void vote(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voted==false){
                    mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                    mQuery = mDBRef.orderByChild("Votes");

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            int tmpId;
                            if(mTableIDs.size()==0){
                                nextID="ID1";
                            }
                            else{
                                tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                                nextID="ID"+tmpId;
                            }

                            //System.out.println("tmpId= "+tmpId);
                            voted=true;

                            db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                            db.addStringValueToDatabase(userName, "Votes."+nextID+".UserName");
                            db.addLongValueToDatabase(1, "Votes."+nextID+".Vote");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voted==false){
                    mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                    mQuery = mDBRef.orderByChild("Votes");

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            int tmpId;
                            if(mTableIDs.size()==0){
                                nextID="ID1";
                            }
                            else{
                                tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                                nextID="ID"+tmpId;
                            }
                            voted=true;
                            db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                            db.addStringValueToDatabase(userName, "Votes."+nextID+".UserName");
                            db.addLongValueToDatabase(2, "Votes."+nextID+".Vote");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voted==false){
                    mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                    mQuery = mDBRef.orderByChild("Votes");

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            int tmpId;
                            if(mTableIDs.size()==0){
                                nextID="ID1";
                            }
                            else{
                                tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                                nextID="ID"+tmpId;
                            }
                            voted=true;
                            db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                            db.addStringValueToDatabase(userName, "Votes."+nextID+".UserName");
                            db.addLongValueToDatabase(3, "Votes."+nextID+".Vote");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voted==false){
                    mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                    mQuery = mDBRef.orderByChild("Votes");

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            int tmpId;
                            if(mTableIDs.size()==0){
                                nextID="ID1";
                            }
                            else{
                                tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                                nextID="ID"+tmpId;
                            }
                            voted=true;
                            db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                            db.addStringValueToDatabase(userName, "Votes."+nextID+".UserName");
                            db.addLongValueToDatabase(4, "Votes."+nextID+".Vote");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voted==false){
                    mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                    mQuery = mDBRef.orderByChild("Votes");

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            int tmpId;
                            if(mTableIDs.size()==0){
                                nextID="ID1";
                            }
                            else{
                                tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                                nextID="ID"+tmpId;
                            }
                            voted=true;
                            db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                            db.addStringValueToDatabase(userName, "Votes."+nextID+".UserName");
                            db.addLongValueToDatabase(5, "Votes."+nextID+".Vote");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });
    }

    private Question getQuestionFromIntent() {
        Question questionTmp;
        questionTmp= (Question) getIntent().getSerializableExtra("question");
        return questionTmp;
    }
}
