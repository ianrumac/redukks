package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.store.ReadWriteStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

open class StateFlowStore<T>(initial: T) : ReadWriteStore<T> {

  private val stateFlow = MutableStateFlow(initial)

  private val sharedFlow
    get() = stateFlow.asSharedFlow()

  override val state: T
    get() = stateFlow.value

  override fun listen(): Flow<T> = sharedFlow

  override fun update(stateUpdate: (T) -> T) {
    stateFlow.tryEmit(stateUpdate(state))
  }

  override fun update(stateUpdate: T) {
    stateFlow.tryEmit(stateUpdate)
  }
}
