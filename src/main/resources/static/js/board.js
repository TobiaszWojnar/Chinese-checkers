let boardColor = "#151720";
let emptyFieldColor = "#555555";
let colors = ["#555555","red","blue","yellow","green","orange","pink","lightblue","lightpink"];


let n=4
makeBoard(n);


let board = createEmptyBoard(n);
   board[5][5]=4;
   //board[6][5]=3;

repaint(board,colors,n);//TODO why not work

function makeBoard(n){
    let pawnSize = 85-n*10;
    for (let y = 0; y < 4*n+1; y++) {
        for (let x = 0; x < 6*n+1; x++) {
            if((x+y-n)%2===0){
                makePawn(x,y,pawnSize,n);
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
        if(isValidField(x,y,n) === true){//TODO color based on
            node.setAttribute("class","valid_field HoverClass");
            node.style.backgroundColor = `${colors[0]}`;//`${colors[board[y][x]]}`;

        }else{
            node.setAttribute("class","not_valid_field");
            node.style.background = `${boardColor}`;
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


    document.getElementById("board_wrapper").style.height = `${pawnSize*(2*n+1)}px`;
    document.getElementById("board_wrapper").style.width = `${pawnSize*Math.sqrt(2.5)*(3*n+1)+5}px`;
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
    let newBoard = [];
    let row = [];
    for (let i = 0; i < 6*n+1;i++){
        row.push(0);
    }
    for (let i = 0; i < 4*n+1;i++){
        newBoard.push(row);
    }
    return newBoard;
}

function repaint(board,colors,n){
    for(let y = 0; y<board.length;y++){
        for(let x = 0; x<board[0].length;x++){
            if(isValidField(x,y,n)===true) {
                let tempColor = board[y][x];
                console.log(x+" "+y+" "+tempColor);
                document.getElementById(y + "_" + x).style.background = `${colors[tempColor]}`;
            }
        }
    }
}
//TODO add onclick only on board