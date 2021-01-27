package com.javamaster.model;

import lombok.Data;

@Data
public class Replay {
    private String gameId;
    private String login;
    private boolean forward;
}
