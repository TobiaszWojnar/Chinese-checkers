function makeBoard(n){
    for (let y = 0; y < 4*n+1; y++) {
        for (let x = 0; x < 6*n+1; x++) {
            makePawn(x,y);
        }
    }
    var pawnSize = 40;
    //document.getElementsByClassname("field").style.height = pawnSize+"pt";
    //document.getElementById("field").style.width = pawnSize+"pt";

    var widthBoard = pawnSize*(7*n+1);
    //document.getElementById("gameBoard").style.width = widthBoard+"pt";

    var widthWrapper = pawnSize*(7*n+1)+32;
    //document.getElementById("wrapper").style.width = widthWrapper+"pt";
    //var widthWithMargin = 300*n+50//50 +100*(3*n+1https://www.w3schools.com/colors/color_tryit.asp?hex=7FFF00);
    //document.getElementById("gameBoard").style.width = widthWithMargin+"pt";
    //document.getElementsByClassname("field").style.property = new style;
}

function makePawn(x,y) {
  var node = document.createElement("li");
  node.setAttribute("id",y+"_"+x);
  node.setAttribute("class","field");
  document.getElementById("gameBoard").appendChild(node);
}

var n=3
makeBoard(n);

// document.getElementById(id).style.property = new style