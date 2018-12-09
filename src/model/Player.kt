package model

data class Player (val name: String? = "", val score: Int = 0) {
    override fun toString(): String {
        return "$name : $score point"
    }
}

