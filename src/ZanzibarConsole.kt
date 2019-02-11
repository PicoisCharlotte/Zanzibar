import model.Dice
import model.Player
import model.Score
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

        do {
            println("Which dice(s) will you change ?")
            val answer = readLine()
            if(answer != "" && answer != " ") {
                println("Do you want to change another one ? Y/N")
                choice = readLine()
                for (dice in dices) {
                    if (dice.idDice == answer!!.toInt()) {
                        diceToChange.add(dice)
                    }
                }
            } else {
                println("Please, choose a correct answer")
            }

        } while (choice!!.startsWith("y", ignoreCase = true))


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

        println("Do you want to rolling dice again? Y/N")
        choice = readLine()!!

        return when(choice.toUpperCase()) {
            "Y" -> true
            "N" -> false
            else -> false
        }
    }

    override fun makeScoreValueCorrespondance(dices: MutableList<Dice>) {
        //print(dices[0].value)
        //print(dices[1].value)
        //print(dices[2].value)
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
}

private fun Random.nextInt(range: IntRange): Int {
    return range.start + nextInt(range.last - range.start)
}


