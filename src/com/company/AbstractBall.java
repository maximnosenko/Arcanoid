package com.company;

public abstract class AbstractBall extends AbstractActor implements Movable,Runnable,Destructable{

    int minY;// для пересечения нижнего поля
    int speed;//скорость шарика
    double dirX,dirY,r;//нужны для направления шарика
    boolean isMoving=true,ready=true;
    int life=3;

    AbstractBall(double x, double y, int sizeX, int sizeY)
    {
        this.x=x;
        this.y=y;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    abstract void setDir(double newX, double newY);

    @Override
    public void Destroy() {
        //isMoving=false;
        //life=life-1;
    }

    @Override
    public void ToggleMovement() {
        ready=true;
        //isMoving=false;
        dirX=0;
        dirY=0;
        life=life-1;
        //setCoordinates(getX(),getY());
    }

    @Override
    abstract public void run();

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed=speed;
    }

    @Override
    public void setXDir(double dirX) {
        this.dirX=dirX;
    }

    @Override
    public void setYDir(double dirY) {
        this.dirY=dirY;
    }

    @Override
    public double getXDir() {
        return this.dirX;
    }

    @Override
    public double getYDir() {
        return this.dirY;
    }
}
