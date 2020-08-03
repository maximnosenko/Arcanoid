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
        frame.add(habitat, BorderLayout.CENTER);//добавляет основную панель
        frame.add(anInterface,BorderLayout.NORTH);//добавляет панель интерфейса
        System.out.println(singleton.getVector());
        frame.setJMenuBar(menuBar);
        menuBar.add(anInterface.menu);//Добавляет меню
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

        //Баг - Исправлен

        //Листенер должен идти к habitat, а не к frame
        //Иначе положение мыши будет отсчитываться в координатах фрейма, а положение шарика - в координатах habitat
        //frame.addMouseListener(new MouseAdapter() {
        habitat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton()==MouseEvent.BUTTON1)
                {
                    if(!habitat.platform.ballMoving&&!habitat.platform.platformMoving) {
                        //habitat.go=true;
                        habitat.ball.setDir(e.getX(), e.getY());
                        System.out.println(e.getX());
                        System.out.println(e.getY());
                        habitat.platform.ToggleBallMovement();//запускает потоки
                    }
                }
            }
        });
    }

    public synchronized void pause(){//приостановление потоков
        habitat.platform.ballMoving=false;
        habitat.platform.platformMoving=true;
    }

    public void renew(){//возобновление потоков
        habitat.platform.ToggleBallMovement();
        habitat.platform.TogglePlatformMovement();
        habitat.repaint();
    }

    public void Restart()//перезагрузка игры
    {
        anInterface.timeStopped();
        anInterface.starterTime();
        singleton.refreshVector();
        singleton.life = 3;
        singleton.SetPoints(0);
        habitat.setupHabitat();
        habitat.ball.DestroyBall();
        if(habitat.platform.ballMoving)
        habitat.platform.ToggleBallMovement();
        habitat.repaint();
    }

    public void GameOver()//Если игра пройдена или игра проиграна
    {
        habitat.repaint();
        anInterface.timeStopped();
        dialog=new Dialog(this,anInterface,singleton);
    }
}