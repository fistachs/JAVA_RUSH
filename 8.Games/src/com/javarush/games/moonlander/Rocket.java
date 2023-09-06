package com.javarush.games.moonlander;

import static com.javarush.games.moonlander.MoonLanderGame.WIDTH;

public class Rocket extends GameObject {
    private double speedY = 0;
    private double boost = 0.05;
    private double speedX = 0;

    public Rocket(double x, double y) {
        super(x, y, ShapeMatrix.ROCKET);
    }

    public void move(boolean isUpPressed, boolean isLeftPressed, boolean isRightPressed) {
        if (isUpPressed == true) {
            speedY -= boost;

        } else if (isUpPressed == false) {
            speedY += boost;
        }
        y += speedY;

        if (isLeftPressed) {
            speedX -= boost;
            x += speedX;
        } else if (isRightPressed) {
            speedX += boost;
            x += speedX;
        }
        checkBorders();

    }

    private void checkBorders() {
        if (x < 0) {
            x = 0;
            speedX = 0;
        }
        if (x + width > WIDTH) {
            x = WIDTH - width;
            speedX = 0;
        }
        if (y < 0) {
            y = 0;
            speedY = 0;
        }
    }
}
