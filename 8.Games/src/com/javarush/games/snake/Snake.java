package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import java.util.ArrayList;
import java.util.List;


public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction  = Direction.LEFT;
    
    
    private List <GameObject>snakeParts = new ArrayList<>();
    
    public Snake(int x,int y){
        snakeParts.add(new GameObject( x , y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2,y));
    }   
    public void draw(Game game){
        Color color = isAlive ? Color.BLACK : Color.RED;
        
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);
            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;
            game.setCellValueEx(part.x,part.y,Color.NONE,sign,color,75);
            
        }
    }
    
    public void setDirection(Direction direction){
        this.direction = direction;
    }
    
    public void move(){
        
    }

    public GameObject createNewHead(){
        GameObject oldHead = snakeParts.get(0);
        if(direction == Direction.LEFT){
            return new GameObject(oldHead.x -1, oldHead.y );
        }else if(direction == Direction.RIGHT){
            return new GameObject(oldHead.x + 1, oldHead.y );
        }else if(direction == Direction.UP){
            return new GameObject(oldHead.x, oldHead.y -1);
        }else{
            return new GameObject(oldHead.x, oldHead.x+1);
        }

    }

    public void removeTail(){
        snakeParts.remove(snakeParts.size() - 1);

    }
}