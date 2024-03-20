package com.svlshop.service;

public class General {
    public static double doubleRounding(double d, int tail) {
        tail = (int) Math.pow(10,tail);
        return (double) ((int)(d * tail)) / tail;



    }

}
