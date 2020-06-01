package com.company;

public abstract class AbstractActor implements Behavior {
    int x,y;
    int sizeX,sizeY;//размер объекта
    int right,left,up,down;

    //getLeft,getLeft

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    public int getSizeX() {return sizeX; }

    public int getSizeY() {return sizeY; }

    @Override
    public void setCoordinates(int x, int y) {
        this.x=x;
        this.y=y;
        this.right=x+sizeX;
        this.left=x;
        this.up=y;
        this.down=y+sizeY;
    }

}
