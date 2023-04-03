package com.ianrumac.redukks

import com.ianrumac.redukks.defaults.BasicReducedStore

fun testContext(startCount: Int = 0) =
    object : CountContext {
      override val countStore: CountStore = BasicReducedStore(CountContext.State(startCount))
      override val countClient: CountClient =
          object : CountClient {
            override suspend fun add(number: Int): Int = countStore.state.total + number
            override suspend fun subtract(number: Int): Int = countStore.state.total - number
          }
    }
