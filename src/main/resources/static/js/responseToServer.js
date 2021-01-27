/**
 * Move command is send if "Possible" field is clicked.
 * We send standard response to server, with "message": "MOVE".
 *
 * @param x X coordinate of field.
 * @param y Y coordinate of field.
 */
function move(x, y) {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerId,
            "x": x,
            "y": y,
            "message": "MOVE",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            processResponse(JSON.parse(data));
            isYourTurn = false;
            youSelected = false;
        },
        error: function (error) {
            console.log(error);
        }
    })
}

/**
 * Select command is send if players own field is clicked, it sets youSelected variable as true.
 * We send standard response to server, with "message": "SELECT".
 *
 * @param x X coordinate of field.
 * @param y Y coordinate of field.
 */
function select(x, y) {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerId,
            "x": x,
            "y": y,
            "message": "SELECT",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            youSelected = true;
            processResponse(JSON.parse(data));
        },
        error: function (error) {
            console.log(error);
        }
    })
}

/**
 * Deselect command is send if "Chosen" field is clicked, it sets youSelected variable as false.
 * We send standard response to server, with "message": "DESELECT".
 *
 * @param x X coordinate of field.
 * @param y Y coordinate of field.
 */
function deselect(x, y) {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerId,
            "x": x,
            "y": y,
            "message": "DESELECT",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            youSelected = false;
            processResponse(JSON.parse(data));
        },
        error: function (error) {
            console.log(error);
        }
    })
}

/**
 * Skip command is send after button is clicked.
 * We send standard response to server, , with "message": "SKIP" and coordinates x, y empty.
 */
function skip(){
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerId,
            "x": "",
            "y": "",
            "message": "SKIP",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            isYourTurn = false;
            youSelected = false;
            processResponse(JSON.parse(data));
        },
        error: function (error) {
            console.log(error);
        }
    })
}

/**
 * Resign command is send after button is clicked.
 * We send standard response to server, , with "message": "RESIGN" and coordinates x, y empty.
 */
function resign(){
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "playerNr": playerId,
            "x": "",
            "y": "",
            "message": "RESIGN",
            "gameId": gameId,
            "login": login
        }),
        success: function (data) {
            isYourTurn = false;
            youSelected = false;
            processResponse(JSON.parse(data));
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function previous(){
    $.ajax({
        url: url + "/game/replay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "gameId": gameId,
            "login": login,
            "forward": false
        }),
        success: function (data) {
            processResponse(JSON.parse(data));
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function forward(){
    $.ajax({
        url: url + "/game/replay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "gameId": gameId,
            "login": login,
            "forward": true,
        }),
        success: function (data) {
            processResponse(JSON.parse(data));
        },
        error: function (error) {
            console.log(error);
        }
    })
}