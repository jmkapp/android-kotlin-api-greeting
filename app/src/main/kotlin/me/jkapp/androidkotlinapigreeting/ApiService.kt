package me.jkapp.androidkotlinapigreeting

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiService : IntentService(_name)
{
    companion object
    {
        private val _name : String = ApiService::class.java.simpleName
        val PENDING_RESULT_EXTRA : String = "pending_result"
        val URL_EXTRA : String = "url"
        val API_RESULT_EXTRA : String = "greeting"
        val RESULT_CODE : Int = 0
    }

    override fun onHandleIntent(intent: Intent)
    {
        val reply : PendingIntent = intent.getParcelableExtra(PENDING_RESULT_EXTRA)

        val retrofit = Retrofit.Builder()
                .baseUrl(intent.getStringExtra(URL_EXTRA))
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        val greetingApi = retrofit.create(GreetingApi::class.java)

        val callResponse : Call<Greeting> = greetingApi.getGreeting()
        val response = callResponse.execute()

        if(response.isSuccessful)
        {
            val greeting: Greeting = response.body()
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
}

