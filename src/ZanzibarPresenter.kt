import model.Player

class ZanzibarPresenter {
    private val zanzibarView: ZanzibarView = ZanzibarConsole()

    fun start() {
        zanzibarView.welcomeGame()

        val players: MutableList<Player> = zanzibarView.createPlayers()

         zanzibarView.askScoreToReach()

        val beginPlayer = zanzibarView.askWhoBegin(players)

        var playersOrder = zanzibarView.orderPlayers(players, beginPlayer)

        playersOrder = zanzibarView.resetScore(playersOrder)
        for(player in playersOrder){
            println("${player.name} is playing ")
            val dices = zanzibarView.firstRound(player)
            val diceToChange = zanzibarView.changeDice(dices)

            do {
                val dicesToKeep = zanzibarView.rollDice(diceToChange, dices, player)

                zanzibarView.makeScoreVelueCorrespondance(dicesToKeep)

            } while (zanzibarView.askToContinueRound())


        }
        //val game = Game(playersOrder, score)

    }
}

// TODO : - message ordre joueurs
