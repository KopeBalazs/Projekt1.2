package com.example.userapp2.Controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp2.Models.Group;
import com.example.userapp2.Models.Question;
import com.example.userapp2.R;
import com.example.userapp2.Views.ActivityJoinQuestion;
import com.example.userapp2.Views.ActivityVoting;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterQuestion extends RecyclerView.Adapter<RecyclerViewAdapterQuestion.ViewHolder> {
    private static final String TAG = "debug1";

    private List<String> mQuestions;
    private Context mContext;
    private ArrayList<Question> questions;
    private Intent intentStartViewQuestions;
    private Bundle bundle=new Bundle();

    public RecyclerViewAdapterQuestion(Context mContext, List<String> mGroupNames, ArrayList<Question> questions) {
        this.mQuestions = mGroupNames;
        this.mContext = mContext;
        this.questions= questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.textGroupName.setText(mQuestions.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ mQuestions.get(position));
                Toast.makeText(mContext, mQuestions.get(position), Toast.LENGTH_SHORT).show();
                intentStartViewQuestions = new Intent(mContext, ActivityVoting.class);
                bundle.putSerializable("question", questions.get(position));
                intentStartViewQuestions.putExtras(bundle);
                mContext.startActivity(intentStartViewQuestions);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textGroupName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textGroupName= itemView.findViewById(R.id.textView);
            parentLayout= itemView.findViewById(R.id.parent_layout);
        }
    }
}
