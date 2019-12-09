package com.example.userapp2.Views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp2.Controllers.RecyclerViewAdapterGroup;
import com.example.userapp2.Controllers.RecyclerViewAdapterQuestion;
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
import java.util.List;

public class ActivityJoinQuestion extends AppCompatActivity {

    private static final String TAG = "ActivityJoinQuestion";
    private ArrayList<Question> questionsOfTheActGroup=new ArrayList<>();

    private Query mQuery;
    private DatabaseReference mDBRef;
    private ArrayList<String> questionStringsOfTheActGroup=new ArrayList<>();
    private Group actGroup;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        uploadQuestionsListAndRecyclerView();
    }

    private Group getGroupFromIntent() {
        Group groupTmp;
        groupTmp= (Group) getIntent().getSerializableExtra("adminsGroups");
        return groupTmp;
    }
    private String getUserNameFromIntent() {
        String userName;
        userName= (String) getIntent().getSerializableExtra("userName");
        return userName;
    }

    private void init(){
        actGroup=getGroupFromIntent();
        userName=getUserNameFromIntent();
    }

    private void uploadQuestionsListAndRecyclerView() {
        mDBRef = FirebaseDatabase.getInstance().getReference("Question");
        mQuery = mDBRef.orderByChild("Question");

        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot questionsTmp : dataSnapshot.getChildren()) {
                    if (questionsTmp.child("GID").getValue().toString().equals(actGroup.getId().toString())) {
                        /*String groupId = groupTmp.getKey().toString();
                        String adminID = groupTmp.child("AID").getValue().toString();
                        String groupName = groupTmp.child("Name").getValue().toString();*/

                        String questionId= questionsTmp.getKey().toString();

                        //Calendar calendar = Calendar.getInstance();
                        //.util.Date now = calendar.getTime();
                        String timeStarted= questionsTmp.child("DateStarted").getValue().toString();
                        long duration= Long.parseLong(questionsTmp.child("Duration").getValue().toString());
                        Group group=actGroup;
                        String value= questionsTmp.child("Question").getValue().toString();
                        boolean state= Boolean.parseBoolean(questionsTmp.child("State").getValue().toString());

                        Question tempQuestion = new Question(questionId, timeStarted, duration, value, state);

                        System.out.println(tempQuestion);

                        questionsOfTheActGroup.add(tempQuestion);
                        System.out.println("after");
                    }
                }
                questionStringsOfTheActGroup=uploadquestionStringsOfTheActGroup(questionsOfTheActGroup);
                initRecyclerView(questionStringsOfTheActGroup, questionsOfTheActGroup);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private ArrayList<String> uploadquestionStringsOfTheActGroup(ArrayList<Question> questions){
        ArrayList<String> tempGroupsQuestions=new ArrayList<String>();

        for(int i=0; i<questions.size(); i++){
            tempGroupsQuestions.add(questions.get(i).getValue());
        }

        return tempGroupsQuestions;
    }
    private void initRecyclerView(List<String> nameList, ArrayList<Question> questions){
        // Log.d(TAG, "initRecyclerView: init recyclerview."+findViewById(R.id.recyclerView));
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        RecyclerViewAdapterQuestion adapter=new RecyclerViewAdapterQuestion(this, nameList, questions, userName);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
