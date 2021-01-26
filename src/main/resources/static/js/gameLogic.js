let turn = "";
let currentPlayer;
let isYourTurn = false;
let youSelected = false;
let yourPlayerNr;

function prepareGameToPlay(board_size){

    document.getElementById("lobby_wrapper").style.display = "none";
    document.getElementById("board_wrapper").style.display = "inline-block";//TODO why not see?
    document.getElementById("game_buttons_wrapper").style.display = "inline-block";
    makeBoard(board_size);
    repaint(board,colors,board_size);
    document.getElementById("content-wrapper").style.height = "1000px";
    document.getElementById("content-wrapper").style.display = "block";
}


$(".valid_field").click(function () {

    let touchedField = $(this).attr('id');

    console.log("touchedField why not work");
    if(isYourTurn){
        let fieldValue = board[touchedField.split("_")[0]][touchedField.split("_")[1]];
        if(youSelected) {
            if(fieldValue===7) {//highlighted or possible
                move(yourPlayerNr,touchedField.split("_")[1],touchedField.split("_")[0]);
            } else if (fieldValue===8){//chosen
                deselect(yourPlayerNr,touchedField.split("_")[1],touchedField.split("_")[0]);
            }
            //else ignore
        } else if(fieldValue === yourPlayerNr){
            select(yourPlayerNr,touchedField.split("_")[1],touchedField.split("_")[0]);
        }
    }
});

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
