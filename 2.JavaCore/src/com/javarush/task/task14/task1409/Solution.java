package com.javarush.task.task14.task1409;

/* 
Мосты
*/

public class Solution {
    public static void main(String[] args) {
        println(new WaterBridge());
        println(new SuspensionBridge());
    }
    public static void println(Bridge bridge) {
        if (bridge instanceof WaterBridge) {
            WaterBridge waterBridge = (WaterBridge) bridge;
            waterBridge.getCarsCount();
            System.out.println(waterBridge.getCarsCount());
        } else if (bridge instanceof SuspensionBridge) {
            SuspensionBridge suspensionBridge = (SuspensionBridge) bridge;
            suspensionBridge.getCarsCount();
            System.out.println(suspensionBridge.getCarsCount());
        }
    }
}

