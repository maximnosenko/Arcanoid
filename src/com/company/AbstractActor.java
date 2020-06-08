package com.company;

import java.io.Serializable;

public abstract class AbstractActor implements Behavior, Serializable {
    double x,y;
    int sizeX,sizeY;//размер объекта
    double right,left,up,down;//стороны объекта
    double centerX,centerY;//центр стороны

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x=x;
    }

    @Override
    public void setY(double y) {
        this.y=y;
    }

    public int getSizeX() {return sizeX; }

    public int getSizeY() {return sizeY; }

    @Override
    public void setCoordinates(double x, double y) {
        this.x=x;
        this.y=y;
        this.right=x+sizeX;
        this.left=x;
        this.up=y;
        this.down=y+sizeY;
        this.centerX = x + sizeX/2;
        this.centerY = y + sizeY/2;
    }

}
