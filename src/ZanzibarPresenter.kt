import model.Dice
import model.Player

class ZanzibarPresenter {
    private val zanzibarView: ZanzibarView = ZanzibarConsole()

    private var round = 0;
    private var continu = false;
    private var scoreCompare = 0;

    fun start() {
        zanzibarView.welcomeGame()

        val players: MutableList<Player> = zanzibarView.createPlayers()

        val scoreToReach = zanzibarView.askScoreToReach()

        val beginPlayer = zanzibarView.askWhoBegin(players)

        var playersOrder = zanzibarView.orderPlayers(players, beginPlayer)

        playersOrder = zanzibarView.resetScore(playersOrder)
        while(scoreCompare < scoreToReach) {
            for (player in playersOrder) {
                round = 1
                println("${player.name} is playing ")
                val dices = zanzibarView.firstRound(player)
                for (dice in dices) {
                    println(dice)
                }
                var diceToChange = zanzibarView.changeDice(dices)
                round += 1
                continu = zanzibarView.askToContinueRound()
                if (continu) {
                    var dicesToKeep = mutableListOf<Dice>()
                    do {
                        dicesToKeep = zanzibarView.rollDice(diceToChange, dices, player)
                        println("DICES of " + player.name + " round : " + round + " >>> ")
                        for (dice in dicesToKeep) {
                            println(dice)
                        }
                        continu = zanzibarView.askToContinueRound()
                        if(continu)
                            diceToChange = zanzibarView.changeDice(dicesToKeep)
                        //zanzibarView.makeScoreValueCorrespondance(dicesToKeep)
                        round += 1

                    } while (continu && round <= 3)
                    println("DICES FINAL >>> ")
                    for (dice in dicesToKeep) {
                        println(dice)
                    }
                    val score = zanzibarView.getScore(dicesToKeep)
                    player.score += score
                    println("SCORE >>> " + player.score)
                } else {
                    println("CONTINU PAS")
                }
                scoreCompare = zanzibarView.compareScore(players)
                if(scoreCompare >= scoreToReach){
                    println("Fin de la partie, le gagnant est : " + player.name + " avec un score de : " + player.score)
                    break
                }
            }
        }
        //val game = Game(playersOrder, score)
    }
}

// TODO : - message ordre joueurs
