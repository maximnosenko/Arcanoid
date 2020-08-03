package com.company;

import java.awt.*;

public class Platform extends AbstractPlatform {
    Platform(double x, double y, int sizeX, int sizeY,Habitat habitat) {
        super(x, y, sizeX, sizeY);
        this.habitat=habitat;
    }

    @Override
    public void painting(Graphics g) {//рисование панели
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x,(int)y,sizeX,sizeY);
        g.drawRect((int)x,(int)y,sizeX,sizeY);
    }


    public synchronized void isPlatformMoving()
    {
        try {
            if(platformMoving)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isMoving) {
            isPlatformMoving();
            if (getX() + moveDirection * speed < maxX && getX() + moveDirection * speed > minX) {
                setCoordinates((getX() + moveDirection * speed), getY());
                if (!ballMoving)
                    ball.setCoordinates(ball.getX() + moveDirection * speed,ball.getY());
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                isMoving = false;
            }
        }
    }
}
