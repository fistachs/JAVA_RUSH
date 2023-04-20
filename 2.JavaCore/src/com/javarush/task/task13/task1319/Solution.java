package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        
        StringBuilder builder = new StringBuilder();
        
        String ex = "";
        while(!ex.equals("exit")){
            ex = reader.readLine();
            builder.append(ex).append("\n");
        }
        
        writer.write(builder.toString());
        writer.close();
    }
}
