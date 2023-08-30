package com.javarush.games.moonlander;
import com.javarush.engine.cell.*;
import com.javarush.engine.cell.Game;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape;
    @Override
    public void initialize(){
        setScreenSize(WIDTH,HEIGHT);
        showGrid(false);
        createGame();
    }
    @Override
    public void onTurn(int Step){
        rocket.move();
        drawScene();
    }
    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x > WIDTH - 1 || x < 0 || y < 0 || y > HEIGHT - 1) {
            return;
        }
        super.setCellColor(x,y,color);
    }

    private void createGame() {
        setTurnTimer(50);
        createGameObjects();
        drawScene();
    }
    private void drawScene(){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellColor(i, j, Color.ORANGE);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }
    private void createGameObjects(){
        rocket = new Rocket(WIDTH / 2.0, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
    }
}
