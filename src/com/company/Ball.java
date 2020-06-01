package com.company;

import java.awt.*;

public class Ball extends AbstractBall implements Movable {
    //int w,h;
    Habitat habitat;

    Ball(int x, int y,int sizeX,int sizeY) {
        super(x, y,sizeX,sizeY);
        singleton=Singleton.getInstance();
        dirX=-0.6;
        dirY=0.8;
        speed=2;
    }

    @Override
    public void painting(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x,y,sizeX,sizeY);
        g.drawOval(x,y,sizeX,sizeY);
    }

    @Override
    public void onCollision(AbstractActor actor,int dir)//Moveble
    {
        switch (dir){
            case 1: {
                this.setX(actor.right);
                dirX=-dirX;
                //System.out.println(getX()+getX());
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
                //System.out.println(this.x+this.y);
                break;
            }
            case 4:{
                this.setY(actor.up-sizeY);
                dirY=-dirY;
                //System.out.println(this.x+this.y);
                break;
            }
            default:
        }
        System.out.println(speed);
            //System.out.println(actor.left+sizeX);
            //this.right=actor.left;

        //isMoving=false;
        //return 0;
    }//isMoveing false
    //onColigen написать что шарик столкунлся, чтобы обрабатывать столкновение

    @Override
    void setDir(double newX, double newY) {///////////////////////////
        dirX=newX;
        dirY=newY;
    }

    @Override
    public void run() {
        while(isMoving){
            setCoordinates((int)(getX()+getXDir()*getSpeed()),(int)(getY()+getYDir()*getSpeed()));
            System.out.println("getSpeed "+getSpeed());
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
