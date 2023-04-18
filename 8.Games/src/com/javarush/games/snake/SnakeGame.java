package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game{
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    
    
    @Override
    public void initialize(){
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }
    
    @Override
    public void onTurn(int step){
        snake.move();
        drawScene();
    }
    
    private void createGame(){
        snake = new Snake(WIDTH/2,HEIGHT/2);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        
        
    }
    
    private void drawScene(){
        for(int y = 0 ;y < 15;y++){
            for(int x = 0 ; x < 15 ;x++){
                setCellColor(x,y,Color.DARKSEAGREEN);
            }
        }
        snake.draw(this);
    }
    
}