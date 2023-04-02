package com.ianrumac.redukks

/** Makes reading Redux actions and updates easier, by marking them in the code with a DSL marker */
@DslMarker annotation class ActionDSL

@DslMarker annotation class ReducerDSL

@DslMarker annotation class StoreDSL
