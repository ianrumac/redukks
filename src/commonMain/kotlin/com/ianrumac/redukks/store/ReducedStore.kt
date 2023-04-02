package com.ianrumac.redukks.store

import com.ianrumac.redukks.reducers.Reducer

/**
 * A [ReducedStore] is a [Store] that allows writing only through typed classes implementing
 * [Reducer] by reducing over current state.
 */
interface ReducedStore<StateType, Updates : Reducer<StateType>> : Store<StateType> {
  fun <T : Updates> update(stateUpdate: T)
}
