package com.example.ponggame;

import javax.swing.JFrame;

public class PongGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        GamePanel gamePanel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.startGame();
    }
}
