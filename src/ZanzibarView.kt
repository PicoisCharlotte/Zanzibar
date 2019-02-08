import model.Dice
import model.Player

interface ZanzibarView {
    fun welcomeGame()

    fun createPlayers(): MutableList<Player>

    fun askScoreToReach(): Int

    fun askWhoBegin(players: MutableList<Player>): Player

    fun orderPlayers(players: MutableList<Player>, playerBegin: Player): MutableList<Player>

    fun resetScore(players: MutableList<Player>): MutableList<Player>

    fun rollDice(diceToChange: MutableList<Dice>, dices: MutableList<Dice>, player: Player): MutableList<Dice>

    fun firstRound(player: Player): MutableList<Dice>

    fun changeDice(dices: MutableList<Dice>): MutableList<Dice>

    fun askToContinueRound(): Boolean

    fun makeScoreValueCorrespondance(dices: MutableList<Dice>)
}
