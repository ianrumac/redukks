package com.ianrumac.redukks.actions

interface TypedAction<Context> {
  val execute: suspend Context.() -> Unit
}
