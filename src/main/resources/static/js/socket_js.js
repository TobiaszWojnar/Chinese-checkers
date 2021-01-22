const url = 'http://localhost:8080';
let stompClient;
let gameId;
let playerType;

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
    let login = document.getElementById("login").value;//TODO  pozostałe pola
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({//TODO JSON1
                "login": login
                //"nrOfPlayers": login
                //"boardSize": login
                //"ruleSet": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = 'X';//TODO Player1
                reset();//TODO setBoard
                connectToSocket(gameId);
                alert("Your created a game. Game id is: " + data.gameId);
                gameOn = true;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}


function connectToRandom() {//TODO na razie nie używamy
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
                playerType = 'O';//TODO generate board (list of buttons)
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

function connectToSpecificGame() {
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
                playerType = 'O';//TODO data.player
                reset();//displayResponse
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}
