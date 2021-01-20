package com.javamaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Field {//TODO field
    Empty(0), Player1(1), Player2(2), Player3(3), Player4(4),
    Player5(5), Player6(6), Possible(7), Chosen(8);

    private final Integer value;

    public Integer getValue() {
        return value;
    }
}
