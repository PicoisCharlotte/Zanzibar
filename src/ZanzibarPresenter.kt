import model.Game
import model.Player

class ZanzibarPresenter {
    private val zanzibarView: ZanzibarView = ZanzibarConsole()

    fun start() {
        zanzibarView.welcomeGame()

        val players: MutableList<Player> = zanzibarView.createPlayers()

        val score: Int = zanzibarView.askScoreToReach()

        val beginPlayer = zanzibarView.askWhoBegin(players)

        var playersOrder = zanzibarView.orderPlayers(players, beginPlayer)

        playersOrder = zanzibarView.resetScore(playersOrder)
        if (playersOrder != null) {
            for(player in playersOrder){
                zanzibarView.firstRound(player)
            }
        }
        //val game = Game(playersOrder, score)

    }
}

// TODO : - message ordre joueurs
