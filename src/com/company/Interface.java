package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Interface extends JPanel implements Serializable {
    private Singleton singleton=Singleton.getInstance();
    public int currentTime=0;//текущее время игры
    private static Timer mTimer;
    private Game game;
    JMenu menu=new JMenu("menu");

    Interface(Singleton singleton, Game game){
        this.singleton=singleton;
        this.game = game;
        Buttons();
        starterTime();
        panelMenu();
    }

    public void starterTime()//запускает отсчет времени
    {
        currentTime=0;
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
        graphics.drawString("Time " + currentTime + "s",100, 25);
        graphics.drawString("Points: " + singleton.Getpoints(), 200, 25);
        graphics.setColor(Color.RED);
        for (int i=0; i < singleton.life;i++) {
            graphics.fillOval(5 + i*20, 12, 15, 15);
            graphics.drawOval(5 + i*20, 12, 15, 15);
        }
    }

    public void Buttons(){//кнопка перезапуска
        JButton restart=new JButton("restart");
        JButton pause=new JButton("pause");
        JButton renew=new JButton("renew");
        restart.setFocusable(false);
        pause.setFocusable(false);
        renew.setFocusable(false);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.Restart();
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.pause();
                //restart(currentTime);
                mTimer.cancel();
            }
        });
        renew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //mTimer.schedule(new Updater(),0,1000);
                System.out.println(currentTime);
                restart(currentTime);
                game.renew();
            }
        });
        restart.setBounds(0,25,25,25);
        add(restart);
        add(pause);
        add(renew);
    }

    public void restart(int t){
        currentTime=t;
        starterTime();
    }

    private class Updater extends TimerTask {//счетчик блягодаря которому видем пройденое время
        private Interface anInterface;
        public Updater(Interface inter)
        {
            anInterface=inter;
        }
        @Override
        public void run() {
            currentTime+=1;
            anInterface.repaint();
        }
    }

    public void panelMenu(){

        JMenuItem save=new JMenuItem("save");
        JMenuItem load=new JMenuItem("load");
        menu.add(save);
        menu.add(load);
        save.setFocusable(false);
        load.setFocusable(false);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    objSave();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    objLoad();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        menu.setFocusable(false);
    }

    public void objSave() throws IOException {
        FileOutputStream file =new FileOutputStream("GAME.ser");
        ObjectOutputStream outputStream=new ObjectOutputStream(file);

        outputStream.writeInt(currentTime);
        outputStream.writeInt(singleton.life);
        outputStream.writeInt(singleton.Getpoints());
        System.out.println(singleton.Getpoints());
        for(AbstractActor actor:singleton.getVector()){
            if(actor instanceof AbstractPlatform)
                continue;
            outputStream.writeObject(actor);
            //System.out.println(actor);
        }
        outputStream.flush();
        outputStream.close();
    }

    public void objLoad() throws IOException,ClassNotFoundException {
        FileInputStream file=new FileInputStream("GAME.ser");
        ObjectInputStream object=new ObjectInputStream(file);
        currentTime=0;
        singleton.getVector().clear();
        singleton.points=0;

        currentTime= object.readInt();
        singleton.life=object.readInt();
        singleton.AddPoints(object.readInt());
        System.out.println(singleton.points);
        while (true)
        {
            try {
                AbstractActor actor= (AbstractActor) object.readObject();
                singleton.getVector().add(actor);
            } catch (IOException e) {
                System.out.println("exit");
                object.close();
                break;
            }
        }
        singleton.getVector().add(game.habitat.platform);
        game.habitat.repaint();
    }
}
