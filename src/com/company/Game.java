package com.company;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Реализация интерфейсов и всякого прикольного
public class Game {
    JFrame frame;
    private Singleton singleton=Singleton.getInstance();
    private Habitat habitat=new Habitat(singleton);


    public Game(){
        new Thread(habitat.ball).start();
        frame=new JFrame("Arkanoid");
        frame.add(habitat);
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
                        habitat.ball.setDir(e.getX(), e.getY());
                        habitat.platform.ToggleBallMovement();
                    }
                }
            }
        });
    }
}