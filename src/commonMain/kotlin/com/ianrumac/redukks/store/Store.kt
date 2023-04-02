package com.ianrumac.redukks.store

import com.ianrumac.redukks.StoreDSL
import kotlinx.coroutines.flow.Flow

/**
 * A basic interface for a [Store]. Stores are the main way to access state in redukks. They can be
 * used to both retrieve the current state and listen for changes to the state. If you only need to
 * listen to changes, expose only a [Store] to your consumers.
 */
interface Store<T> {
  val state: T

  @StoreDSL fun listen(): Flow<T>
}
