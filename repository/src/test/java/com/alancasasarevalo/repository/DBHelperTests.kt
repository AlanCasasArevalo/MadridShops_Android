package com.alancasasarevalo.repository

import org.junit.Test

import com.alancasasarevalo.repository.db.convertBooleanToInt
import org.junit.Assert.*

class DBHelperTests {
    @Test
    @Throws(Exception::class)
    fun given_false_converts_into_0() {
        assertEquals(0, convertBooleanToInt(false).toLong())
    }

   @Test
    @Throws(Exception::class)
    fun given_false_converts_into_1() {
        assertEquals(1, convertBooleanToInt(true).toLong())
    }

}