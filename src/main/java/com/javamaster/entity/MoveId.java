package com.javamaster.entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class MoveId implements Serializable {

    @Column(name = "game")
    private String game;

    @Column(name = "move_id")
    private int move_id;

    public MoveId() {

    }

    public String getGame_id() {
        return game;
    }

    public int getMove_id() {
        return move_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame_id(), getMove_id());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoveId)) {
            return false;
        }
        MoveId that = (MoveId) obj;
        return Objects.equals(getGame_id(), that.getGame_id()) &&
                Objects.equals(getMove_id(), that.getMove_id());
    }
}
