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

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private Question question;

    private FireBaseAdapter db=new FireBaseAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        init();
        vote();
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
    }

    private void vote(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                mQuery = mDBRef.orderByChild("Votes");

                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                            mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                        }
                        int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                        //System.out.println("tmpId= "+tmpId);
                        nextID="ID"+tmpId;

                        db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                        db.addStringValueToDatabase("ID2", "Votes."+nextID+".UID");
                        db.addLongValueToDatabase(1, "Votes."+nextID+".Vote");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                mQuery = mDBRef.orderByChild("Votes");

                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                            mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                        }
                        int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                        //System.out.println("tmpId= "+tmpId);
                        nextID="ID"+tmpId;

                        db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                        db.addStringValueToDatabase("ID2", "Votes."+nextID+".UID");
                        db.addLongValueToDatabase(2, "Votes."+nextID+".Vote");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                mQuery = mDBRef.orderByChild("Votes");

                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                            mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                        }
                        int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                        //System.out.println("tmpId= "+tmpId);
                        nextID="ID"+tmpId;

                        db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                        db.addStringValueToDatabase("ID2", "Votes."+nextID+".UID");
                        db.addLongValueToDatabase(3, "Votes."+nextID+".Vote");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                mQuery = mDBRef.orderByChild("Votes");

                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                            mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                        }
                        int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                        //System.out.println("tmpId= "+tmpId);
                        nextID="ID"+tmpId;

                        db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                        db.addStringValueToDatabase("ID2", "Votes."+nextID+".UID");
                        db.addLongValueToDatabase(4, "Votes."+nextID+".Vote");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBRef = FirebaseDatabase.getInstance().getReference("Votes");
                mQuery = mDBRef.orderByChild("Votes");

                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                            mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                        }
                        int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                        //System.out.println("tmpId= "+tmpId);
                        nextID="ID"+tmpId;

                        db.addStringValueToDatabase(question.getId(), "Votes."+nextID+".QID");
                        db.addStringValueToDatabase("ID2", "Votes."+nextID+".UID");
                        db.addLongValueToDatabase(5, "Votes."+nextID+".Vote");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private Question getQuestionFromIntent() {
        Question questionTmp;
        questionTmp= (Question) getIntent().getSerializableExtra("question");
        return questionTmp;
    }
}
