package me.jkapp.androidkotlinapigreeting

import org.junit.Test

import org.junit.Assert.*

class JsonParserTest
{
    @Test
    @Throws(Exception::class)
    fun jsonParser_works()
    {
        val testString : String = "{\"greetingId\":7,\"content\":\"Hello, World!\"}"

        val parser = JsonParser(testString)

        val greeting : Greeting = parser.getGreeting()

        assertEquals("7", greeting.greetingId)
        assertEquals("Hello, World!", greeting.content)
    }
}