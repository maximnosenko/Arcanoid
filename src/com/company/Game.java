package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Реализация интерфейсов и всякого прикольного
public class Game {
    JFrame frame;
    private Singleton singleton=Singleton.getInstance();
    Interface anInterface=new Interface(singleton, this);
    private Habitat habitat=new Habitat(singleton,anInterface,this);
    private Dialog dialog;
    boolean going=false;

    public Game(){
        frame=new JFrame("Arkanoid");
        frame.add(habitat, BorderLayout.CENTER);
        frame.add(anInterface,BorderLayout.NORTH);
        System.out.println(singleton.getVector());
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
                        habitat.go=true;
                        habitat.ball.setDir(e.getX()-5, e.getY()-30);
                        habitat.platform.ToggleBallMovement();//запускает потоки
                    }
                }
            }
        });
    }

    public void Restart()
    {
        new Thread(habitat).start();
        //new Thread(habitat.ball).start();
        //new Thread(habitat.platform).start();
        habitat.going=true;
        anInterface.timeStopped();
        singleton.refreshVector();
        singleton.life = 3;
        singleton.SetPoints(0);
        habitat.setupHabitat();
        habitat.ball.DestroyBall();
        if (habitat.platform.ballMoving)
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