function Player(name, points) {
  this.name = name;
  this.points = points;
}



//Business logic

var player1 = new Player();
var player2 = new Player();

var score1 = 0;
var score2 = 0;

//1st Player //

Player.prototype.checkWin1 = function() {
  var diceRoll1 = Math.floor(Math.random() * 6 + 1);

  if (diceRoll1 === 1) {
    alert("2nd player's Turn");
    score1 = 0;
  } else {
    if (score1 >= 100) {
      alert('1st players victory!')
    } else {
      console.log('aluta continua!');
      player1.points = diceRoll1;
      score1 += diceRoll1;
      console.log(score1);
      console.log('You rolled a die of: ' + diceRoll1 + ' for a score of: ' + score1);
    }
  }
}
//2nd player//

Player.prototype.checkWin2 = function() {
  var diceRoll2 = Math.floor(Math.random() * 6 + 1);

  if (diceRoll2 === 1) {
    alert("1st Player's Turn");
    score2 = 0;
  } else {
    if (diceRoll2 >= 100) {
      alert("2nd player's victory!");
    } else {
      console.log('The game continues!');
      player2.points = diceRoll2;
      score2 += diceRoll2;
      console.log(score2);
      console.log('You rolled a die of: ' + diceRoll2 + ' for a score of: ' + score2);
    }
  }
}

console.log(player1);
console.log(player2);


//User Interface//

$(document).ready(function() {
  $('form#player1Form').submit(function(event) {
    event.preventDefault();
    var name1Display = ($('input#player1Name').val());
    console.log(name1Display);
    $('#player1NameDisp').text(name1Display);
    $('#score1').text(score1);
    $('#roll1').click(player1.checkWin1());
  });
  $('form#player2Form').submit(function(event) {
    event.preventDefault();
    var name2Display = ($('input#player2Name').val());
    console.log(name2Display);
    $('#player2NameDisp').text(name2Display);
    $('#score2').text(score2);
    $('#roll2').click(player2.checkWin2());
  });

});
