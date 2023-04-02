package com.ianrumac.redukks.reducers

/**
 * A [Reducer] is a function that takes the current state and an action and returns a new state. It
 * is a pure function that has no side effects. It is defined as an interface so that we are forced
 * to define the function when we define the update class and don't forget to handle the transition.
 *
 * @param State The type of the state we are reducing
 * @property reduce The function that takes the current state and returns a new one i.e: sealed
 *   class Updates(override val block: (State) -> State) : Reducers<State> {
 *
 *   object RemoveOne : Updates({ state -> state.copy(count = state.count - 1) })
 *
 *   object AddOne : Updates({ state -> state.copy(count = state.count + 1) }) }
 */
interface Reducer<State> {
  val reduce: (State) -> State
}
