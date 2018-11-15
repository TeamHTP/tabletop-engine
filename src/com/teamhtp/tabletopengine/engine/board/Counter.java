package com.teamhtp.tabletopengine.engine.board;

import com.teamhtp.tabletopengine.engine.Unique;

import java.util.UUID;

public class Counter extends Unique {

    private String name;
    private double value;

    public Counter(String name, double value, UUID uuid) {
        super(uuid);
        this.name = name;
        this.value = value;
    }

    public Counter(String name, double value) {
        this(name, value, UUID.randomUUID());
    }

    public Counter(String name) {
        this(name, 0);
    }

    public String getName() {
        return this.name;
    }

    public double getValue() {
        return this.value;
    }

}
