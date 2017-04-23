package me.jkapp.androidkotlinapigreeting

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.MapType
import java.io.IOException

internal class JsonParser(json : String)
{
    private val _values = HashMap<String, String>();

    init
    {
        val mapper = ObjectMapper()
        val type : MapType = mapper.typeFactory.constructMapType(
                Map::class.java, String::class.java, String::class.java)

        try
        {
            val data : Map<String, String> = mapper.readValue(json, type)
            _values.putAll(data)
        }
        catch(ioe : IOException)
        {
            // do nothing, leave values map empty
        }
    }

    fun getGreeting() : Greeting
    {
        return Greeting(_values["greetingId"] ?: "", _values["content"] ?: "")
    }
}
