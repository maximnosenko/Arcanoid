package com.company;


public abstract class AbstractBlock extends AbstractActor {
    int points=0;

    AbstractBlock(double x, double y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
