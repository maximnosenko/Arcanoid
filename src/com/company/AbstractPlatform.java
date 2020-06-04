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
    public void moveRight() {//движение платформы вправо
        moveDirection=1;
    }

    @Override
    public void moveLeft() {//движение платформы вправо
        moveDirection=-1;
    }

    public void stay(){//остановка платформы
        moveDirection=0;
    }

    public void createBall(String type) {//создание нового шарика
        switch (type) {
            case "Ball":
                ball = new Ball(centerX - ballSize / 2, getY() - ballSize - 10, ballSize, ballSize, this);
                break;
        }
        ballMoving = false;
        new Thread(ball).start();//? т.к. в game есть
    }

    public AbstractBall getBall()
    {
        return ball;
    }

    public synchronized void isBallMoving() {//если мячик не активен, то поток ждет
        try {
            if(!ballMoving)
                wait();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void ToggleBallMovement() {//если мячик запушен, то поток возобновляется
        if (!ballMoving)
            notifyAll();
        ballMoving = !ballMoving;
    }
}
