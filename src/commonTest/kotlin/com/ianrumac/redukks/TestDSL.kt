package com.ianrumac.redukks

@DslMarker annotation class TestingDSL

@TestingDSL
inline fun Given(what: String, block: () -> Unit) {
  println("\nGiven $what")
  block()
}

@TestingDSL
inline fun <T> When(what: String, block: () -> T): T {
  println("\tWhen $what")
  return block()
}

@TestingDSL
inline fun Then(what: String, block: () -> Unit) {
  println("\t\tThen $what")
  block()
}

@TestingDSL
inline fun And(what: String, block: () -> Unit) {
  println("\t\t\tAnd $what")
  block()
}
