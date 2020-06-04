package com.company;

import java.awt.*;

public abstract class AbstractBall extends AbstractActor implements Movable,Runnable{

    int minY;// для пересечения нижнего поля
    int speed;//скорость шарика
    double dirX,dirY,r;//нужны для направления шарика
    int life=3;
    AbstractPlatform platform;

    AbstractBall(double x, double y, int sizeX, int sizeY, AbstractPlatform platform)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        setCoordinates(x, y);
        this.platform = platform;
    }

    abstract void setDir(double newX, double newY);

    abstract public void paintingCount(Graphics g,int xball);

    @Override
    public void DestroyBall() {//уменьшает жизни и ставит шарик на исходную
        //life=life-1;
        setCoordinates(platform.centerX - sizeX/2, platform.getY() - sizeY - 10);
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
