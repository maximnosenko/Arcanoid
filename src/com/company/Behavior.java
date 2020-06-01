package com.company;

import java.awt.*;

public interface Behavior {
    void painting(Graphics g);
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    void setCoordinates(double x,double y);
}
