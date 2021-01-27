let currentPlayer;
let isYourTurn = false;
let youSelected = false;
let playerId;

function prepareGameToPlay(board_size){
    document.getElementById("lobby_wrapper").style.display = "none";
    document.getElementById("board_wrapper").style.display = "inline-block";
    document.getElementById("game_buttons_wrapper").style.display = "inline-block";

    makeBoard(board_size);
    document.getElementById("content-wrapper").style.height = "1000px";
    document.getElementById("content-wrapper").style.display = "block";
}

function prepareReplay(board_size){
    document.getElementById("lobby_wrapper").style.display = "none";
    document.getElementById("board_wrapper").style.display = "inline-block";
    document.getElementById("replay_buttons_wrapper").style.display = "inline-block";

    makeBoard(board_size);
    document.getElementById("content-wrapper").style.height = "1000px";
    document.getElementById("content-wrapper").style.display = "block";
}

function fieldClicked(id){
    let y = id.split("_")[0];
    let x = id.split("_")[1];

    if(isYourTurn){
        let fieldValue = board[y][x];
        if(youSelected) {
            if(fieldValue === "Possible") {
                move(x, y);
            } else if (fieldValue === "Chosen"){
                deselect(x,y);
            }
            //else ignore
        } else if(fieldValue === playerId){
            select(x,y);
        }
    }
}


function processResponse(data){
    board = data.board;
    currentPlayer = data.current;//TODO Will this work
    if (currentPlayer === playerId) {
        isYourTurn = true;
    }
    let gameInfo = "Players in game:"+preparePlayerList(data.PlayerList)+
        "Winners:"+preparePlayerList(data.winners);
    document.getElementById("game_info").innerHTML = `${gameInfo}`;
    if(data.resigned != null){
        alert("Player "+data.resigned+" has resigned");
    }
    if(data.skipped != null){
        alert("Player "+data.skipped+" has skipped");
    }
    repaint();

}
function preparePlayerList(listOfPlayer){
        return "";
}