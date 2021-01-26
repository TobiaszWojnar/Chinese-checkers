let boardColor = "#151720";
let emptyFieldColor = "#555555";
let colors = ["#555555","#e54b6c","#94bce3","#E8D595","#78d49b","#f28358","#a16da8","#EEECF4","#c8b6af"];


// let n=2
// makeBoard(n);

let board;
   //  board[1][1]=1;
   //  board[5][2]=2;
   //  board[5][3]=3;
   //  board[5][4]=4;
   //  board[5][5]=5;
   //  board[5][6]=6;
   //  board[5][7]=7;
   //  board[5][8]=8;
   // //board[6][5]=3;

// repaint(board,colors,board_size);

function makeBoard(board_size){
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
    function makePawn(x,y,pawnSize,n) {
        const node = document.createElement("li");
        node.setAttribute("id",y+"_"+x);
        node.style.height = `${pawnSize}px`;
        node.style.width = `${pawnSize}px`;
        node.style.margin = `0 ${pawnSize*(Math.sqrt(2.5)-1)}px 0 0`;
        if(isValidField(x,y,n) === true){
            node.setAttribute("class","valid_field HoverClass");//
            node.style.backgroundColor = `${colors[0]}`;

        }else{
            node.setAttribute("class","not_valid_field");
            node.style.visibility = "hidden";
        }
        document.getElementById("board_wrapper").appendChild(node);
    }

    function makeHalfPawn(x,y,pawnSize) {
        const node = document.createElement("li");
        node.setAttribute("id",y+"_"+x);
        node.setAttribute("class","not_valid_field");
        node.style.height = `${pawnSize}px`;
        node.style.width = `${pawnSize/2}px`;
        node.style.margin = `0 ${pawnSize*(Math.sqrt(2.5)-1)/2}px 0 0`;
        node.style.background = `${boardColor}`;
        document.getElementById("board_wrapper").appendChild(node);
    }

    document.getElementById("board_wrapper").style.height = `${pawnSize*(2*board_size+1)}px`;
    document.getElementById("board_wrapper").style.width = `${pawnSize*Math.sqrt(2.5)*(3*board_size+1)+5}px`;

    board = createEmptyBoard(board_size);
    repaint(board,colors,board_size);
}


function isValidField(x,y,n){
    if ((x + y -n) % 2 === 0) {
        for (let i = 0; i < n; i++) {
            if (y === i || y === 4 * n - i) {
                return x >= 3 * n - i && x <= 3 * n + i;
            }
        }
        for (let i = n; i <= 2 * n; i++) {
            if (y === i || y === 4 * n - i) {
                return x >= i - n && x <= 7 * n - i;
            }
        }
    }
    return false;
}

function createEmptyBoard(n){
    let newBoard = new Array(4*n+1);
    for(let i = 0 ; i < newBoard.length; i++){
        newBoard[i] = new Array(6*n+1).fill(0);
    }
    return newBoard;
}

function repaint(board,colors,n){
    for(let y = 0; y<board.length;y++){
        for(let x = 0; x<board[y].length;x++){
            if(isValidField(x,y,n)===true) {
                let tempColor = board[y][x];
                //console.log(x+" "+y+" "+tempColor);
                document.getElementById(y + "_" + x).style.background = `${colors[tempColor]}`;
            }
        }
    }
}
//TODO add onclick only on board