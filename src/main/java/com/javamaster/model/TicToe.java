package com.javamaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicToe {//TODO field
    X(1), O(2);

    private Integer value;
}
