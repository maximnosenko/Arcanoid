package com.company;

import java.awt.*;

public class Platform extends AbstractPlatform {
    AbstractBall ball;
    Platform(double x, double y, int sizeX, int sizeY,AbstractBall ball) {
        super(x, y, sizeX, sizeY);
        setCoordinates(this.x,this.y);
        this.ball=ball;
    }

    @Override
    public void painting(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x,(int)y,sizeX,sizeY);
        g.drawRect((int)x,(int)y,sizeX,sizeY);
    }

    @Override
    public void run() {
        while (isMoving) {
            synchronized (this){
            synchronized (ball) {
                if (getX() + moveDirection * speed < maxX && getX() + moveDirection * speed > minX&&moveDirection!=0) {
                    //ball.notify();
                    setCoordinates((getX() + moveDirection * speed), getY());
                    if (ball.getXDir() == 0 && ball.getYDir() == 0 && moveDirection == 1) {
                        //ball.x+=5;
                        ball.setX(ball.getX() + moveDirection * speed);
                    }
                    if (ball.getXDir() == 0 && ball.getYDir() == 0 && moveDirection == -1) {
                        // ball.x-=5;
                        ball.setX(ball.getX() + moveDirection * speed);
                    }
                }
            }
            if(moveDirection==0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                isMoving = false;
            }
        }
    }
}