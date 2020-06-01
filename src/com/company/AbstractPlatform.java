package com.company;

public abstract class AbstractPlatform extends AbstractActor implements Runnable,PlayerControl {

    int speed,maxX,maxY;

    AbstractPlatform(double x,double y,int maxX ,int maxY)
    {
        this.x=x;
        this.y=y;
        this.maxX=maxX;
        this.maxY=maxY;
    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void createBall() {

    }

    @Override
    public void run() {

    }
}
