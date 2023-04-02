package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.actions.Dispatcher
import com.ianrumac.redukks.actions.TypedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * [TypedActionHandler] is a class that allows you to dispatch and execute async actions inside a
 * context. This is useful when you want to dispatch actions from within an action handler, without
 * having to rely on an infinite suspending stream.
 *
 * @param context The Context to invoke actions in. It should provide all the dependencies needed
 *   for the action to execute. The dependencies are only captured during execution of the closure.
 * @param scope The scope to use for the action dispatcher
 */
class TypedActionHandler<Context, ActionType : TypedAction<Context>>(
    private val context: Context,
    private val scope: CoroutineScope
) : Dispatcher<ActionType> {

  private fun dispatchActionInContext(value: TypedAction<Context>) {
    with(this) { scope.launch { value.execute(context) } }
  }

  override fun dispatch(value: ActionType) {
    dispatchActionInContext(value)
  }
}

fun <Context,Actions: TypedAction<Context>> actionHandlerFor(
    context: Context,
    scope: CoroutineScope
): TypedActionHandler<Context,Actions> = TypedActionHandler(context, scope)
