package com.ianrumac.redukks.store

import com.ianrumac.redukks.StoreDSL

/**
 * An interface that designates a [Store] that can be updated with new state. This is used to allow
 * for a [Store] to be passed a [com.ianrumac.redukks.reducers.Reducer] that can update the state.
 */
interface WriteableStore<T> {

  @StoreDSL fun update(stateUpdate: T)

  @StoreDSL fun update(stateUpdate: (T) -> T)
}
