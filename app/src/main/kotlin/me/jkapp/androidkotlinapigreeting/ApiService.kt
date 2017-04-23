package me.jkapp.androidkotlinapigreeting

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.util.Log

class ApiService : IntentService(_name)
{
    companion object
    {
        private val _name : String = ApiService::class.java.simpleName
        val PENDING_RESULT_EXTRA : String = "pending_result"
        val URL_EXTRA : String = "url"
        val API_RESULT_EXTRA : String = "url"
        val RESULT_CODE : Int = 0
    }

    override fun onHandleIntent(intent: Intent)
    {
        val reply : PendingIntent = intent.getParcelableExtra(PENDING_RESULT_EXTRA)

        val consumer = JsonApiConsumer(intent.getStringExtra(URL_EXTRA))
        val parser = JsonParser(consumer.readJsonFeed())

        val greeting : Greeting = parser.getGreeting()

        val result = Intent()
        result.putExtra(API_RESULT_EXTRA, greeting)

        try
        {
            reply.send(this, RESULT_CODE, result)
        }
        catch(ce : PendingIntent.CanceledException)
        {
            Log.i(_name, "reply cancelled", ce)
        }
    }
}
