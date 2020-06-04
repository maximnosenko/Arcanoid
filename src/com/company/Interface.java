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

    Interface(Singleton singleton){
        this.singleton=singleton;
       // this.habitat=habitat;
        Button();
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
        graphics.drawString("Simulation " + getTime() + "s",0, 30);

            if(singleton.life>=1) {
                graphics.setColor(Color.RED);
                graphics.fillOval(20, 0, 15, 15);
                graphics.drawOval(20, 0, 15, 15);
            }
            if(singleton.life>=2) {
                graphics.setColor(Color.RED);
                graphics.fillOval(40, 0, 15, 15);
                graphics.drawOval(40, 0, 15, 15);
            }
            if(singleton.life>=3) {
                graphics.setColor(Color.RED);
                graphics.fillOval(60, 0, 15, 15);
                graphics.drawOval(60, 0, 15, 15);
            }
            //xball+=20;
        repaint();

    }

    public void Button(){
        JButton restart=new JButton("restart");
        restart.setFocusable(false);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

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
