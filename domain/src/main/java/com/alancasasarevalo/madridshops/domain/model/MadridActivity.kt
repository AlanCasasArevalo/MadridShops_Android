package com.alancasasarevalo.madridshops.domain.model

import android.os.Parcel
import android.os.Parcelable

data class MadridActivity(val id: Int,
                          val name: String,
                          val img: String,
                          val logoImg: String,

                          val address: String,
                          val url: String,
                          val descriptionEn: String,

                          val gpsLati: String,
                          val gpsLong: String,
                          val openingHoursEn: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(img)
        parcel.writeString(logoImg)
        parcel.writeString(address)
        parcel.writeString(url)
        parcel.writeString(descriptionEn)
        parcel.writeString(gpsLati)
        parcel.writeString(gpsLong)
        parcel.writeString(openingHoursEn)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MadridActivity> {
        override fun createFromParcel(parcel: Parcel): MadridActivity {
            return MadridActivity(parcel)
        }

        override fun newArray(size: Int): Array<MadridActivity?> {
            return arrayOfNulls(size)
        }
    }
}
