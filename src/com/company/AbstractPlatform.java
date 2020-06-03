package com.company;

public abstract class AbstractPlatform extends AbstractActor implements Runnable,PlayerControl {

    int speed=5,maxX=730,minX=10;
    int moveDirection=0;
    boolean isMoving=true;
    int ballSize = 20;
    AbstractBall ball;
    boolean ballMoving;

    AbstractPlatform(double x,double y,int sizeX,int sizeY)
    {
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        setCoordinates(x,y);
        createBall("Ball");
    }

    @Override
    public void moveRight() {
        moveDirection=1;
    }

    @Override
    public void moveLeft() {
        moveDirection=-1;
    }

    public void stay(){//остановка платформы
        moveDirection=0;
    }

    public void createBall(String type) {
        switch (type) {
            case "Ball":
                ball = new Ball(centerX - ballSize / 2, getY() - ballSize - 10, ballSize, ballSize, this);
                break;
        }
        ballMoving = false;
        new Thread(ball).start();
    }

    public AbstractBall getBall()
    {
        return ball;
    }

    public synchronized void isBallMoving() {
        try {
            if(!ballMoving)
                wait();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void ToggleBallMovement() {
        if (!ballMoving)
            notifyAll();
        ballMoving = !ballMoving;
    }
}
