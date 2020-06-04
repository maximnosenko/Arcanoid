package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Habitat extends JPanel implements Runnable{
    boolean going= true,go=true;
    Singleton singleton;
    private ConcreteFactory factory= new ConcreteFactory();
    AbstractBall ball;
    AbstractPlatform platform;
    //double x=120,y=300;
    int sizeX=50,sizeY=25;
    Interface anInterface;

    public Habitat(Singleton singleton,Interface anInterface) {
        this.singleton = singleton;
        new Thread(this).start();
        platform = new Platform(380, 600, 100, 25);//370,600,100,25
        ball = platform.getBall();
        new Thread(platform).start();
        singleton.getVector().add(platform);
        this.anInterface=anInterface;
        setupHabitat();
    }

    public void paint(Graphics graphics) {
        super.paintComponent(graphics);
        for(int i=0;i<singleton.getVector().size();i++){
            singleton.getVector().get(i).painting(graphics);//из-за него моргает
            if(check(singleton.getVector().get(i))){
                removeBlock(singleton.getVector().get(i));
            }
        }
        //ball.paintingCount(graphics,10);
        //ball.paintingCount(graphics,30);
        //ball.paintingCount(graphics,50);
        platform.painting(graphics);
        if (going)
            ball.painting(graphics);
    }

    public boolean check(AbstractActor actor){//проверка с каокой стороной шарик столкнулся
        int direct;
        if(actor.down>ball.up&& ball.centerX > actor.left && ball.centerX < actor.right&&ball.down>actor.down)
        {
            direct=3;
            ball.onCollision(actor,direct);
            return true;
        }
        if(actor.right>ball.left&&actor.right<ball.right&&ball.centerY > actor.up && ball.centerY < actor.down){
            direct=1;
            ball.onCollision(actor,direct);
            return true;
        }
        if(actor.left<ball.right&&actor.right>ball.right&&ball.centerY > actor.up && ball.centerY < actor.down){
            direct=2;
            ball.onCollision(actor,direct);
            return true;
        }
        if(actor.up<ball.down&&actor.right>ball.centerX&&actor.left<ball.centerX&&ball.up<actor.up)
        {

            direct=4;
            ball.onCollision(actor,direct);
            if(actor instanceof Wall) {
                //go=false;
                //anInterface.timeStopped();
                singleton.life-=1;
                anInterface.repaint();
                if (singleton.life == 0) {
                    GameOver();
                }
                else {
                    ball.DestroyBall();
                    platform.ToggleBallMovement();//
                    //rewriting();
                }
            }
            return true;
        }
        if(Math.sqrt(Math.pow(ball.centerX-actor.right,2)+Math.pow(ball.centerY-actor.down,2))<ball.getSizeX()/2){
            //System.out.println("нижний правый");
            direct=5;
            ball.onCollision(actor,direct);
            return true;
        }
        if(Math.sqrt(Math.pow(actor.right-ball.centerX,2)+Math.pow(actor.up-ball.centerY,2))<ball.getSizeX()/2)
        {
            //System.out.println("верхний правый");
            direct=6;
            ball.onCollision(actor,direct);
            return true;
        }
        if(Math.sqrt(Math.pow(ball.centerX-actor.left,2)+Math.pow(ball.centerY-actor.up,2))<ball.getSizeX()/2)
        {
            //System.out.println("верхний левый");
            direct=7;
            ball.onCollision(actor,direct);
            return true;
        }
        if(Math.sqrt(Math.pow(actor.left-ball.centerX,2)+Math.pow(actor.down-ball.centerY,2))<ball.getSizeX()/2){
            direct=8;
            ball.onCollision(actor,direct);
            return true;
            //System.out.println("нижний левый ");
        }
        return false;
    }

    public void removeBlock(AbstractActor actor){
        if(actor instanceof AbstractBlock)
        {
            singleton.getVector().remove(actor);
            singleton.AddPoints(((AbstractBlock) actor).points);
        }
    }

    public void setupHabitat()
    {
        factory.createWall(0,0,10,700);
        factory.createWall(825,0,10,700);
        factory.createWall(0,0,840,10);
        factory.createWall(0,652,850,10);
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 8; j++) {
                factory.createBlock(120 + i*60, 300 - j*35, sizeX, sizeY);
            }
        }
    }

    @Override
    public void run() {
        while (going) {
            //System.out.println(time);
            try {
                Thread.sleep(10);
                repaint();
            } catch (InterruptedException e) {
                going=false;
            }
        }
    }

    public void GameOver()
    {
        going = false;
        repaint();
        anInterface.timeStopped();
        //Вызвать диалог, в котором написано game over, есть кнопка перезапуск, есть кнопка выход и есть твои набранные очки
    }
}