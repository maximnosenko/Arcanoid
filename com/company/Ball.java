package com.company;

import java.awt.*;

public class Ball extends AbstractBoll implements Movable {
    //int w,h;
    Habitat habitat;

    Ball(int x, int y,int sizeX,int sizeY) {
        super(x, y,sizeX,sizeY);
        singleton=Singleton.getInstance();
        dirX = 1;
        dirY = -1;
        speed = 2;
    }

    @Override
    public void painting(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x,y,sizeX,sizeY);
        g.drawOval(x,y,sizeX,sizeY);
    }

    @Override
    void setDir(double newX, double newY) {///////////////////////////
        dirX=newX;
        dirY=newY;
    }

    @Override
    public void run() {
        while(isMoving){
            setCoordinates((int)(getX()+getXDir()*getSpeed()),(int)(getY()+getYDir()*getSpeed()));
            //setDir(Math.random() * 2 - 1, Math.random() * 2 - 1);
            //System.out.println(right);
            //System.out.println(left);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                isMoving=false;
            }
        }

    }
}
