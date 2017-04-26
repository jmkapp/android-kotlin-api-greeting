package me.jkapp.androidkotlinapigreeting

import android.app.PendingIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class ApiGreetingActivity : AppCompatActivity()
{
    private val _apiRequestCode = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_greeting)
    }

    fun getApiMessage(view: View)
    {
        val pendingResult: PendingIntent = createPendingResult(_apiRequestCode, Intent(), 0)
        val url = getString(R.string.api_url)

        val intent = Intent(applicationContext, ApiService::class.java)
        intent.putExtra(ApiService.PENDING_RESULT_EXTRA, pendingResult)
        intent.putExtra(ApiService.URL_EXTRA, url)
        startService(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode : Int, data : Intent)
    {
        if(requestCode == _apiRequestCode)
        {
            val greeting : Greeting = data.getParcelableExtra(ApiService.API_RESULT_EXTRA)

            val message : String = greeting.greetingId + " : " + greeting.content

            val textView = findViewById(R.id.textView) as TextView
            textView.text = message
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
