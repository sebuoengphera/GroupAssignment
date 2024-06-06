package com.example.ponggame;

import java.awt.*;

public class Ball {

    private int x, y;
    private int diameter = 20;
    private int xVelocity = 3, yVelocity = 3;
    private boolean shouldMove = true; // Flag to indicate whether the ball should continue moving

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(Paddle p1, Paddle p2) {
        if (shouldMove) { // Only update position if the ball should move
            x += xVelocity;
            y += yVelocity;

            if (y <= 0 || y >= GamePanel.HEIGHT - diameter) {
                yVelocity = -yVelocity;
            }

            if (x <= p1.getX() + p1.getWidth() && y >= p1.getY() && y <= p1.getY() + p1.getHeight()) {
                xVelocity = -xVelocity;
            }

            if (x >= p2.getX() - p2.getWidth() && y >= p2.getY() && y <= p2.getY() + p2.getHeight()) {
                xVelocity = -xVelocity;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }
}
