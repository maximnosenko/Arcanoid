package com.company;

import java.awt.*;

public class Ball extends AbstractBall implements Movable {
    Ball(double x, double y,int sizeX,int sizeY) {
        super(x, y,sizeX,sizeY);
        dirX=-0.3;
        dirY=0.8;
        speed=5;
    }

    @Override
    public void painting(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int)x,(int)y,sizeX,sizeY);
        g.drawOval((int)x,(int)y,sizeX,sizeY);
    }

    @Override
    public void onCollision(AbstractActor actor,int dir)//Moveble
    {
        switch (dir){
            case 1: {
                this.setX(actor.right);
                dirX=-dirX;
                break;
            }
            case 2:{
                this.setX(actor.left-sizeX);
                dirX=-dirX;
                break;
            }
            case 3:{
                this.setY(actor.down);
                dirY=-dirY;
                break;
            }
            case 4:{
                this.setY(actor.up-sizeY);
                dirY=-dirY;
                break;
            }
            default:
        }
    }

    @Override
    void setDir(double newX, double newY) {
        dirX=newX;
        dirY=newY;
    }

    @Override
    public void run() {
        while(isMoving){
            setCoordinates(getX()+getXDir()*getSpeed(),getY()+getYDir()*getSpeed());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                isMoving=false;
            }
        }
    }
}
