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
        val API_RESULT_EXTRA : String = "url"
        val RESULT_CODE : Int = 0
    }

    private val _greetingApi : GreetingApi
    private val _url : String = "http://jsbr.us-west-2.elasticbeanstalk.com"

    init
    {
        val retrofit = Retrofit.Builder()
                .baseUrl(_url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        _greetingApi = retrofit.create(GreetingApi::class.java)
    }

    override fun onHandleIntent(intent: Intent)
    {
        val reply : PendingIntent = intent.getParcelableExtra(PENDING_RESULT_EXTRA)

        val callResponse : Call<Greeting> = _greetingApi.getGreeting()
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

