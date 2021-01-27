const url = 'http://localhost:8080';
let stompClient;
let gameId;
let login;
let playerList;

function connectToSocket(gameId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            processResponse(data);
        })
    })
}

function create_game() {
    login = document.getElementById("login").value;
    let rule_set = document.getElementById("rule_set").value;
    let nr_of_players = document.getElementById("nr_of_players").value;
    let e_board_size = document.getElementById("board_size");
    board_size = e_board_size.options[e_board_size.selectedIndex].value;
    let board_type = e_board_size.options[e_board_size.selectedIndex].text;

    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        prepareGameToPlay(e_board_size.value);
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "login": login
                },
                "ruleSet": rule_set,
                "numOfPlayers": nr_of_players,
                "boardType": board_type
            }),
            success: function (data) {
                gameId = data.gameId;
                playerId = data.player;
                processResponse(data);                              //TODO
                connectToSocket(gameId);
                alert("You created a game. Game id is: " + gameId);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connect_to_random() {//TODO na razie nie używamy
    login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/connect/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login
            }),
            success: function (data) {
                alert("Not implemented: " );
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connect_by_id() {
    login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        gameId = document.getElementById("game_id").value;
        if (gameId == null || gameId === '') {
            alert("Please enter game id");
        }
        $.ajax({
            url: url + "/game/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "login": login
                },
                "gameId": gameId
            }),
            success: function (data) {
                gameId = data.gameId;
                playerId = data.player;//TODO
                board_size = (data.board.length-1)/4;
                prepareGameToPlay(board_size);
                processResponse(data);
                connectToSocket(gameId);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToSocketReplay(gameId, login) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/replay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/replay-progress/" + gameId + "/" + login, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            processResponse(data);
        })
    })
}

function replay() {
    login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        gameId = document.getElementById("game_id").value;
        if (gameId == null || gameId === '') {
            alert("Please enter game id");
        }
    }
    $.ajax({
        url: url + "/game/connect/replay",//TODO
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "gameId": gameId
        }),
        success: function (data) {//TODO ma mieć dwa
            board_size = (data.board.length-1)/4;
            prepareReplay(board_size);
            processResponse(data);
            connectToSocketReplay(gameId, login);
            //dostaje listę ruchów
            //i aktualną planszę
            /*
            data
            */
        },
        error: function (error) {
            console.log(error);
        }
    })
}