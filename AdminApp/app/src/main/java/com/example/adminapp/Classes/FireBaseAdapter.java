package com.example.adminapp.Classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FireBaseAdapter {
    public FireBaseAdapter(){

    }

    public void addStringValueToDatabase(String value, String path){
        ArrayList<String> pathArray= sliceStringByChar(path, '.');

        executeDatabaseSetValueByValueAndPathArray(value, pathArray);
    }

    private void executeDatabaseSetValueByValueAndPathArray(String value, ArrayList<String> pathArray){
        DatabaseReference dbSignup= FirebaseDatabase.getInstance().getReference(pathArray.get(0));
        if(pathArray.size()==3){
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).setValue(value);
        }
        if(pathArray.size()==4){
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).child(pathArray.get(3)).setValue(value);
        }
        if(pathArray.size()==5){
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).child(pathArray.get(3)).child(pathArray.get(4)).setValue(value);
        }
        if(pathArray.size()==6){
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).child(pathArray.get(3)).child(pathArray.get(4)).child(pathArray.get(5)).setValue(value);
        }
    }

    private ArrayList<String> sliceStringByChar(String str, char chr){
        int actIndex=0;
        int i;
        ArrayList<String> stringArray=new ArrayList<>();
        for( i=0 ; i<= countOccurenceOfACharacter(str, chr); i++) {
            if(str.indexOf(chr,actIndex)!= -1){
                stringArray.set(i, str.substring(actIndex, str.indexOf(chr,actIndex)));
                actIndex= str.indexOf(chr,actIndex);
            }
            else{
                stringArray.set(i, str.substring(actIndex, str.length()));
            }
        }
        return stringArray;
    }

    private int countOccurenceOfACharacter(String str, char chr){
        int ctr=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==chr){
                ctr++;
            }
        }
        return ctr;
    }
}
