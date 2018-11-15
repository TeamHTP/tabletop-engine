package com.teamhtp.tabletopengine.engine;

import java.util.UUID;

public class Unique {

    private UUID uuid;

    public Unique(UUID uuid) {
        this.uuid = uuid;
    }

    public Unique() {
        this(UUID.randomUUID());
    }

    public UUID getUuid() {
        return uuid;
    }

}
