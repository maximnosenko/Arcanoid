package com.company;

import java.awt.*;

public abstract class AbstractBall extends AbstractActor implements Movable,Runnable{

    int minY;// для пересечения нижнего поля
    int speed;//скорость шарика
    double dirX,dirY,r;//нужны для направления шарика
    AbstractPlatform platform;
    boolean go=true;

    AbstractBall(double x, double y, int sizeX, int sizeY, AbstractPlatform platform)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        setCoordinates(x, y);
        this.platform = platform;
    }

    abstract void setDir(double newX, double newY);//Задает новое направление

    @Override
    public void DestroyBall() {//уменьшает жизни и ставит шарик на исходную
        setCoordinates(platform.centerX - sizeX/2, platform.getY() - sizeY - 10);
    }

    @Override
    abstract public void run();

    @Override
    public int getSpeed() {
        return speed;
    }//возвращает скорость

    @Override
    public double getXDir() {
        return this.dirX;
    }//направление по X

    @Override
    public double getYDir() {
        return this.dirY;
    }//Направление по Y
}
