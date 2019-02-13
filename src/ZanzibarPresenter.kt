import model.Dice
import model.Player

class ZanzibarPresenter {
    private val zanzibarView: ZanzibarView = ZanzibarConsole()

    private var round = 0
    private var continu = false
    private var scoreCompare = 0

    fun start() {
        zanzibarView.welcomeGame()

        val players: MutableList<Player> = zanzibarView.createPlayers()

        val scoreToReach = zanzibarView.askScoreToReach()

        val beginPlayer = zanzibarView.askWhoBegin(players)

        var playersOrder = zanzibarView.orderPlayers(players, beginPlayer)

        var maxRound = 0
        var maxRoundFlag = false

        playersOrder = zanzibarView.resetScore(playersOrder)

        while(scoreCompare < scoreToReach) {
            for (player in playersOrder) {
                round = 1
                println("${player.name} is playing round n° $round")

                val dices = zanzibarView.firstRound(player)
                for (dice in dices) {
                    println(dice)
                }

                var diceToChange = zanzibarView.changeDice(dices)

                continu = zanzibarView.askToContinueRound()
                if (continu
//                        && maxRound > 1
                ) {
//                    round += 1

                    var dicesToKeep = mutableListOf<Dice>()
                    do {
                        //round ++
                        dicesToKeep = zanzibarView.rollDice(diceToChange, dices, player)
                        println("DICES of ${player.name} round : $round >>> ")
                        for (dice in dicesToKeep) {
                            println(dice)
                        }
                        continu = zanzibarView.askToContinueRound()
                        if (continu)
                            diceToChange = zanzibarView.changeDice(dicesToKeep)

                        round += 1
                        println("round $round")
                    } while (continu && ((!maxRoundFlag && round < 3) || (round < maxRound)) )


                    println("DICES FINAL >>> ")
                    for (dice in dicesToKeep) {
                        println(dice)
                    }
                    val score = zanzibarView.getScore(dicesToKeep)
                    player.score += score


                    println("SCORE >>> ${player.score}")

                    scoreCompare = zanzibarView.compareScore(players)
                    if(scoreCompare >= scoreToReach){
                        println("Game is over, the winner is  : ${player.name} reached the score : ${player.score}")
                        break
                    }

                } else {
                    println("Don't continue")
                }

                if(!maxRoundFlag) {
                    maxRoundFlag = true
                    maxRound = round
                }
            }
        }
    }
}