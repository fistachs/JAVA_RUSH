package com.javarush.task.task12.task1226;

/* 
Лазать, летать и бегать
*/

public class Solution {

    public static void main(String[] args) {

    }

    public abstract class Cat implements CanRun,CanClimb{
    }

    public abstract class Dog implements CanRun{
    }

    public abstract class Tiger extends Cat {
    }

    public abstract class Duck implements CanFly,CanRun{
    }

    public interface CanFly{
        void fly();
    }

    public interface CanClimb{
        void climb();
    }

    public interface CanRun{
        void run();
    }
}
