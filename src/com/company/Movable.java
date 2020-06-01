package com.company;

public interface Movable {
    int getSpeed();//скорость шарика
    void setSpeed(int speed);
    void setXDir(double dirX);
    void setYDir(double dirY);
    double getXDir();
    double getYDir();
    int ToggleMovement();//для остановления шарика
    void onCollision(AbstractActor actor,int dir);//проверка на столкновение
}