package model

data class Player (val name: String? = "", var score: Int = 0) {
    override fun toString(): String {
        return "$name : $score points \n"
    }
}



