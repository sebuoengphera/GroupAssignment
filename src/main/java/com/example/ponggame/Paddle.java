package com.example.ponggame; // Define the package for the Paddle class

import java.awt.*; // Import necessary classes from the AWT package

public class Paddle { // Declare the Paddle class

    // Declare instance variables to store the position, size, and movement of the paddle
    private int x, y; // Variables to store the x and y coordinates of the paddle
    private int width = 20, height = 80; // Variables to store the width and height of the paddle
    private int yVelocity = 0; // Variable to store the velocity of the paddle in the y direction
    private int speed = 10; // Variable to store the speed of the paddle
    private boolean up, down; // Boolean variables to indicate if the paddle should move up or down
    private Color color; // Variable to store the color of the paddle

    // Constructor to initialize the paddle with a specified position and color
    public Paddle(int x, int y, Color color) { // Constructor with parameters for the initial position and color of the paddle
        this.x = x; // Set the initial x-coordinate of the paddle
        this.y = y; // Set the initial y-coordinate of the paddle
        this.color = color; // Set the color of the paddle
    }

    // Method to update the position of the paddle based on user input
    public void update() { // Method to update the position of the paddle
        // Move the paddle up if the 'up' flag is true and the paddle is not at the top boundary
        if (up && y > 0) { // Condition to check if the 'up' flag is true and the paddle is not at the top boundary
            y -= speed; // Move the paddle up by the specified speed
        }
        // Move the paddle down if the 'down' flag is true and the paddle is not at the bottom boundary
        if (down && y < GamePanel.HEIGHT - height) { // Condition to check if the 'down' flag is true and the paddle is not at the bottom boundary
            y += speed; // Move the paddle down by the specified speed
        }
    }

    // Method to draw the paddle on the graphics context
    public void draw(Graphics g) { // Method to draw the paddle
        g.setColor(color); // Set the color of the graphics context to the color of the paddle
        g.fillRect(x, y, width, height); // Draw a filled rectangle (paddle) at the specified position and size
    }

    // Setter method to set the 'up' flag for the paddle movement
    public void setUp(boolean up) { // Method to set the 'up' flag for the paddle movement
        this.up = up; // Set the 'up' flag to the specified value
    }

    // Setter method to set the 'down' flag for the paddle movement
    public void setDown(boolean down) { // Method to set the 'down' flag for the paddle movement
        this.down = down; // Set the 'down' flag to the specified value
    }

    // Getter method to retrieve the x-coordinate of the paddle
    public int getX() { // Method to retrieve the x-coordinate of the paddle
        return x; // Return the x-coordinate of the paddle
    }

    // Getter method to retrieve the y-coordinate of the paddle
    public int getY() { // Method to retrieve the y-coordinate of the paddle
        return y; // Return the y-coordinate of the paddle
    }

    // Getter method to retrieve the width of the paddle
    public int getWidth() { // Method to retrieve the width of the paddle
        return width; // Return the width of the paddle
    }

    // Getter method to retrieve the height of the paddle
    public int getHeight() { // Method to retrieve the height of the paddle
        return height; // Return the height of the paddle
    }
}
