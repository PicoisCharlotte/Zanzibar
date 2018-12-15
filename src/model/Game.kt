package model

data class Game (val players: MutableList<Player>, val scoreToReach: Int) {
    override fun toString(): String {
        return "players : $players, score to reach : $scoreToReach"
    }
}