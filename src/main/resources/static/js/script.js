var turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
var turn = "";
var gameOn = false;

function playerTurn(turn, id) {//TODO to jest próba wybrania,
    if (gameOn) {
        var spotTaken = $("#" + id).text();
        if (spotTaken === "#") {
            makeAMove(playerType, id.split("_")[0], id.split("_")[1]);
        }
    }
}

function makeAMove(type, xCoordinate, yCoordinate) {//type = player //change name {type, x, y, message}
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({//TODO diffrent stuff send
            "type": type,
            "coordinateX": xCoordinate,
            "coordinateY": yCoordinate,
            "gameId": gameId
        }),
        success: function (data) {
            gameOn = false;//TODO gameOn isYourTurn
            displayResponse(data);//TODO if data.status = finished; show data.winner tylko ona będzie miała wiele
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponse(data) {
    let board = data.board;
    for (let i = 0; i < board.length; i++) {//TODO different make new function
        for (let j = 0; j < board[i].length; j++) {
            if (board[i][j] === 1) {
                turns[i][j] = 'X'
            } else if (board[i][j] === 2) {
                turns[i][j] = 'O';
            }
            let id = i + "_" + j;
            $("#" + id).text(turns[i][j]);
        }
    }
    if (data.winner != null) {//data.message winner, resigned and skipped
        alert("Winner is " + data.winner);
    }
    gameOn = true;
}

$(".field").click(function () {//TODO add is valid
    var slot = $(this).attr('id');
    playerTurn(turn, slot);
});
//TODO add function on button skip

function reset() {
    turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
    $(".field").text("#");
}

$("#reset").click(function () {
    reset();
});
