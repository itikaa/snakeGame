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
    int body=6;
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
 // this.setFocusable(true);
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
        if(running) {
            for (int i = 0; i < height / unit_size; i++) {
                g.drawLine(i * unit_size, 0, i * unit_size, height);
                g.drawLine(0, i * unit_size, width, i * unit_size);
            }
            //draw the fruit
            g.setColor(Color.red);
            g.fillOval(fruitX, fruitY, unit_size, unit_size);

            //draw the snake
            for (int i = 0; i < body; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], unit_size, unit_size);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], unit_size, unit_size);
                }
            }

            g.setColor(Color.red);
            g.setFont(new Font("Ink Free",Font.BOLD,35));
            FontMetrics metrics=getFontMetrics(g.getFont());
            g.drawString("Score:"+fruiteaten,(width-metrics.stringWidth("Score:"+fruiteaten))/2,g.getFont().getSize());
        }
        else{
            gameover(g);
        }
    }
    public void move(){
        for(int i=body;i>0;i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
            switch(direction){
                case 'U':
                    y[0]=y[0]-unit_size;
                    break;
                case'D':
                    y[0]=y[0]+unit_size;
                    break;
                case 'L':
                    x[0]=x[0]-unit_size;
                    break;
                case 'R':
                    x[0]=x[0]+unit_size;
                    break;
            }
    }
    public void newfruit(){
 fruitX=random.nextInt((int)(width/unit_size))*unit_size;
 fruitY=random.nextInt((int)(height/unit_size))*unit_size;
    }
    public void checkfruit(){
        if(x[0]==fruitX && y[0]==fruitY){
            fruiteaten++;
            body++;
            newfruit();
        }
    }
    public void checkcollision(){
        //checks is head collides with body
        for(int i=body;i>0;i--){
            if(x[i]==x[0]&&y[i]==y[0]){
                running=false;
            }
        }
        //checks if head touches left border
        if(x[0]<0){
            running=false;
        }
        //checks if head touches right border
        if(x[0]>width-1) {
            running = false;
        }
        //checks if head touches bottom border
        if(y[0]>height-1) {
            running = false;
        }
        //checks if head touches top border
        if(y[0]<0) {
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }

    public void gameover(Graphics g){
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,35));
        FontMetrics metrics1=getFontMetrics(g.getFont());
        g.drawString("Score:"+fruiteaten,(width-metrics1.stringWidth("Score:"+fruiteaten))/2,g.getFont().getSize());
        //GameOver text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2=getFontMetrics(g.getFont());
        g.drawString("Game Over",(width-metrics2.stringWidth("Game Over"))/2,height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
 if(running){
     move();
     checkfruit();
     checkcollision();
 }
 repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction!='R'){
                        direction='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction!='L'){
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction!='U'){
                        direction='D';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction!='D'){
                        direction='U';
                    }
                    break;
            }
        }
    }
}
