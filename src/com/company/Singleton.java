package com.company;

import java.util.Vector;

public class Singleton {
    private static Singleton instance;
    private Vector<AbstractActor> vector=new Vector<>();
    int life=3;

    public Vector<AbstractActor> getVector(){
        return vector;
    }

    public static Singleton getInstance() {
        if(instance==null)
            instance=new Singleton();
        return instance;
    }
}
