package com.ianrumac.redukks.actions

import com.ianrumac.redukks.ActionDSL

/**
 * A [Dispatcher] is a contract that allows for the dispatching of actions, implemented by the likes
 * of [ReduxViewModel] and [DefaultActionDispatcher].
 */
interface Dispatcher<Actions> {
  @ActionDSL fun dispatch(value: Actions)
}
