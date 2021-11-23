package com.myproject.trello.models

import android.os.Parcel
import android.os.Parcelable
/*
//data class holds only data information
//adding a parcelable implementation makes data class parcelable by which the data can be used any where in application
*/
 data class User (
    val id: String = "",
    var name: String = "",
    val email: String = "",
    val image: String = "",
    val mobilenum: Long = 0,
    val fcmToken: String = ""
):Parcelable {
     constructor(parcel: Parcel) : this(
         parcel.readString()!!,
         parcel.readString()!!,
         parcel.readString()!!,
         parcel.readString()!!,
         parcel.readLong(),
         parcel.readString()!!
     ) {
     }

     override fun describeContents()=0

     override fun writeToParcel(dest:Parcel,flags:Int)= with(dest) {
        writeString(id)
        writeString(name)
        writeString(email)
        writeString(image)
        writeLong(mobilenum)
        writeString(fcmToken)
     }

     companion object CREATOR : Parcelable.Creator<User> {
         override fun createFromParcel(parcel: Parcel): User {
             return User(parcel)
         }

         override fun newArray(size: Int): Array<User?> {
             return arrayOfNulls(size)
         }
     }
 }