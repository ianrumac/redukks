package com.ianrumac.redukks.actions

import com.ianrumac.redukks.*
import com.ianrumac.redukks.CountContext.State
import com.ianrumac.redukks.defaults.AsyncAction
import com.ianrumac.redukks.defaults.BasicStore
import com.ianrumac.redukks.defaults.TypedActionHandler
import com.ianrumac.redukks.store.ReadWriteStore
import com.ianrumac.redukks.store.Store
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Not a test of the library, but a test of the action pattern. This test can be considered a
 * demonstration of how simple action unit testing is. Since they are nothing but suspending
 * closures, they can be tested in the same way as any other basic function.
 */
class CountActionsShould {

  @Test
  fun add_number_when_executed() = runTest {
    Given("A context with a count of 0") {
      val context = testContext(0)
      When("An action to add 1 is executed") {
        CountContext.Actions.Add(1).execute(context)
        Then("The count should be 1") {
          val count = context.countStore.state.total
          assertEquals(1, count)
        }
      }
    }
  }

  @Test
  fun subtract_number_when_executed() = runTest {
    Given("A context with a count of 1") {
      val context = testContext(1)
      When("An action to subtract 1 is executed") {
        CountContext.Actions.Subtract(1).execute(context)
        Then("The count should be 0") {
          val count = context.countStore.state.total
          assertEquals(0, count)
        }
      }
    }
  }

  @Test
  fun add_number_from_client() = runTest {
    Given("A context with a count of 1") {
      val context = testContext(1)
      When("An action to add to client is executed") {
        CountContext.Actions.AddToClient(1).execute(context)

        Then("The count should be 2") {
          val count = context.countStore.state.total
          assertEquals(2, count)
        }
      }
    }
  }

  @Test
  fun subtract_number_from_client() = runTest {

    Given("A context with a count of 4") {
      val context = testContext(4)
      When("An action to subtract from client is executed") {
        CountContext.Actions.subtract(2).execute(context)
        Then("The count should be 2") {
          val count = context.countStore.state.total
          assertEquals(2, count)
        }
      }
    }
  }
}
