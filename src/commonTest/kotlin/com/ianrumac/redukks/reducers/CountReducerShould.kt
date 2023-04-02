package com.ianrumac.redukks.reducers

import com.ianrumac.redukks.CountContext
import com.ianrumac.redukks.Given
import com.ianrumac.redukks.Then
import com.ianrumac.redukks.When
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Not a test of the library, but a test of the reducer pattern. This test can be considered a
 * demonstration of how simple reducer unit testing is. Instead of complicating with mocks, you can
 * just create the state and update you want to test, then invoke the update via .reduce() and check
 * the result.
 */
class CountReducerShould {

  @Test
  fun add_to_total_when_a_number_is_added() {
    Given("A state with a count of 0") {
      val state = CountContext.State(0)
      When("An update to add 1 is invoked") {
        val newState = CountContext.Updates.Add(1).reduce(state)
        Then("The count should be 1") { assertEquals(1, newState.total) }
      }
    }
  }

  @Test
  fun substract_from_total_when_a_number_is_subtracted() {
    Given("A state with a count of 1") {
      val state = CountContext.State(1)
      When("An update to subtract 1 is invoked") {
        val newState = CountContext.Updates.Subtract(1).reduce(state)
        Then("The count should be 1") { assertEquals(0, newState.total) }
      }
    }
  }

  @Test
  fun update_total_when_a_number_update_is_requested() {
    Given("A state with a count of 1") {
      val state = CountContext.State(1)
      When("An update change total to 3 is invoked") {
        val newState = CountContext.Updates.UpdateState(3).reduce(state)
        Then("The count should be 3") { assertEquals(3, newState.total) }
      }
    }
  }
}
