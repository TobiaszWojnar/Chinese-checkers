let currentPlayer;
let isYourTurn = false;
let gameStatus;
let youSelected = false;
let playerId;

function prepareGameToPlay(board_size){
    document.getElementById("game_buttons_wrapper").style.display = "inline-block";
    document.getElementById("game_buttons_wrapper").style.width = "700px";
    prepareForGame(board_size);
}

function prepareReplay(board_size){
    document.getElementById("replay_buttons_wrapper").style.display = "inline-block";
    document.getElementById("replay_buttons_wrapper").style.width = "700px";
    prepareForGame(board_size);
    }

function prepareForGame(board_size){
    document.getElementById("content-wrapper").style.height = "1000px";
    document.getElementById("content-wrapper").style.display = "block";
    document.getElementById("lobby_wrapper").style.display = "none";
    document.getElementById("game_info").style.display = "inline-block";
    document.getElementById("game_info").style.width = "700px";
    document.getElementById("board_wrapper").style.display = "inline-block";
    makeBoard(board_size);
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

function skipClicked(){
    if(isYourTurn){
        skip();
    }
}

function resignClicked(){
    if(isYourTurn){
        resign();
    }
}

function processResponse(data){//TODO Ignacy test
    board = data.board;
    currentPlayer = data.current;
    if (currentPlayer === playerId) {
        isYourTurn = true;
    }
    gameStatus = data.status;
    if(gameStatus == "FINISHED"){
        isYourTurn = false;
        youSelected = false;
    }
    let winnerList = 'List of winners: <ol id="winners_List">'+preparePlayerList(data.winners)+"</ol>";
    document.getElementById("winners_wrapper").innerHTML = winnerList;

    let playersList = 'List of players: <ol id="players_List">'+preparePlayerList(data.playerList)+"</ol>";
    document.getElementById("players_wrapper").innerHTML = playersList;
    /*
    document.getElementById("players_List").innerHTML = preparePlayerList(data.playerList);
    document.getElementById("winners_List").innerHTML = preparePlayerList(data.winners);
     */

//                    List of winners:
//                    <ol id="Winners_List">
//                        <li>The two divs are</li>
//                        <li>The two divs are</li>
//                        <li>The two divs are</li>
//                    </ol>
// document.getElementById("game_info").innerHTML = `${gameInfo}`;
    let additionalInfo = `It is ${currentPlayer}'s turn.`;
    document.getElementById("extra_game_info").innerHTML = additionalInfo;


    if(data.resigned != null){
        alert("Player "+data.resigned+" has resigned");
    }
    if(data.skipped != null){
        alert("Player "+data.skipped+" has skipped");
    }
    repaint();

}
function preparePlayerList(playerList){
    let result = "";
    for(let i = 0 ; i < playerList.length; i++){
        result.concat(`<li>${playerList[i].login}</li>`);
    }
    return result;
}