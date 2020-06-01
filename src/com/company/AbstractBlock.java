package com.company;


public abstract class AbstractBlock extends AbstractActor implements Destructable {
    int points;
    AbstractBlock(double x,double y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void Destroy() {//удаление

    }
}
