package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.*
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test of the [TypedActionHandler] implementation. It should run actions inside a CoroutineScope,
 * capturing a Context during execution time.
 */
class TypedActionHandlerShould {

  @Test
  fun dispatch_actions_with_context() = runTest {
    Given("A context with a count of 0") {
      val context = testContext()
      val handler = actionHandlerFor(context, this)
      When("An action to add 1 is dispatched") {
        handler.dispatch(CountContext.Actions.Add(1))
        runCurrent()
        Then("The count should be 1") {
          val count = context.countStore.state.total
          assertEquals(1, count)
        }
      }
    }
  }
}
