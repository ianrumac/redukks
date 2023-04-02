package com.ianrumac.redukks.defaults

import com.ianrumac.redukks.CountContext.State
import com.ianrumac.redukks.Given
import com.ianrumac.redukks.Then
import com.ianrumac.redukks.When
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test of the [BasicStore] implementation. It is a state-flow backed store that can be updated via
 * .update and .update {} methods.
 */
class BasicStoreShould {

  @Test
  fun update_state_when_direct_method_is_invoked() = runTest {
    Given("A store with a count of 0") {
      val state = State(0)
      val store = BasicStore(state)
      When("The store is updated to a new state with a count of 2") { store.update(State(2)) }
      Then("The count should be 2") { assertEquals(2, store.state.total) }
    }
  }

  @Test
  fun update_state_when_a_closure_is_invoked() = runTest {
    Given("A store with a count of 0") {
      val state = State(0)
      val store = BasicStore(state)
      When("The store is updated with a closure returning state with a count of 2") {
        store.update { State(2) }
      }
      Then("The count should be 2") { assertEquals(2, store.state.total) }
    }
  }
}
