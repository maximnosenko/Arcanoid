package com.company;

import java.awt.*;

public class Wall extends AbstractActor {

    Wall(double x,double y, int sizeX,int sizeY){
        this.x=x;
        this.y=y;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        setCoordinates(this.x,this.y);
    }

    @Override
    public void painting(Graphics g) {//Рисование стены
        g.setColor(Color.BLACK);
        g.fillRect((int)x,(int)y,sizeX,sizeY);
        g.drawRect((int)x,(int)y,sizeX,sizeY);
    }
}
