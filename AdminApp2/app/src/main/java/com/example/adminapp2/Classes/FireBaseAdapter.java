package com.example.adminapp2.Classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FireBaseAdapter {

    private static final String TAG = "myLogs";
    private DatabaseReference dbSignup;
    private ArrayList<String> names;

    public FireBaseAdapter() {

    }

    public void addStringValueToDatabase(String value, String path) {
        Log.d(TAG, "Arrived in addStringValueToDatabase");

        ArrayList<String> pathArray = sliceStringByChar(path, '.');

        Log.d(TAG, "pathArray declarated");

        executeDatabaseSetValueByValueAndPathArray(value, pathArray);
    }

    private void executeDatabaseSetValueByValueAndPathArray(String value, ArrayList<String> pathArray) {

        Log.d(TAG, "Arrived in the executeDatabaseSetValueByValueAndPathArray method");
        dbSignup = FirebaseDatabase.getInstance().getReference(pathArray.get(0));

        Log.d(TAG, "dbSignup declarated");

        if (pathArray.size() == 2) {
            dbSignup.child(pathArray.get(1)).setValue(value);
            Log.d(TAG, "if size==" + pathArray.size() + ": " + pathArray.get(0) + ", " + pathArray.get(1));
        }
        if (pathArray.size() == 3) {
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).setValue(value);
            //Log.d(TAG, "if size=="+ pathArray.size()+": "+ pathArray.get(0)+", "+pathArray.get(1)+", "+pathArray.get(2));
        }
        if (pathArray.size() == 4) {
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).child(pathArray.get(3)).setValue(value);
            Log.d(TAG, "if size==" + pathArray.size());
        }
        if (pathArray.size() == 5) {
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).child(pathArray.get(3)).child(pathArray.get(4)).setValue(value);
            Log.d(TAG, "if size==" + pathArray.size());
        }
        if (pathArray.size() == 6) {
            dbSignup.child(pathArray.get(1)).child(pathArray.get(2)).child(pathArray.get(3)).child(pathArray.get(4)).child(pathArray.get(5)).setValue(value);
            Log.d(TAG, "if size==" + pathArray.size());
        }
    }


    private ArrayList<String> sliceStringByChar(String str, char chr) {
        int actIndex = 0;
        int i, n = countOccurenceOfACharacter(str, chr);
        //Log.d(TAG, "Arrived in sliceStringByChar");
        ArrayList<String> stringArray = new ArrayList<>();
        //Log.d(TAG, "ArrayList declared");
        for (i = 0; i <= n; i++) {
            if (str.indexOf(chr, actIndex) != -1) {
                stringArray.add(i, str.substring(actIndex, str.indexOf(chr, actIndex)));
                Log.d(TAG, "End of if - cycle" + i + ": " + str.substring(actIndex, str.indexOf(chr, actIndex)));
                actIndex = str.indexOf(chr, actIndex) + 1;
                Log.d(TAG, "akt index: " + actIndex);
            } else {
                Log.d(TAG, "Last slicing");
                stringArray.add(i, str.substring(actIndex, str.length()));
            }
        }
        Log.d(TAG, "Slicing ended");
        return stringArray;
    }

    private int countOccurenceOfACharacter(String str, char chr) {
        int ctr = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == chr) {
                ctr++;
            }
        }
        Log.d(TAG, "Occurances counted: " + ctr);
        return ctr;
    }
}
