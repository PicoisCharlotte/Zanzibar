package model

data class Game (val players: MutableList<Player>, val scoreToReach: Int, val round: Int = 0)