package com.ianrumac.redukks

import com.ianrumac.redukks.actions.TypedAction
import com.ianrumac.redukks.defaults.AsyncAction
import com.ianrumac.redukks.reducers.Reducer
import com.ianrumac.redukks.store.ReducedStore

interface CountClient {
  suspend fun add(number: Int): Int
  suspend fun subtract(number: Int): Int
}

typealias CountStore = ReducedStore<CountContext.State, CountContext.Updates>

/**
 * A context provides a way to group related actions and state together. It also provides a way to
 * define the context in which actions can be executed, so that the closure can access the context's
 * properties during execution time.
 */
interface CountContext {

  val countStore: ReducedStore<State, Updates>
  val countClient: CountClient

  data class State(val total: Int)

  // Or, if you prefer a sealed class:
  sealed class Updates(override val reduce: (State) -> State) : Reducer<State> {
    class Add(private val number: Int) : Updates({ state -> State(total = state.total + number) })

    class Subtract(private val number: Int) :
        Updates({ state -> State(total = state.total - number) })

    class UpdateState(private val newState: Int) : Updates({ State(newState) })
  }

  sealed class Actions(override val execute: suspend CountContext.() -> Unit) :
      TypedAction<CountContext> {

    data class Add(private val number: Int) : Actions({ countStore.update(Updates.Add(number)) })

    data class Subtract(private val number: Int) :
        Actions({ countStore.update(Updates.Subtract(number)) })

    data class AddToClient(private val newState: Int) :
        Actions({
          val addResult = countClient.add(newState)
          countStore.update(Updates.UpdateState(addResult))
        })

    /** Example usage of [AsyncAction] to implement an asynchronous action without using a type. */
    companion object {
      fun subtract(number: Int) =
          AsyncAction<CountContext> {
            val subtractResult = countClient.subtract(number)
            countStore.update(Updates.UpdateState(subtractResult))
          }
    }
  }
}
