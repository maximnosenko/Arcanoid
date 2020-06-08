package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

//Реализация интерфейсов и всякого прикольного
public class Game implements Serializable {
    JFrame frame;
    private Singleton singleton=Singleton.getInstance();
    Interface anInterface=new Interface(singleton, this);
    public Habitat habitat=new Habitat(singleton,anInterface,this);
    private Dialog dialog;
    JMenuBar menuBar=new JMenuBar();
    //boolean going=false;

    public Game(){
        frame=new JFrame("Arkanoid");
        frame.add(habitat, BorderLayout.CENTER);
        frame.add(anInterface,BorderLayout.NORTH);
        System.out.println(singleton.getVector());
        frame.setJMenuBar(menuBar);
        menuBar.add(anInterface.menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

            }
        });
        frame.setSize(850,750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {//при нажании -> панель движется в право также работает и слевой стороной
                super.keyTyped(e);
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                {
                    habitat.platform.moveRight();
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    habitat.platform.moveLeft();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {//если клавиши отжаты, то панель останавливается
                super.keyReleased(e);
                if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    habitat.platform.stay();
                }
            }
        });
        //слушатель которые отдает координаты нажатого курсора и запускает шарик
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton()==MouseEvent.BUTTON1)
                {
                    if(!habitat.platform.ballMoving) {
                        //habitat.go=true;
                        habitat.ball.setDir(e.getX()-5, e.getY()-30);
                        habitat.platform.ToggleBallMovement();//запускает потоки
                    }
                }
            }
        });
    }

    public synchronized void pause(){
        habitat.platform.ballMoving=false;
        //habitat.platform.isMoving=false;
        habitat.platform.platformMoving=true;
        //habitat.going=false;
        //habitat.repaint();

        //habitat.repaint();
        //habitat.platform.isBallMoving();
        //habitat.platform.isMoving=false;
    }

    public void renew(){
        habitat.platform.ToggleBallMovement();
        habitat.platform.TogglePlatformMovement();
        //habitat.platform.ballMoving=true;
        //habitat.platform.isMoving=true;
        habitat.repaint();
    }

    public void Restart()
    {
        //habitat.going=true;
        anInterface.timeStopped();
        singleton.refreshVector();
        singleton.life = 3;
        singleton.SetPoints(0);
        habitat.setupHabitat();
        habitat.ball.DestroyBall(); //ВоСТАНОВИТЬ
        if(habitat.platform.ballMoving)
        habitat.platform.ToggleBallMovement();
        anInterface.starterTime();
        habitat.repaint();
    }

    public void GameOver()
    {
        habitat.going = false;
        habitat.repaint();
        anInterface.timeStopped();
        dialog=new Dialog(this,anInterface,singleton);
    }
}