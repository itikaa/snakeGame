package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class GamePanel extends JPanel implements ActionListener {
    static final int width=600;
    static final int height=600;
    static final int unit_size=25;
    static final int game_unit=(width*height)/unit_size;
    static final int delay=75;
    final int[] x=new int[game_unit];
    final int[] y=new int[game_unit];
    int body=4;
    int fruiteaten;
    int fruitX;
    int fruitY;
    char direction='R';
    boolean running=false;
    Random random;
    Timer timer;
    GamePanel(){
  random=new Random();
  this.setPreferredSize(new Dimension(width,height));
  this.setBackground(Color.BLACK);
  this.setFocusable(true);
  this.addKeyListener(new MyKeyAdapter());
  StartGame();
    }
    public void StartGame(){
        newfruit();
        running=true;
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        for(int i=0;i<height/unit_size;i++){
            g.drawLine(i*unit_size,0,i*unit_size,height);
            g.drawLine(0,i*unit_size,width,i*unit_size);
        }
        g.setColor(Color.red);
        g.fillOval(fruitX,fruitY,unit_size,unit_size);
    }
    public void move(){}
    public void newfruit(){
 fruitX=random.nextInt((int)(width/unit_size))*unit_size;
 fruitY=random.nextInt((int)(height/unit_size))*unit_size;
    }
    public void checkfruit(){}
    public void checkcollision(){}
    public void gameover(){}
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
        }
    }
}
