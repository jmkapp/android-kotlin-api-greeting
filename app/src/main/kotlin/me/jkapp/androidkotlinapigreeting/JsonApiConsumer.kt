package me.jkapp.androidkotlinapigreeting

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

internal class JsonApiConsumer(url : String)
{
    private val _url : String = url

    fun readJsonFeed() : String
    {
        val stringBuilder = StringBuilder()

        try
        {
            val url = URL(_url)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.setRequestProperty("Accept", "application/json")

            try
            {
                urlConnection.connect()

                if(urlConnection.responseCode == HttpURLConnection.HTTP_OK)
                {
                    val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                    var output : String? = reader.readLine()
                    while (output != null)
                    {
                        stringBuilder.append(output)
                        output = reader.readLine()
                    }
                }
            }
            finally
            {
                urlConnection.disconnect()
            }
        }
        catch (mue : MalformedURLException)
        {
            return "Malformed URL";
        }
        catch (ioe : IOException)
        {
            return "IOException ocurred";
        }

        return stringBuilder.toString()
    }
}