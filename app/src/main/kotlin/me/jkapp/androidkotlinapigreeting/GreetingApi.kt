package me.jkapp.androidkotlinapigreeting

import retrofit2.Call
import retrofit2.http.GET

interface GreetingApi
{
    @GET("/greeting")
    fun getGreeting() : Call<Greeting>
}
