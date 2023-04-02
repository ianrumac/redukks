package com.ianrumac.redukks.actions

import com.ianrumac.redukks.ActionDSL
import kotlinx.coroutines.Deferred

/**
 * A [AsyncDispatcher] is a contract that allows for the dispatching of actions, implemented by the
 * likes of [com.ianrumac.redukks.defaults.TypedActionHandler].
 */
interface AsyncDispatcher<Actions> {
  @ActionDSL suspend fun dispatch(value: Actions): Deferred<Unit>
}
