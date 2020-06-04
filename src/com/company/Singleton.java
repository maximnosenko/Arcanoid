package com.company;

import java.util.Vector;

public class Singleton {
    private static Singleton instance;
    private Vector<AbstractActor> vector=new Vector<>();
    int life=3;
    int points = 0;

    public Vector<AbstractActor> getVector(){
        return vector;
    }

    public static Singleton getInstance() {
        if(instance==null)
            instance=new Singleton();
        return instance;
    }

    public void AddPoints (int points)
    {
        this.points += points;
    }

    public int Getpoints()
    {
        return this.points;
    }

    public void SetPoints(int points)
    {
        this.points = points;
    }
}
