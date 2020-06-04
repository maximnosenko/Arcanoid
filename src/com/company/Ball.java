package com.company;

import java.awt.*;

public class Ball extends AbstractBall {
    Ball(double x, double y,int sizeX,int sizeY, AbstractPlatform platform) {
        super(x, y,sizeX,sizeY, platform);
        speed=3;
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
            case 5:{
                dirX=Math.sqrt(0.5);
                dirY=-Math.sqrt(0.5);
                break;
            }
            case 6:{
                dirX=Math.sqrt(0.5);
                dirY=Math.sqrt(0.5);
                break;
            }
            case 7:{
                dirX=-Math.sqrt(0.5);
                dirY=Math.sqrt(0.5);
                break;
            }
            case 8:{
                dirX=-Math.sqrt(0.5);
                dirY=-Math.sqrt(0.5);
            }
            default:
        }
    }

    @Override
    void setDir(double newX, double newY) {//получается координаты мышки и задает нужное направление
        r=Math.sqrt(Math.pow(newX-centerX,2)+Math.pow(newY-centerY,2));
        dirX=(newX-centerX)/r;
        dirY=(newY-centerY)/r;
        //isMoving=true;
    }

    public void paintingCount(Graphics g,int xball)
    {
        g.setColor(Color.RED);
        g.fillOval(xball,10,15,15);
        g.drawOval(xball,10,15,15);
    }

    @Override
    public synchronized void run() {
        while(true){
            platform.isBallMoving();
            setCoordinates(getX()+getXDir()*getSpeed(),getY()+getYDir()*getSpeed());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
