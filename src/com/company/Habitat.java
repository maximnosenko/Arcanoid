package com.company;

import javax.swing.*;
import java.awt.*;

public class Habitat extends JPanel implements Runnable{
    //int x1=10,y1=0,x2=10,y2=700;//0,0,0,700
    //int weight,height=0;
    boolean going= true;
    Singleton singleton = Singleton.getInstance();
    private ConcreteFactory factory= new ConcreteFactory();
    Ball ball;

    public Habitat(Singleton singleton) {
        this.singleton = singleton;
        factory.createWall(0,0,10,700);
        factory.createWall(825,0,10,700);
        factory.createWall(0,0,840,10);
        factory.createWall(0,652,850,10);
        new Thread(this).start();
        //factory.createBoll();
        ball=new Ball(420,500,20,20);//420,500,20,20
        new Thread(ball).start();
        //платформу и стены сделать тут
    }

    public void paint(Graphics graphics) {
        super.paintComponent(graphics);
        for(AbstractActor actor:singleton.getVector()) {
            actor.painting(graphics);
            int direct=0;
            //if(actor.right>ball.left&&actor.left<ball.right&&actor.up<ball.down&&actor.down>ball.up){
            //actor.right>ball.left && actor.getX()<ball.getX()&&actor.down>ball.up&&ball.down<actor.up&&actor.left<ball.left
              //  ball.onCollision(actor);
                //System.out.println("si si si");
                //going=false;
            //singleton.getVector().get(i).painting(graphics);
            //}
            if(actor.down>ball.up&& ball.getX() > actor.left && ball.getX() < actor.right&&ball.down>actor.down)
            {
                direct=3;
                ball.onCollision(actor,direct);
                //System.out.println("actor"+actor.getY());
                //System.out.println("ball"+ball.getY());
                System.out.println("Привет Андрей тун тудудн тун");
                continue;
            }
            if(actor.right>ball.left&&actor.right<ball.right&&ball.getY() > actor.up && ball.getY() < actor.down){
                direct=1;
                ball.onCollision(actor,direct);
                continue;
            }
            if(actor.left<ball.right&&actor.right>ball.right&&ball.getY() > actor.up && ball.getY() < actor.down){
                direct=2;
                ball.onCollision(actor,direct);
                continue;
            }
            if(actor.up<ball.down&&actor.right>ball.getX()&&actor.left<ball.getY()&&ball.up<actor.up)
            {
                direct=4;
                ball.onCollision(actor,direct);
                //continue;
            }
            //if(actor.up<ball.right&&){

            //}
        }
        ball.painting(graphics);
    }

    @Override
    public void run() {
        while (going) {
            try {
                Thread.sleep(10);
                repaint();
            } catch (InterruptedException e) {
                going=false;
            }
        }
        //проверка есть ли колизия каждые 100 милисекунд проверять
        //проверить через цикл пересечения с шариком проверить не пересекаютсяли
        //if(xsize(одного obj) - xsize(другого)) onColision
    }
}