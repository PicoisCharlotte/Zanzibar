import model.Dice
import model.Player
import model.Score
import java.lang.Double.parseDouble
import java.util.*

class ZanzibarConsole: ZanzibarView {

    override fun welcomeGame() {
        println("Welcome to Zanzibar !")
    }

    override fun createPlayers(): MutableList<Player> {
        val numberPlayer = askNumberOfPlayer()
        val players: MutableList<Player> = mutableListOf()

        for (i in 1..numberPlayer) {
            do {
                println("Enter name of player n°$i")
                val name: String? = readLine()
                val player = Player(name, 0)
                players.add(player)

            } while (!checkIfInputIsEmpty(name))
        }
        return players
    }

    private fun askNumberOfPlayer(): Int {
        var number: Int = 0

        do {
            println("How many players will join ? (min 2)")
            val playerNumber: String? = readLine()
            var numeric = checkStringIsNumeric(playerNumber)

            if (!numeric)
                println("Please choose a number of player")

            if (checkIfInputIsEmpty(playerNumber) && numeric) {
                number = playerNumber?.toInt() ?: 2

            }

        } while (number < 2)

        println("$number players will play")

        return number
    }

    override fun askScoreToReach(): Int {
        var scoreToReach: Int = 0
        var choice: String? = null
        var score: String?

        do {
            println("Now, you have to decide score to reach to win :")
            score = readLine()
            val numeric = checkStringIsNumeric(score)
            if (!numeric) {
                println("Please choose a correct score")
            }

            if(checkIfInputIsEmpty(score) && numeric) {
                scoreToReach = score?.toInt() ?: 2
            }

            if(scoreToReach < 2) {
                println("Score can't be under 2")
            }else{
                println("Score fixed at $scoreToReach. Do you valid this score? Y/N")
                choice = readLine()
            }
        } while (scoreToReach < 2
                || choice!!.startsWith("n", true)
                || !checkIfInputIsEmpty(choice)
                || !checkIfInputIsEmpty(scoreToReach.toString())
        )
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
        playersOrder = players.sortedBy { it.score }.toMutableList()
        return playersOrder.asReversed()
    }

    override fun resetScore(players: MutableList<Player>): MutableList<Player> {
        for(player in players){
            player.score = 0
        }
        return players
    }

    override fun firstRound(player: Player): MutableList<Dice> {
        val dices: MutableList<Dice> = rollThreeDices()

        return dices
    }

    override fun changeDice(dices: MutableList<Dice>): MutableList<Dice>{
        val diceToChange: MutableList<Dice> = mutableListOf()

        var choice: String? = ""
        var numeric: Boolean

        do {
            println("Which dice(s) will you change ?")
            val answer = readLine()
            numeric = checkStringIsNumeric(answer)

            if (!numeric) {
                println("Please select correct dice number")
            }

            if(checkIfInputIsEmpty(answer) && numeric) {
                println("Do you want to change another one ? Y/N")
                choice = readLine()
                for (dice in dices) {
                    if (dice.idDice == answer!!.toInt()) {
                        diceToChange.add(dice)
                    }
                }
            }
        } while (choice!!.startsWith("y", ignoreCase = true)
                || !checkStringIsNumeric(answer)
        )

        return diceToChange
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

    override fun rollDice(diceToChange: MutableList<Dice>, diceToKeep: MutableList<Dice>, player: Player): MutableList<Dice> {
        val random = Random()

        for(dice in diceToChange){
            dice.value = random.nextInt(1..7)
        }
        return diceToKeep
    }

    override fun askToContinueRound(): Boolean {
        val choice: String?

        println("Do you want to roll dice again? Y/N")
        choice = readLine()!!

        return when(choice.toUpperCase()) {
            "Y" -> true
            "N" -> false
            else -> false
        }
    }

    override fun getScore(dices: MutableList<Dice>): Int {
        var score = 0
        if(dices[0].value == dices[1].value && dices[1].value == dices[2].value){
            when(dices[1].value){
                1 -> score = Score.ACES.value
                2 -> score = Score.TWO.value
                3 -> score = Score.THREE.value
                4 -> score = Score.FOUR.value
                5 -> score = Score.FIVE.value
                6 -> score = Score.SIX.value
                else -> score = 0
            }
        }
        return score
    }

    override fun compareScore(players: MutableList<Player>): Int {
        var i = 1
        var score = 0
        while(i < players.size){
            if(players.get(i).score > score){
                score = players.get(i).score
            }
            i++
        }
        return score
    }
}

private fun checkIfInputIsEmpty(str: String?): Boolean {
    if(str!!.trim() != "" && str != " ") {
        return true
    }
    println("Please choose a correct answer")
    return false
}

private fun checkStringIsNumeric(str: String?): Boolean{
    val numeric: Boolean = try {
        parseDouble(str)
        true
    }catch (e: NumberFormatException) {
        false
    }

    return numeric
}

private fun Random.nextInt(range: IntRange): Int {
    return range.start + nextInt(range.last - range.start)
}