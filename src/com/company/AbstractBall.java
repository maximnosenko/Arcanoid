package com.company;

public abstract class AbstractBall extends AbstractActor implements Movable,Runnable,Destructable{

    int minY;// для пересечения нижнего поля
    int speed;//скорость шарика
    double dirX,dirY;//нужны для направления шарика
    boolean isMoving=true;

    AbstractBall(double x, double y, int sizeX, int sizeY)
    {
        this.x=x;
        this.y=y;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    @Override
    public void setCoordinates(double x, double y) {
        super.setCoordinates(x, y);
        this.centerX=x+sizeX/2;
        this.centerY=y+sizeY/2;
    }

    abstract void setDir(double newX, double newY);

    @Override
    public void Destroy() {

    }

    @Override
    public int ToggleMovement() {
        return 0;
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
