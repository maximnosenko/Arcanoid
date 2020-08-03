package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JDialog {
        public Dialog(Game game,Interface anInterface,Singleton singleton) {//Диологовое окно по завершении игры
            JButton button1 = new JButton("restart");//кнопка отвечающая за перезагрузку
            button1.setFocusable(false);
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    game.Restart();
                    dispose();
                    requestFocus();
                }
            });
            setTitle("Window");
            JButton button2 = new JButton("exit");//кнопка отвечающая за выходи из программы
            button2.setFocusable(false);
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    requestFocus();
                    System.exit(0);
                }
            });
            JPanel contents = new JPanel();
            contents.add(button1);
            contents.add(button2);
            setContentPane(contents);
            JTextArea textArea=new JTextArea(5,25);
            JTextArea textArea1=new JTextArea(2,15);
            textArea1.setFont(new Font("Times Roman", Font.BOLD, 20));
            textArea1.setText("GAME OVER \n");
            textArea.setFont(new Font("Times Roman", Font.BOLD, 14));
            textArea.setText("Time Game "+anInterface.currentTime +"\n"+" Points "+singleton.points+"\n"+" life left "+singleton.life);
            textArea.setEditable(false);
            contents.add(textArea1);
            contents.add(textArea);
            setSize(350, 200);
            setLocationRelativeTo(null);
            setVisible(true);
            requestFocus();
        }
}
