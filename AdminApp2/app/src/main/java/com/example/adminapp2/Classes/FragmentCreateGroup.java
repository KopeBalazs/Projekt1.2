package com.example.adminapp2.Classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adminapp2.R;

public class FragmentCreateGroup extends Fragment {
    private static final String TAG = "FragmentCreateGroup";

    private EditText textGroupName;
    private EditText textGroupDescription;
    private Button createGroupButton;
    private FireBaseAdapter db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_group,container,false);
        init(view);

        createGroup();

        return view;
    }

    private void init(View view){
        textGroupName= (EditText)view.findViewById(R.id.editTextNewGroupName);
        createGroupButton= (Button)view.findViewById(R.id.buttonCreateGroup);
        db=new FireBaseAdapter();
    }

    private void createGroup(){
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputGroupName=textGroupName.getText().toString();
                if(inputGroupName.length()>0){
                    IdGenerator id=new IdGenerator();
                    db.addStringValueToDatabase(inputGroupName,"Groups."+id.getAnID()+".Name");
                }
            }
        });
    }
}
