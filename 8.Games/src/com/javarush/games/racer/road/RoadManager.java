package com.javarush.games.racer.road;

import com.javarush.games.racer.RacerGame;
import com.javarush.engine.cell.Game;
import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();

    private RoadObject createRoadObject(RoadObjectType type, int x, int y){
        if(type == RoadObjectType.THORN){
            return new Thorn(x,y);
        }else{
            return null;
        }
    }

    private void addRoadObject(RoadObjectType roadObjectType, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(roadObjectType);
        RoadObject roadObject = createRoadObject(roadObjectType, x, y);
        if (roadObject != null) {
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
            road.move(boost+road.speed);
        }
    }
}
