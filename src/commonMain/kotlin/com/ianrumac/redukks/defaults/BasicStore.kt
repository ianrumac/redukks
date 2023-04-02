package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.StoreDSL
import com.ianrumac.redukks.reducers.Reducer
import com.ianrumac.redukks.store.ReadWriteStore
import com.ianrumac.redukks.store.Store

/**
 * A [BasicStore] is a [Store] that allows writing only through typed classes implementing [Reducer]
 * by reducing over current state.
 */
class BasicStore<StateType>(
    private val store: ReadWriteStore<StateType>,
) : ReadWriteStore<StateType> by store {

  constructor(initial: StateType) : this(StateFlowStore(initial))

  @StoreDSL
  override fun update(stateUpdate: StateType) {
    store.update(stateUpdate)
  }

  @StoreDSL
  override fun update(stateUpdate: (StateType) -> StateType) {
    store.update(stateUpdate)
  }
}
