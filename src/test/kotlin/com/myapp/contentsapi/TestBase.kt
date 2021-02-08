package com.myapp.contentsapi

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

interface TestBase {
    @BeforeEach
    fun setup() = MockKAnnotations.init(this)
    @AfterEach
    fun tearDown() = unmockkAll()
}