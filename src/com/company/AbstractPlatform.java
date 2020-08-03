package com.company;

public abstract class AbstractPlatform extends AbstractActor implements Runnable,PlayerControl {

    public int speed=5,maxX=730,minX=10;//скорость и ограничения панели
    public int moveDirection=0;//движение платформы
    public boolean isMoving=true;//запускает работу потока
    public int ballSize = 20;//Размер Шарика
    public AbstractBall ball;
    public Habitat habitat;
    public boolean ballMoving,platformMoving;//запускает работу движение шарика

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
    public void moveLeft() {//движение платформы влево
        moveDirection=-1;
    }

    public void stay(){//остановка платформы
        moveDirection=0;
    }

    public void createBall(String type) {//создание нового шарика
        switch (type) {
            case "Ball":
                //создание нового шарика
                ball = new Ball(centerX - ballSize / 2, getY() - ballSize - 10, ballSize, ballSize, this);
                break;
        }
        ballMoving = false;//останавливает движение шарика
        new Thread(ball).start();//запуск потока шарика
    }

    public AbstractBall getBall()
    {
        return ball;
    }

    public synchronized void TogglePlatformMovement() {//Возобновляет потока платформы
        if (!platformMoving)
            notifyAll();
        platformMoving=!platformMoving;
    }


    public synchronized void isBallMoving() {//если мячик активен, то поток ждет
        try {
            if(!ballMoving)
                wait();//останавливает поток
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void ToggleBallMovement() {//Возобновляет потока шарика
        if (!ballMoving)
            notifyAll();
        ballMoving = !ballMoving;
    }
}
