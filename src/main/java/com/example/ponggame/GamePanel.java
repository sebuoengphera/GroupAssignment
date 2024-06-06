package com.example.ponggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    // Constants for the width and height of the game panel
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    // Thread for running the game loop
    private Thread gameThread;
    // Flag to indicate if the game is running
    private boolean running;
    // Ball and paddles for the game
    private Ball ball;
    private Paddle player1, player2;
    // Scores for player 1 and player 2
    private int player1Score = 0;
    private int player2Score = 0;
    // Flag to indicate if the game is over
    private boolean gameOver = false;
    // Start over button
    private JButton startOverButton;

    // Constructor to initialize the game panel
    public GamePanel() {
        // Set the preferred size of the game panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // Set the background color to white
        setBackground(Color.BLACK);
        // Make the game panel focusable to receive key events
        setFocusable(true);
        // Add a key listener to the game panel
        addKeyListener(this);

        // Initialize the ball at the center of the panel
        ball = new Ball(WIDTH / 2, HEIGHT / 2);
        // Initialize the paddles for player 1 and player 2
        player1 = new Paddle(10, HEIGHT / 2 - 40, Color.RED);
        player2 = new Paddle(WIDTH - 30, HEIGHT / 2 - 40, Color.BLUE);

        // Initialize the start over button
        startOverButton = new JButton("Start Over");
        startOverButton.addActionListener((ActionEvent e) -> startOver());
    }

    // Method to start the game
    public void startGame() {
        // Set the running flag to true
        running = true;
        // Create and start the game thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Method to start over the game
    private void startOver() {
        player1Score = 0;
        player2Score = 0;
        gameOver = false;
        ball.setX(WIDTH / 2);
        ball.setY(HEIGHT / 2);
        ball.setXVelocity(-ball.getXVelocity());
        // Remove the start over button
        remove(startOverButton);
        repaint();
    }

    @Override
    public void run() {
        // Main game loop
        while (running) {
            // Update game state
            update();
            // Repaint the game panel
            repaint();

            try {
                // Sleep for 10 milliseconds to control the frame rate
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to update the game state
    private void update() {
        // If the game is not over, update the ball and paddles
        if (!gameOver) {
            ball.update(player1, player2);
            player1.update();
            player2.update();

            // Check if the ball has gone out of bounds and update scores
            if (ball.getX() < 0) {
                player2Score++;
                checkGameOver();
                resetBall();
            } else if (ball.getX() > WIDTH) {
                player1Score++;
                checkGameOver();
                resetBall();
            }
        }
    }

    // Method to check if the game is over
    private void checkGameOver() {
        if (player1Score >= 10 || player2Score >= 10) {
            gameOver = true;
        }
    }

    // Method to reset the ball to the center of the panel
    private void resetBall() {
        ball.setX(WIDTH / 2);
        ball.setY(HEIGHT / 2);
        ball.setXVelocity(-ball.getXVelocity());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the ball and paddles
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);

        // Draw the scores
        g.setColor(Color.WHITE); // Set the score color to black
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Player1:Player2", WIDTH / 2 - 80, 30);
        g.drawString(player1Score + ":" + player2Score, WIDTH / 2 - 20, 70);

        // Draw the winner if the game is over
        if (gameOver) {
            String winner = (player1Score >= 10) ? "Player 1" : "Player 2";
            g.drawString("Winner is " + winner, WIDTH / 2 - 80, HEIGHT / 2);
            // Draw the start over button
            startOverButton.setBounds(WIDTH / 2 - 60, HEIGHT / 2 + 30, 120, 30);
            add(startOverButton);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press events for paddle movement if the game is not over
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                player1.setUp(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player1.setDown(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                player2.setUp(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player2.setDown(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key release events for paddle movement
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            player1.setUp(false);
            player1.setDown(false);
        }
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.setUp(false);
            player2.setDown(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No implementation needed for keyTyped
    }
}

