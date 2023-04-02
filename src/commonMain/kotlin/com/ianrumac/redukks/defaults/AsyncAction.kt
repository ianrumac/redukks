package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.actions.TypedAction

open class AsyncAction<Context>(override val execute: suspend Context.() -> Unit) : TypedAction<Context>
