package com.company;

import java.util.Vector;

public class Singleton {
    private static Singleton instance;
    private Vector<AbstractActor> vector=new Vector<>();//вектор в которм хранятся все объекты
    int life=3;//общее время жизни
    int points = 0;//очки

    public Vector<AbstractActor> getVector(){
        return vector;
    }

    public static Singleton getInstance() {
        if(instance==null)
            instance=new Singleton();
        return instance;
    }

    public void refreshVector()
    {
        vector.clear();
    }

    public void AddPoints (int points)//добавление очков
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
