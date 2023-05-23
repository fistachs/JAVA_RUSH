package com.javarush.games.racer.road;

import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;
import com.javarush.engine.cell.Game;
import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    private static final int PLAYER_CAR_DISTANCE = 12;
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }


    private RoadObject createRoadObject(RoadObjectType type, int x, int y){
        if(type == RoadObjectType.THORN){
            return new Thorn(x,y);
        }else if(type == RoadObjectType.DRUNK_CAR){
            return new MovingCar(x,y);
        }else {
            return new Car(type, x, y);
        }
    }

    private void addRoadObject(RoadObjectType roadObjectType, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(roadObjectType);
        RoadObject roadObject = createRoadObject(roadObjectType, x, y);
        if (isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }
    }
    public void draw(Game game){
        for(RoadObject road : items){
            road.draw(game);
        }
    }
    public void move(int boost){
        for(RoadObject road : items) {
            road.move(boost+road.speed,items);
        }
        deletePassedItems();
    }
    private boolean isThornExists(){
        for(RoadObject road : items) {
            if(road instanceof Thorn){
                return true;
            }
        }
        return false;
    }

    private void generateThorn(Game game) {
        int randomNumber = game.getRandomNumber(100);
        if (randomNumber < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }
    public void generateNewRoadObjects(Game game){
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        for (RoadObject item : new ArrayList<>(items)) {
            if (item.y >= RacerGame.HEIGHT) {
                items.remove(item);
                if (item.type != RoadObjectType.THORN) {
                    passedCarsCount++;
                }
            }
        }
    }

    public boolean checkCrush(PlayerCar playerCar) {
        for (RoadObject item : items) {
            if (item.isCollision(playerCar)) {
                return true;
            }
        }
        return false;
    }
    private void generateRegularCar(Game game){
        int RegCar = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if(RegCar < 30){
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }
    private boolean isRoadSpaceFree(RoadObject object){
        for (RoadObject item : items) {
            if (item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }
    private boolean isMovingCarExists(){
        for (RoadObject item : items){
            if(item instanceof MovingCar){
                return true;
            }
        }
        return false;
    }
    private void generateMovingCar(Game game){
        int game1 = game.getRandomNumber(100);
        if(game1 < 10 && !isMovingCarExists()){
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }
}
