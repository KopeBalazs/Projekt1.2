package com.example.adminapp2.Classes;

public class IdGenerator {
    private int id=0;

    public IdGenerator(){

    }

    public int getAnID() {
        int aktId=id;
        id++;
        return aktId;
    }
}
