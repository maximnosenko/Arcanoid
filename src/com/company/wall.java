package com.company;

import java.awt.*;

public class Wall extends AbstractActor {

    Wall(double x,double y, int sizeX,int sizeY){//конструктор, который задает изначальные помя
        this.x=x;
        this.y=y;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        setCoordinates(this.x,this.y);
    }

    @Override
    public void painting(Graphics g) {//Рисует стены
        g.setColor(Color.BLACK);//закрашивает стены черным цветом
        g.fillRect((int)x,(int)y,sizeX,sizeY);
        g.drawRect((int)x,(int)y,sizeX,sizeY);
    }
}
