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
}
