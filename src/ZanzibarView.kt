import model.Dice
import model.Player

interface ZanzibarView {
    fun welcomeGame()

    fun createPlayers(): MutableList<Player>

    fun askScoreToReach(): Int

    fun askWhoBegin(players: MutableList<Player>): Player

    fun orderPlayers(players: MutableList<Player>, playerBegin: Player): MutableList<Player>

    fun resetScore(players: MutableList<Player>): MutableList<Player>

    fun rollDice(dice: MutableList<Dice>, player: Player): Player

    fun firstRound(player: Player): MutableList<Dice>

    fun keepDice(dices: MutableList<Dice>): MutableList<Dice>
}
