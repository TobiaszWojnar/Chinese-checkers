var board = [];//TODO
var turn = "";
var currentPlayer;

//TODO
 /*
    if isYourTurn
        if youSelected
            if clickedField == highlighted
                move
            if clickedField == chosen
                deselect
            else
                ignore
        else
            if clickedField == yourId
                select
*/
/*
//TODO na buttonach
*/
$(".field").click(function () {//TODO add is valid
    var slot = $(this).attr('id');
    //TODO if your move
    //TODO if is valid field
    move(playerNr, id.split("_")[0], id.split("_")[1]);
});

function playerTurn(turn, id) {//TODO to jest próba wybrania,
    if (isYourTurn) {
        var spotTaken = $("#" + id).text();
        if (spotTaken === "#") {
            move(playerNr, id.split("_")[0], id.split("_")[1]);
        }
    }
}

function move(playerNr, x, y) {//type = player //change name {type, x, y, message}
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerNr,
            "x": x,
            "y": y,
            "message": "MOVE",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            processResponse(data);
            isYourTurn = false;
            displayResponse(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function select(playerNr, x, y) {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerNr,
            "x": x,
            "y": y,
            "message": "SELECT",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            displayResponse(data);//TODO if data.status = finished; show data.winner tylko ona będzie miała wiele
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function deselect(playerNr, x, y) {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerNr,
            "x": x,
            "y": y,
            "message": "DESELECT",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            displayResponse(data);//TODO if data.status = finished; show data.winner tylko ona będzie miała wiele
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function skip(){
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerNr,
            "x": "",
            "y": "",
            "message": "SKIP",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            displayResponse(data);//TODO if data.status = finished; show data.winner tylko ona będzie miała wiele
        },
        error: function (error) {
            console.log(error);
        }
    })
}
function resign(){
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerNr,
            "x": "",
            "y": "",
            "message": "RESIGN",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            processResponse(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}
function processResponse(data){//TODO if data.status = finished; show data.winner tylko ona będzie miała wiele


}

function displayResponse(data) {

}
