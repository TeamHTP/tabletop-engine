package com.teamhtp.tabletopengine.engine;

import java.util.Random;

public class Dice {

    private Random random;

    private int sides;

    private int roll;

    public Dice(int sides, long seed) {
        this.random = new Random(seed);
        this.sides = sides;
    }

    public Dice(int sides) {
        this.random = new Random();
        this.sides = sides;
    }

    public int roll() {
        roll = random.nextInt(sides) + 1;
        return getLastRoll();
    }

    public int getLastRoll() {
        return roll;
    }

}
