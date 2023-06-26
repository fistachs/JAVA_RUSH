package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

import static com.javarush.engine.cell.Key.*;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private List<Bullet> playerBullets;


    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();

        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) {
            enemyBullets.add(bullet);
        }

        drawScene();
    }
    @Override
    public void onKeyPress(Key key){
        if (key == SPACE && isGameStopped == true) {
            createGame();
        }else if(key == LEFT){
            playerShip.setDirection(Direction.LEFT);
        }else if(key == RIGHT){
        playerShip.setDirection(Direction.RIGHT);
    }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (Key.LEFT == key && playerShip.getDirection() == Direction.LEFT) {
            playerShip.setDirection(Direction.UP);
        }
        if (Key.RIGHT == key && playerShip.getDirection() == Direction.RIGHT) {
            playerShip.setDirection(Direction.UP);
        }
    }

    private void createGame() {
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        playerBullets = new ArrayList<Bullet>();
        createStars();
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        playerShip.draw(this);
        for(Bullet buletta : playerBullets){
            buletta.draw(this);
        }
        for (Bullet bullet : enemyBullets) {
            bullet.draw(this);
        }
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }

        for (Star star : stars) {
            star.draw(this);
        }
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            stars.add(new Star(x, y));
        }
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        playerShip.move();
        for(Bullet buletta : playerBullets){
            buletta.move();
        }
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.move();
        }
    }

    private void removeDeadBullets() {
        for (Bullet bullet : new ArrayList<>(enemyBullets)) {
            if (!bullet.isAlive || bullet.y >= HEIGHT - 1) {
                enemyBullets.remove(bullet);
            }
        }
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        removeDeadBullets();
        if(playerShip.isAlive == false){
            stopGameWithDelay();
        }
    }
    private void stopGame(boolean isWin){
        isGameStopped = true;
        stopTurnTimer();
        if(isWin == true){
            showMessageDialog(Color.BLACK,"You win!",Color.GREEN,70);
        }else if(isWin == false){
            showMessageDialog(Color.BLACK,"You looser!",Color.RED,70);
        }
    }
    private void stopGameWithDelay(){
        animationsCount++;
        if(animationsCount>=10){
            stopGame(playerShip.isAlive);
        }
    }
}
