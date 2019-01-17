package model

class Dice(val idDice: Int, var value: Int) {
    override fun toString(): String {
        return "Dice : id -> $idDice value -> $value)"
    }

}