package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Interface extends JPanel {
    private Singleton singleton=Singleton.getInstance();
    public int currentTime=0;//текущее время игры
    private Timer mTimer;
    private Game game;

    Interface(Singleton singleton, Game game){
        this.singleton=singleton;
        this.game = game;
        Button();
        starterTime();
    }

    public void starterTime()//запускает отсчет времени
    {
        mTimer = new Timer();
        mTimer.schedule(new Updater(this),0,1000);
    }

    public void timeStopped()//останавлиет время
    {
        mTimer.cancel();
    }

    public void paintComponent(Graphics graphics){// отрисовывает всю вторую панель
        super.paintComponent(graphics);
        graphics.setFont(new Font("Times Roman", Font.BOLD, 13));
        graphics.drawString("Time " + getTime() + "s",100, 25);
        graphics.drawString("Points: " + singleton.Getpoints(), 200, 25);
        graphics.setColor(Color.RED);
        for (int i=0; i < singleton.life;i++) {
            graphics.fillOval(5 + i*20, 12, 15, 15);
            graphics.drawOval(5 + i*20, 12, 15, 15);
        }
    }

    public void Button(){//кнопка перезапуска
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


    private class Updater extends TimerTask {//счетчик блягодаря которому видем пройденое время
        private Interface anInterface;
        int time=0;
        public Updater(Interface inter)
        {
            anInterface=inter;
        }
        @Override
        public void run() {
            time +=1;
            setCurrentTime(time);
            anInterface.repaint();
        }
    }

    public void setCurrentTime(int time){
        currentTime=time;
    }
    private int getTime()
    {
        return currentTime;
    }

}
