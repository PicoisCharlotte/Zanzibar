import model.Player

interface ZanzibarView {
    fun welcomeGame()

    fun createPlayers(): MutableList<Player>

    fun askScoreToReach(): Int

}
