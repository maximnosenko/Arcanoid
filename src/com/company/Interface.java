package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JPanel implements Runnable {
    int xLife=10;
    boolean going=true;
    Singleton singleton=Singleton.getInstance();

    Interface(Singleton singleton){
        setBackground(Color.RED);
       // new Thread().start();
        this.singleton=singleton;
        //System.out.println(getWidth());
        Buttons();
    }

    public void paint(Graphics graphics)
    {
        //graphics.setColor(Color.BLUE);
        for(int i=0;i<singleton.life;i++) {
            graphics.setColor(Color.RED);
            graphics.fillOval(0,0,10,10);
            graphics.drawOval(0,0,10,10);
            xLife+=10;
            if(singleton.life==0)
            {
                going=false;
            }
        }
    }

    public void Buttons(){
        JButton start=new JButton();
        start.setFocusable(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        this.add(start);
        start.setBounds(50,70,10,10);
    }

    @Override
    public void run() {
        while (going)
        {
            try {
                Thread.sleep(10);
                repaint();
            } catch (InterruptedException e) {
                going=false;
            }
        }
    }
}
