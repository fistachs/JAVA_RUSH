package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;
import com.javarush.engine.cell.Game;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape;
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x > WIDTH - 1 || x < 0 || y < 0 || y > HEIGHT - 1) {
            return;
        }
        super.setCellColor(x, y, color);
    }

    @Override
    public void onKeyPress(Key key) {
        if (Key.RIGHT == key) {
            isRightPressed = true;
            isLeftPressed = false;
        } else if (Key.LEFT == key) {
            isLeftPressed = true;
            isRightPressed = false;
        } else if (Key.UP == key) {
            isUpPressed = true;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (Key.RIGHT == key) {
            isRightPressed = false;
        } else if (Key.LEFT == key) {
            isLeftPressed = false;
        } else if (Key.UP == key) {
            isUpPressed = false;
        }
    }

    private void createGame() {
        setTurnTimer(50);
        createGameObjects();
        drawScene();
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
    }

    private void drawScene() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellColor(x, y, Color.BLACK);
            }
        }

        rocket.draw(this);
        landscape.draw(this);
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2.0, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);

    }

    private void check() {

    }

    private void win() {

    }
    private void gameOver() {

    }
}
