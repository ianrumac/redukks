package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.CountContext
import com.ianrumac.redukks.Given
import com.ianrumac.redukks.Then
import com.ianrumac.redukks.When
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BasicReducedStoreShould {

  @Test
  fun update_state_inside_reduced_store() = runTest {
    Given("A store with a count of 0") {
      val state = CountContext.State(0)
      val store = reducedStore(state)
      When("An update to add 1 is dispatched") {
        val update = CountContext.Updates.Add(1)
        store.update(update)
      }
      Then("The count should be 1") { assertEquals(1, store.state.total) }
    }
  }
}
