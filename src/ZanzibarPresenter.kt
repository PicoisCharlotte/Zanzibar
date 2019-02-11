import model.Dice
import model.Player

class ZanzibarPresenter {
    private val zanzibarView: ZanzibarView = ZanzibarConsole()

    private var round = 0;
    private var continu = false;

    fun start() {
        zanzibarView.welcomeGame()

        val players: MutableList<Player> = zanzibarView.createPlayers()

         zanzibarView.askScoreToReach()

        val beginPlayer = zanzibarView.askWhoBegin(players)

        var playersOrder = zanzibarView.orderPlayers(players, beginPlayer)

        playersOrder = zanzibarView.resetScore(playersOrder)
        for(player in playersOrder){
            round = 0
            println("${player.name} is playing ")
            val dices = zanzibarView.firstRound(player)
            for(dice in dices) {
                println(dice)
            }
            var diceToChange = zanzibarView.changeDice(dices)
            round += 1
            continu = zanzibarView.askToContinueRound()
            if(continu) {
                var dicesToKeep = mutableListOf<Dice>()
                do {
                    dicesToKeep = zanzibarView.rollDice(diceToChange, dices, player)
                    for(dice in dicesToKeep) {
                        println(dice)
                    }
                    diceToChange = zanzibarView.changeDice(dicesToKeep)
                    //zanzibarView.makeScoreValueCorrespondance(dicesToKeep)
                    round += 1

                    continu = zanzibarView.askToContinueRound()
                } while (continu && round <= 3)
                println("DICES FINAL >>> ")
                for(dice in dicesToKeep) {
                    println(dice)
                }
                val score = zanzibarView.getScore(dicesToKeep)
                println("SCORE >>> " + score)
                player.score += score
            } else {
                println("CONTINU PAS")
            }
        }
        //val game = Game(playersOrder, score)

    }
}

// TODO : - message ordre joueurs
