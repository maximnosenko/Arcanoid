package com.company;

import java.awt.*;

public interface Behavior {
    void painting(Graphics g);//рисование объектов
    double getX();
    double getY();
    void setX(double x);//координаты объекта
    void setY(double y);//координаты объекта
    void setCoordinates(double x,double y);//устанавливает координаты объекта и определяет центр
}
