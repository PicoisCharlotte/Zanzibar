import model.Game
import model.Player

class ZanzibarPresenter {
    private val zanzibarView: ZanzibarConsole = ZanzibarConsole(this)

    fun start() {
        zanzibarView.welcomeGame()

        val numberPlayer: Int = zanzibarView.getNumberOfPlayer()

        val players: MutableList<Player> = zanzibarView.createPlayers(numberPlayer)

        val score: Int = zanzibarView.fixScoreToReach()

        val game = Game(players, score)

        println(game)
    }
}
