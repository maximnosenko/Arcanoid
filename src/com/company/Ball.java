package com.company;

import java.awt.*;

public class Ball extends AbstractBall {

    Ball(double x, double y,int sizeX,int sizeY, AbstractPlatform platform) {
        super(x, y,sizeX,sizeY, platform);
        speed=2;
    }

    @Override
    public void painting(Graphics g) {//Рисование шарика
        g.setColor(Color.RED);
        g.fillOval((int)x,(int)y,sizeX,sizeY);
        g.drawOval((int)x,(int)y,sizeX,sizeY);
    }

    @Override
    public void onCollision(AbstractActor actor,int dir)//осткок шарика от объектов
    {
        switch (dir){
            case 1: {
                this.setX(actor.right);//передаем сторону объекта к которой прилетел шарик
                dirX=-dirX;//изменения направления шарика, чтобы получился отскок
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

        //Фича

        //Движущаяся платформа изменяет направление движения шарика
        if (actor instanceof AbstractPlatform) {
            dirX += platform.moveDirection;
            double r = Math.sqrt(Math.pow(dirX, 2) + Math.pow(dirY, 2));
            dirX /= r;
            dirY /= r;
        }
    }

    @Override
    void setDir(double newX, double newY) {//получается координаты мышки и задает нужное направление
        System.out.println(centerX);
        System.out.println(centerY);
        r=Math.sqrt(Math.pow(newX-centerX,2)+Math.pow(newY-centerY,2));
        System.out.println(r);
        dirX=(newX-centerX)/r;
        dirY=(newY-centerY)/r;
        go=true;
    }

    @Override
    public synchronized void run()//запускает поток шарика
    {
        while(go){
            platform.isBallMoving();//метод для остановки шарика
            //задаем новые координаты,для перемещение шарика
            setCoordinates(getX()+getXDir()*getSpeed(),getY()+getYDir()*getSpeed());
            if (centerY >= platform.centerY)
                platform.habitat.BallMissed();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
