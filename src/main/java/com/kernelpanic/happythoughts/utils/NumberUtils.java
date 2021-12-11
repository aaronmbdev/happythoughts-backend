package com.kernelpanic.happythoughts.utils;

public class NumberUtils {

    public static int getRandomInt(final int min, final int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
