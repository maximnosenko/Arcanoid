package com.company;

import javax.swing.*;
import java.awt.*;

public class Habitat extends JPanel implements Runnable{
    boolean going= true;
    Singleton singleton;
    private ConcreteFactory factory= new ConcreteFactory();
    Ball ball;
    double x=120,y=300;
    int sizeX=50,sizeY=25;

    public Habitat(Singleton singleton) {
        this.singleton = singleton;
        factory.createWall(0,0,10,700);
        factory.createWall(825,0,10,700);
        factory.createWall(0,0,840,10);
        factory.createWall(0,652,850,10);
        new Thread(this).start();
        ball=new Ball(420,500,20,20);
        new Thread(ball).start();
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 10; j++) {
                factory.createBlock(x, y, sizeX, sizeY);
                x=x+60;
            }
            x=120;
            y=y-35;
        }
        //платформу и стены сделать тут
    }

    public void paint(Graphics graphics) {
        super.paintComponent(graphics);
        for(AbstractActor actor:singleton.getVector()) {
            actor.painting(graphics);
            int direct;

            if(actor.down>ball.up&& ball.centerX > actor.left && ball.centerX < actor.right&&ball.down>actor.down)
            {
                direct=3;
                ball.onCollision(actor,direct);
                continue;
            }
            if(actor.right>ball.left&&actor.right<ball.right&&ball.centerY > actor.up && ball.centerY < actor.down){
                direct=1;
                ball.onCollision(actor,direct);
                continue;
            }
            if(actor.left<ball.right&&actor.right>ball.right&&ball.centerY > actor.up && ball.centerY < actor.down){
                direct=2;
                ball.onCollision(actor,direct);
                continue;
            }
            if(actor.up<ball.down&&actor.right>ball.centerX&&actor.left<ball.centerX&&ball.up<actor.up)
            {
                direct=4;
                ball.onCollision(actor,direct);
                continue;
                //System.out.println(Math.sqrt(Math.pow(ball.centerX-actor.right,2)+Math.pow(ball.centerY-actor.down,2)));
                //if(Math.sqrt(Math.pow(actor.right - ball.centerX, 2) + Math.pow(actor.down - ball.centerY, 2)) < ball.getSizeX()/2){
            }
            if(Math.sqrt(Math.pow(ball.centerX-actor.right,2)+Math.pow(ball.centerY-actor.down,2))<ball.getSizeX()/2){
               // System.out.println(Math.sqrt(Math.pow(ball.centerX-actor.right,2)+Math.pow(ball.centerY-actor.down,2)));
                System.out.println("нижний правый");
                direct=5;
                ball.onCollision(actor,6);
                continue;
            }
            //System.out.println(Math.sqrt(Math.pow(actor.right-ball.centerX,2)+Math.pow(actor.up-ball.centerY,2)));
            if(Math.sqrt(Math.pow(actor.right-ball.centerX,2)+Math.pow(actor.up-ball.centerY,2))<ball.getSizeX()/2)
            {
                System.out.println("верхний правый");
                direct=6;
                //ball.onCollision(actor,direct);
                continue;
            }
            if(Math.sqrt(Math.pow(ball.centerX-actor.left,2)+Math.pow(ball.centerY-actor.up,2))<ball.getSizeX()/2)
            {
                System.out.println("верхний левый");
                direct=7;
                ball.onCollision(actor,direct);
                continue;
            }
            if(Math.sqrt(Math.pow(actor.left-ball.centerX,2)+Math.pow(actor.down-ball.centerY,2))<ball.getSizeX()/2){
                direct=8;
                ball.onCollision(actor,direct);
                System.out.println("нижний левый ");
            }
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
    }
}