package com.company;

import java.awt.*;

public class Block extends AbstractBlock {
    Block(double x, double y,int sizeX,int sizeY) {
        super(x, y,sizeX,sizeY);
        setCoordinates(x,y);
        points = 100;
    }

    @Override
    public void painting(Graphics g) {//рисование объекта
        g.setColor(Color.BLUE);//задает цвет объекта
        g.fillRect((int)x,(int)y,sizeX,sizeY);//заполнения цветом объекта
        g.drawRect((int)x,(int)y,sizeX,sizeY);//рисует объект по указанным координатам
    }
}
