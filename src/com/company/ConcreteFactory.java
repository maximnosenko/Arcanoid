package com.company;

public class ConcreteFactory implements Factory {
    Singleton singleton=Singleton.getInstance();

    @Override
    public void createBlock(double x,double y,int sizeX,int sizeY) {
        singleton.getVector().add(new Block(x,y,sizeX,sizeY));
    }

    @Override
    public void createWall(double x,double y,int sizeX,int sizeY) {
        singleton.getVector().add(new Wall(x,y,sizeX,sizeY));
    }
}
