package com.company;

import java.awt.*;

public class Platform extends AbstractPlatform {
    Platform(double x, double y, int sizeX, int sizeY) {
        super(x, y, sizeX, sizeY);
    }

    @Override
    public void painting(Graphics g) {//рисование панели
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x,(int)y,sizeX,sizeY);
        g.drawRect((int)x,(int)y,sizeX,sizeY);
    }

    @Override
    public void run() {
        while (isMoving) {
            if (getX() + moveDirection * speed < maxX && getX() + moveDirection * speed > minX) {
                setCoordinates((getX() + moveDirection * speed), getY());
                if (!ballMoving)
                    ball.setX(ball.getX() + moveDirection * speed);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                isMoving = false;
            }
        }
    }
}
