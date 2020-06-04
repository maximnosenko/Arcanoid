package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Реализация интерфейсов и всякого прикольного
public class Game {
    JFrame frame;
    private Singleton singleton=Singleton.getInstance();
    Interface anInterface=new Interface(singleton, this);
    private Habitat habitat=new Habitat(singleton,anInterface);

    boolean going=false;

    public Game(){
        //new Thread(habitat.ball).start();
        //anInterface.setLayout(null);
        frame=new JFrame("Arkanoid");
        //habitat.setBackground(Color.BLUE);
        frame.add(habitat, BorderLayout.CENTER);
        //anInterface.setBackground(Color.RED);
        frame.add(anInterface,BorderLayout.NORTH);
        System.out.println(singleton.getVector());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    habitat.platform.stay();
                }
            }
        });
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton()==MouseEvent.BUTTON1)
                {
                    if(!habitat.platform.ballMoving) {

                        habitat.go=true;
                        //anInterface.starterTime();
                        //System.out.println(habitat.go);
                        habitat.ball.setDir(e.getX()-5, e.getY()-30);
                        habitat.platform.ToggleBallMovement();//запускает потоки
                    }
                }
            }
        });
    }

    public void Restart()
    {
        anInterface.timeStopped();
        singleton.getVector().removeAllElements();
        singleton.life = 3;
        singleton.SetPoints(0);
        habitat.setupHabitat();
        habitat.ball.DestroyBall();
        habitat.platform.ToggleBallMovement();
        anInterface.starterTime();
    }
}