package org.example;
//import org.example.GamePanel;
import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
    this.add(new GamePanel());
    this.setTitle("Snake");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
    this.setFocusable(true);
    this.requestFocus();
    this.setLocationRelativeTo(null);
    }
}
