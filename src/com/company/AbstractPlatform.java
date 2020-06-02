package com.company;

public abstract class AbstractPlatform extends AbstractActor implements Runnable,PlayerControl {

    int speed=5,maxX=730,minX=10;
    int moveDirection=0;
    boolean isMoving=true;

    AbstractPlatform(double x,double y,int sizeX,int sizeY)
    {
        this.x=x;
        this.y=y;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    @Override
    public void moveRight() {
        moveDirection=1;
    }

    @Override
    public void moveLeft() {
        moveDirection=-1;
    }

    public void stay(){//остановка платформы
        moveDirection=0;
    }

    @Override
    public void createBall() {

    }
}
