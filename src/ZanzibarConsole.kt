import model.Dice
import model.Player
import java.util.*

class ZanzibarConsole: ZanzibarView {
    override fun welcomeGame() {
        println("Welcome to Zanzibar !")
    }

    override fun createPlayers(): MutableList<Player> {
        val numberPlayer = askNumberOfPlayer()
        val players: MutableList<Player> = mutableListOf()

        for (i in 1..numberPlayer) {
            println("Enter name of player n°$i")
            val name: String? = readLine()

            val player = Player(name, 0)

            players.add(player)
        }
        return players
    }

    private fun askNumberOfPlayer(): Int {
        var number: Int
        do {
            println("How many players will join ? (min 2)")
            val playerNumber: String? = readLine()
            number = playerNumber?.toInt() ?: 2

        } while (number < 2)

        println("$number players will play")

        return number
    }

    override fun askScoreToReach(): Int {
        var scoreToReach: Int
        var choice: String? = null
        var score: String?

        do {
            println("Now, you have to decide score to reach to win :")
            score = readLine()
            //2 is minimum because 3*2 = 2
            scoreToReach = score?.toInt() ?: 2

            if(scoreToReach < 2) {
                println("Score can't be under 2")
            }else{
                println("Score fixed at $score. Do you valid this score? Y/N")
                choice = readLine()
            }
        } while (scoreToReach < 2
                || choice!!.startsWith("n", true))

        return scoreToReach
    }

    override fun askWhoBegin(players: MutableList<Player>): Player{
        println("We have to determine which player will begin")

        val random = Random()
        var total: Int
        var higherScore = 0

        var beginPlayer = Player()

        for (player in players) {
            val firstValue = random.nextInt(1..7)
            val secondValue = random.nextInt(1..7)
            val thirdValue = random.nextInt(1..7)

            total = firstValue + secondValue + thirdValue

            if(total > higherScore) {
                higherScore = total
                beginPlayer = player
            }
            player.score = total

            println("${player.name}'s score is ${player.score}")

        }
        println("Player $beginPlayer will begin")
        return beginPlayer
    }

    override fun orderPlayers(players: MutableList<Player>, playerBegin: Player): MutableList<Player> {
        var playersOrder : MutableList<Player> = mutableListOf()
        playersOrder = players.sortedBy {it.score }.toMutableList()
        return playersOrder.asReversed()
    }

    override fun resetScore(players: MutableList<Player>): MutableList<Player> {
        for(player in players){
            player.score = 0
        }
        return players
    }

    override fun rollDice(dice: MutableList<Dice>, player: Player): Player {
        return player
    }

    override fun firstRound(player: Player): MutableList<Dice> {
        val dices: MutableList<Dice> = rollThreeDices()

        for (dice in dices) {
            println(dice)
        }
        return dices
    }

    override fun keepDice(dices: MutableList<Dice>): MutableList<Dice>{
        val diceToKeep: MutableList<Dice> = mutableListOf()

        var choice: String?

        do {
            println("Which dice(s) will you keep ?")
            val answer = readLine()
            println("Do you want to keep another one ? Y/N")
            choice = readLine()
            for (dice in dices) {
                if(dice.idDice ==  answer!!.toInt())
                diceToKeep.add(dice)
            }

        } while (choice!!.startsWith("y", ignoreCase = true))

        return diceToKeep
    }

    private fun rollThreeDices(): MutableList<Dice> {
        val random = Random()

        val dices: MutableList<Dice> = mutableListOf()

        val firstDice = Dice(1, random.nextInt(1..7))
        val secondDice = Dice(2, random.nextInt(1..7))
        val thirdDice = Dice(3, random.nextInt(1..7))

        dices.add(firstDice)
        dices.add(secondDice)
        dices.add(thirdDice)

        return dices
    }
}

private fun Random.nextInt(range: IntRange): Int {
    return range.start + nextInt(range.last - range.start)
}


