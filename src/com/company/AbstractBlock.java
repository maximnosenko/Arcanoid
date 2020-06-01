package com.company;


public abstract class AbstractBlock extends AbstractActor implements Destructable {
    int points;

    AbstractBlock(double x, double y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    //удаление
    @Override
    public void Destroy(){

    }
}
