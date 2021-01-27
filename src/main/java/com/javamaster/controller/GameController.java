package com.javamaster.controller;

import com.javamaster.controller.dto.ConnectRequest;
import com.javamaster.exception.InvalidGameException;
import com.javamaster.exception.InvalidParamException;
import com.javamaster.exception.NotFoundException;
import com.javamaster.model.*;
import com.javamaster.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody NewGame newGame) {
        log.info("start game request: {}", newGame);
        return ResponseEntity.ok(gameService.createGame(newGame));
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws NotFoundException, InvalidGameException {
        log.info("gameplay: {}", request);
        Game game = gameService.gamePlay(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameId(), game);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/startreplay")
    public ResponseEntity<Game> startreplay(@RequestBody String gameId) {
        log.info("start replay request: {}", gameId);
        return ResponseEntity.ok(gameService.startReplay(gameId));
    }

    @PostMapping("/replay")
    public ResponseEntity<Game> replay(@RequestBody Replay replay) {
        log.info("replay: {}", replay);
        Game game = gameService.replay(replay.getGameId(), replay.isForward());
        simpMessagingTemplate.convertAndSend("/topic/replay-progress/" + replay.getGameId()
                + "/" + replay.getLogin(), game);
        return ResponseEntity.ok(game);
    }
}
