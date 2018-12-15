import model.Player

interface ZanzibarView {
    fun welcomeGame()

    fun getNumberOfPlayer(): Int

    fun createPlayers(numberPlayer: Int): MutableList<Player>

    fun fixScoreToReach(): Int

}
