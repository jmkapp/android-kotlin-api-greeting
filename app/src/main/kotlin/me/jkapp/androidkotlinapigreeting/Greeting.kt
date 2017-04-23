package me.jkapp.androidkotlinapigreeting

import android.os.Parcel
import android.os.Parcelable

data class Greeting(val greetingId : String, val content : String) : Parcelable
{

    constructor(source : Parcel) : this(source.readString(), source.readString())

    override fun describeContents(): Int
    {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int)
    {
        dest.writeString(greetingId)
        dest.writeString(content)
    }

    companion object
    {
        @JvmField final val CREATOR: Parcelable.Creator<Greeting> = object : Parcelable.Creator<Greeting>
        {
            override fun createFromParcel(source: Parcel): Greeting
            {
                return Greeting(source)
            }

            override fun newArray(size: Int): Array<Greeting?>
            {
                return arrayOfNulls(size)
            }
        }
    }
}
