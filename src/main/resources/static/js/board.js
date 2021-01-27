let colorMap = new Map();
colorMap.set("Board","#151720");
colorMap.set("Empty","#555555");
colorMap.set("Player1","#e54b6c");
colorMap.set("Player2","#94bce3");
colorMap.set("Player3","#E8D595");
colorMap.set("Player4","#78d49b");
colorMap.set("Player5","#f28358");
colorMap.set("Player6","#a16da8");
colorMap.set("Possible","#EEECF4");
colorMap.set("Chosen","#c8b6af");

let board;
let board_size;

/**
 * Creates html objects that are board.
 * Creates items in list, sets its css style, and adds listeners on fields of board.
 * After that it repaints it.
 *
 * @param board_size "size" of board. Length of triangle that is corner. 4 is standard.
 */
function makeBoard(board_size){
    /* We resize fields  */
    let pawnSize = 85-board_size*10;
    for (let y = 0; y < 4*board_size+1; y++) {
        for (let x = 0; x < 6*board_size+1; x++) {
            if((x+y-board_size)%2===0){
                makePawn(x,y,pawnSize,board_size);
            }else if(x===0){
                makeHalfPawn(x,y,pawnSize);
            }
        }
    }

    /**
     * Creates fields on board, with proper spacing.
     * Creates also not visible fields for getting proper spacing with shorter rows.
     * Does not create invalid fields (fields between).
     *
     * @param x X coordinate of field.
     * @param y Y coordinate of field.
     * @param pawnSize height of field.
     */
    function makePawn(x,y,pawnSize) {
        const node = document.createElement("li");
        node.setAttribute("id",y+"_"+x);
        node.style.height = `${pawnSize}px`;
        node.style.width = `${pawnSize}px`;
        node.style.margin = `0 ${pawnSize*(Math.sqrt(2.5)-1)}px 0 0`;
        if(isValidField(x,y,board_size) === true){
            node.setAttribute("class","valid_field HoverClass");
            node.onclick = function() {
                fieldClicked(y+"_"+x);
                console.log("whatever");
            }
            node.style.backgroundColor = colorMap.get("Empty");

        }else{
            node.setAttribute("class", "not_valid_field");
            node.style.visibility = "hidden";
        }
        document.getElementById("board_wrapper").appendChild(node);
    }

    /**
     * Creates indent that is needed for each second row.
     *
     * @param x X coordinate of field.
     * @param y Y coordinate of field.
     * @param pawnSize height of field.
     */
    function makeHalfPawn(x,y,pawnSize) {
        const node = document.createElement("li");
        node.setAttribute("id",y+"_"+x);
        node.setAttribute("class","not_valid_field");
        node.style.height = `${pawnSize}px`;
        node.style.width = `${pawnSize/2}px`;
        node.style.margin = `0 ${pawnSize*(Math.sqrt(2.5)-1)/2}px 0 0`;
        node.style.background = colorMap.get("Board");
        document.getElementById("board_wrapper").appendChild(node);
    }

    document.getElementById("board_wrapper").style.height = `${pawnSize*(2*board_size+1)}px`;
    document.getElementById("board_wrapper").style.width = `${pawnSize*Math.sqrt(2.5)*(3*board_size+1)+5}px`;

    board = initializeBoard(board_size);
    repaint(board,board_size);
}

/**
 * Checks if field x, y coordinates is field on Chinese Checkers Board.
 * Copy of function from server.
 *
 * @param x X coordinate
 * @param y Y coordinate
 * @param n "size" of board. Length of triangle that is corner. 4 is standard.
 * @returns {boolean} true if field (x,y) is valid field on board.
 */
function isValidField(x,y,n){
    if ((x + y - n) % 2 === 0) {
        for (let i = 0; i < n; i++) {
            if (y == i || y == 4 * n - i) {
                return x >= 3 * n - i && x <= 3 * n + i;
            }
        }
        for (let i = n; i <= 2 * n; i++) {
            if (y == i || y == 4 * n - i) {
                return x >= i - n && x <= 7 * n - i;
            }
        }
    }
    return false;
}

/**
 * Initialize empty board based on its size.
 *
 * @param n "size" of board. Length of triangle that is corner. 4 is standard.
 * @returns {any[]} 2s Array that is board of "Empty" fields.
 */
function initializeBoard(n){
    let newBoard = new Array(4*n+1);
    for(let i = 0 ; i < newBoard.length; i++){
        newBoard[i] = new Array(6*n+1).fill("Empty");
    }
    return newBoard;
}

/**
 * Function repaints board based on its current state and colorMap.
 */
function repaint(){
    for(let y = 0; y<board.length;y++){
        for(let x = 0; x<board[y].length;x++){
            if(isValidField(x,y,board_size)===true) {
                let tempColor = board[y][x];
                document.getElementById(y + "_" + x).style.background = `${colorMap.get(tempColor)}`;
            }
        }
    }
}