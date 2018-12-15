import model.Game
import model.Player

class ZanzibarConsole(zanzibarPresenter: ZanzibarPresenter) : ZanzibarView {
    override fun welcomeGame() {
        println("Welcome to Zanzibar !")
    }

    override fun getNumberOfPlayer(): Int {
        var number: Int
        do {
            println("How many players will join ? (min 2)")
            val playerNumber: String? = readLine()
            number = playerNumber?.toInt() ?: 2

        } while (number < 2)

        println("$number players will play")

        return number
    }

    override fun createPlayers(numberPlayer: Int): MutableList<Player> {
        val players: MutableList<Player> = mutableListOf()

        for (i in 1..numberPlayer) {
            println("Enter name of player n°$i")
            val name: String? = readLine()

            val player = Player(name, 0)

            players.add(player)
        }
        println("List of players $players")

        return players
    }

    override fun fixScoreToReach(): Int {
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


}
