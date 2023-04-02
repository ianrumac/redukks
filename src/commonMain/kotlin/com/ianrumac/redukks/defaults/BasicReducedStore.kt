package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.ReducerDSL
import com.ianrumac.redukks.reducers.Reducer
import com.ianrumac.redukks.store.ReadWriteStore
import com.ianrumac.redukks.store.ReducedStore
import com.ianrumac.redukks.store.Store

/**
 * A [BasicReducedStore] is a [Store] that allows writing only through subclasses of Updated that
 * implement [Reducer] by reducing over current state.
 *
 * While the Updates generic is not necessary in itself, it is recommended to use it to ensure that
 * the updates are only of the Updates type. This helps with type safety and prevents the use of
 * incorrect updates.
 */
class BasicReducedStore<StateType, Updates : Reducer<StateType>>(
    private val store: ReadWriteStore<StateType>,
) : ReducedStore<StateType, Updates>, Store<StateType> by store {

  constructor(initial: StateType) : this(BasicStore(initial))

  @ReducerDSL
  override fun <TypeOfUpdate : Updates> update(stateUpdate: TypeOfUpdate) {
    store.update(stateUpdate.reduce(store.state))
  }
}

fun <Type, Updates : Reducer<Type>> reducedStore(initial: Type) =
    BasicReducedStore<Type, Updates>(initial)
