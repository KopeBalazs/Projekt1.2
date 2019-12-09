package com.adminapp2.views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.adminapp2.Classes.FireBaseAdapter;
import com.adminapp2.Classes.RecyclerViewAdapterGroup;
import com.adminapp2.Classes.RecyclerViewAdapterQuestion;
import com.adminapp2.models.Group;
import com.adminapp2.models.Question;
import com.example.adminapp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewQuestionsInGroupsActivity extends AppCompatActivity {

    private static final String TAG = "ViewQuestionsInGroupsAc";

    private ArrayList<Question> questionsOfTheActGroup=new ArrayList<>();
    private Group actGroup;
    private TextView textViewActualGroup;
    private ArrayList<String> questionStringsOfTheActGroup=new ArrayList<>();
    private FireBaseAdapter db=new FireBaseAdapter();
    private ImageButton buttonAddQuestion;
    private EditText editTextNewQuestion;
    private ArrayList<String> mTableIDs =new ArrayList<>();
    private String nextID="ID0";
    private Bundle bundle=new Bundle();
    private Intent intentReloadActivity;

    private Query mQuery;
    private DatabaseReference mDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions_in_groups);

        init();
        uploadQuestionsListAndRecyclerView();
    }

    private Group getGroupFromIntent() {
        Group groupTmp;
        groupTmp= (Group) getIntent().getSerializableExtra("adminsGroups");
        return groupTmp;
    }

    private void init(){
        actGroup=getGroupFromIntent();
        textViewActualGroup=findViewById(R.id.textActualGroup);
        textViewActualGroup.setText(actGroup.getName().toString());
        buttonAddQuestion=findViewById(R.id.buttonAddQuestion);
        editTextNewQuestion=findViewById(R.id.editTextNewQuestion);
        intentReloadActivity = new Intent(ViewQuestionsInGroupsActivity.this, ViewQuestionsInGroupsActivity.class);
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

                        Question tempQuestion = new Question(questionId, timeStarted, duration, group, value, state);

                        System.out.println(tempQuestion);

                        questionsOfTheActGroup.add(tempQuestion);
                        System.out.println("after");
                    }
                }
                questionStringsOfTheActGroup=uploadquestionStringsOfTheActGroup(questionsOfTheActGroup);
                initRecyclerView(questionStringsOfTheActGroup, questionsOfTheActGroup);

                buttonAddQuestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "Arrived in onClick()");
                        createQuestion();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void createQuestion(){
        buttonAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextNewQuestion.length()>0){

                    mDBRef = FirebaseDatabase.getInstance().getReference("Question");
                    mQuery = mDBRef.orderByChild("Question");

                    mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot mySnapshot : dataSnapshot.getChildren()) {
                                mTableIDs.add(mySnapshot.getKey().toString());  //Table IDs
                            }
                            if(mTableIDs.size()==0){
                                nextID="ID1";
                            }
                            else{
                                int tmpId=Integer.parseInt(mTableIDs.get(mTableIDs.size()-1).substring(2))+1;
                                //System.out.println("tmpId= "+tmpId);
                                nextID="ID"+tmpId;
                            }


                            try{
                                Thread.sleep(2000);
                            }
                            catch (Exception e){

                            }

                            System.out.println(nextID);

                            String questionId = nextID;
                            String question = editTextNewQuestion.getText().toString();

                            Question tempQuestion = new Question(questionId,"", 0 ,actGroup, question, false);
                            questionsOfTheActGroup.add(tempQuestion);

                            db.addStringValueToDatabase(editTextNewQuestion.getText().toString(),"Question."+nextID+".Question");
                            db.addStringValueToDatabase(actGroup.getId(),"Question."+nextID+".GID");
                            db.addStringValueToDatabase(" ","Question."+nextID+".DateStarted");
                            db.addLongValueToDatabase(0,"Question."+nextID+".Duration");
                            db.addBooleanValueToDatabase(false,"Question."+nextID+".State");

                            bundle.putSerializable("adminsGroups", actGroup);
                            intentReloadActivity.putExtras(bundle);
                            startActivity(intentReloadActivity);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d(TAG, "Cancelled");
                        }
                    });


                }
                else{
                    Toast toastInvalidGroupName=Toast.makeText(getApplicationContext(), "You need to add a question first!", Toast.LENGTH_LONG);
                    toastInvalidGroupName.show();
                }
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
        RecyclerViewAdapterQuestion adapter=new RecyclerViewAdapterQuestion(this, nameList, questions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
