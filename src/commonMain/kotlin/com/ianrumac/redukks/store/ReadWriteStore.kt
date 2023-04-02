package com.ianrumac.redukks.store

/** A [Store] that can be updated with new state. */
interface ReadWriteStore<T> : Store<T>, WriteableStore<T>
