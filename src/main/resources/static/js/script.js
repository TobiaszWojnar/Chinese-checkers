var board = [];//TODO
var turn = "";
var isYourTurn = false;

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
            "message": "moved",
            "gameId": gameId
        }),
        success: function (data) {
            isYourTurn = false;
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
            "message": "skip",
            "gameId": gameId
        }),
        success: function (data) {
            isYourTurn = false;
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
            "message": "resign",
            "gameId": gameId
        }),
        success: function (data) {
            isYourTurn = false;//TODO isYourTurn isYourTurn
            displayResponse(data);//TODO if data.status = finished; show data.winner tylko ona będzie miała wiele
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponse(data) {

}
