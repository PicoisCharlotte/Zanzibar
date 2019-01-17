package main

import ZanzibarPresenter
import java.util.*

fun main(args: Array<String>) {
//    val one = MyData("one", "one")
//    val two = MyData("one", "two")
//
//    val set = HashSet<MyData>()
//    set.add(one)
//    set.add(two)
//
//    println(set)

    val zanzibarPresenter = ZanzibarPresenter()

    zanzibarPresenter.start()
}

//class MyData(val id: String, val name: String) {
//
//    override fun toString(): String {
//        return "MyData(id='$id', name='$name')"
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as MyData
//
//        if (id != other.id) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return id.hashCode()
//    }
//}