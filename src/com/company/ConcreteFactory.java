package com.company;

import java.io.Serializable;

public class ConcreteFactory implements Factory, Serializable {
    Singleton singleton=Singleton.getInstance();
    //создание блока
    @Override
    public void createBlock(double x,double y,int sizeX,int sizeY) {
        singleton.getVector().add(new Block(x,y,sizeX,sizeY));
    }
    //создание стены
    @Override
    public void createWall(double x,double y,int sizeX,int sizeY) {
        singleton.getVector().add(new Wall(x,y,sizeX,sizeY));
    }
}
