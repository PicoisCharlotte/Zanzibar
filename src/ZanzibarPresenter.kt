import model.Game
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
            val dices = zanzibarView.firstRound(player)
            val diceToChange = zanzibarView.keepDice(dices)

            zanzibarView.rollDice(diceToChange, dices, player)

        }
        //val game = Game(playersOrder, score)

    }
}

// TODO : - message ordre joueurs
