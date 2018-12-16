import model.Game
import model.Player

class ZanzibarPresenter {
    private val zanzibarView: ZanzibarView = ZanzibarConsole()

    fun start() {
        zanzibarView.welcomeGame()

        val players: MutableList<Player> = zanzibarView.createPlayers()

        val score: Int = zanzibarView.askScoreToReach()

        val game = Game(players, score)

        val beginPlayer = zanzibarView.askWhoBegin(players)
    }
}
