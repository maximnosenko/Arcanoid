package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Interface extends JPanel {
    Singleton singleton=Singleton.getInstance();
    int time=0,xball,currentTime=0;
    //Habitat habitat;
    private Timer mTimer;
    private Game game;

    Interface(Singleton singleton, Game game){
        this.singleton=singleton;
        this.game = game;
       // this.habitat=habitat;
        Button();
        starterTime();
    }

    public void starterTime()//переименовать
    {
        mTimer = new Timer();
        mTimer.schedule(new Updater(this),0,1000);
    }

    public void timeStopped()
    {
        mTimer.cancel();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setFont(new Font("Times Roman", Font.BOLD, 13));
        graphics.drawString("Time " + getTime() + "s",100, 25);
        graphics.drawString("Points: " + singleton.Getpoints(), 200, 25);
        graphics.setColor(Color.RED);
        for (int i=0; i < singleton.life;i++) {
            graphics.fillOval(5 + i*20, 12, 15, 15);
            graphics.drawOval(5 + i*20, 12, 15, 15);
        }
            //xball+=20;
        //repaint();

    }

    public void Button(){
        JButton restart=new JButton("restart");
        restart.setFocusable(false);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.Restart();
            }
        });
        restart.setBounds(0,25,25,25);
        add(restart);
    }


    private class Updater extends TimerTask {
        private Interface anInterface;
        int time=0;
        //private boolean FirstRun=true;
        public Updater(Interface inter)
        {
            anInterface=inter;
        }

        public Updater(ActionListener actionListener) {
        }
        @Override
        public void run() {
            time +=1;
            setCurrentTime(time);
            anInterface.repaint();
            //System.out.println(currentTime);
            //mHabitat.Update(currentTime);
        }
    }

    public void setCurrentTime(int time){
        currentTime=time;
        System.out.println(currentTime);
    }
    private int getTime()
    {
        return currentTime;
    }

}
