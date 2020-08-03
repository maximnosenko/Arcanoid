package com.company;

public interface Movable {
    int getSpeed();//скорость шарика
    double getXDir();
    double getYDir();
    void onCollision(AbstractActor actor,int dir);//проверка на столкновение
    void DestroyBall ();//удаление шарика
}