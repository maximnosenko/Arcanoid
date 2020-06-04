package com.company;

import java.awt.*;

public class Block extends AbstractBlock {
    Block(double x, double y,int sizeX,int sizeY) {
        super(x, y,sizeX,sizeY);
        setCoordinates(x,y);
        points = 100;
    }

    @Override
    public void painting(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,sizeX,sizeY);
        g.drawRect((int)x,(int)y,sizeX,sizeY);
    }
}
