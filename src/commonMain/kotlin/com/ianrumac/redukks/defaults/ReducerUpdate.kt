package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.reducers.Reducer

abstract class ReducerUpdate<State>(override val reduce: (State) -> State) : Reducer<State>
