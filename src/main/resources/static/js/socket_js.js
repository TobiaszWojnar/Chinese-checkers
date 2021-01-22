const url = 'http://localhost:8080';
let stompClient;
let gameId;
let playerId;

function connectToSocket(gameId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            displayResponse(data);
        })
    })
}

function create_game() {
    let login = document.getElementById("login").value;
    let rule_set = document.getElementById("rule_set").value;
    let nr_of_players = document.getElementById("nr_of_players").value;
    let board_size = document.getElementById("board_size").value;

    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login,
                "ruleSet": rule_set,
                "nrOfPlayers": nr_of_players,
                "boardSize": board_size
            }),
            success: function (data) {
                gameId = data.gameId;
                playerId = data.;//TODO Player1
                reset();//TODO setBoard
                connectToSocket(gameId);
                alert("You created a game. Game id is: " + data.gameId);
                gameOn = true;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}


function connect_to_random() {//TODO na razie nie używamy
    let login = document.getElementById("login").value;
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
                gameId = data.gameId;
                playerId = 'O';//TODO generate board (list of buttons)
                reset();//TODO paintBoard
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connect_by_id() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        let gameId = document.getElementById("game_id").value;
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
                playerId = 'O';//TODO data.player
                reset();//displayResponse//TODO
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);//TODO
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function replay() {
    let gameId = document.getElementById("game_id").value;
    if (gameId == null || gameId === '') {
        alert("Please enter game id");
    }
    $.ajax({
        url: url + "/game/connect/replay",//TODO
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "gameId": gameId
        }),
        success: function (data) {

        },
        error: function (error) {
            console.log(error);
        }
    })
}