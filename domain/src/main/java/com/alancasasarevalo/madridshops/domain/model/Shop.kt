package com.alancasasarevalo.madridshops.domain.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
* Shop: Represent one Shop
* */
data class Shop(val id: Int,
                val name: String,
                val img: String,
                val logoImg: String,
                val address: String,
                val url: String,
                val telephone: String,
                val email: String,
                val descriptionEn: String,
                val gpsLati: String,
                val gpsLong: String,
                val openingHoursEn: String,
                val keywordsE: String
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
        parcel.writeString(telephone)
        parcel.writeString(email)
        parcel.writeString(descriptionEn)
        parcel.writeString(gpsLati)
        parcel.writeString(gpsLong)
        parcel.writeString(openingHoursEn)
        parcel.writeString(keywordsE)
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

@SuppressLint("ParcelCreator")
@Parcelize
class Shops(var shops: MutableList<Shop>) : Aggregate <Shop>, Parcelable {
    override fun count(): Int {
        return shops.size
    }

    override fun all(): List<Shop> {
        return shops
    }

    override fun get(position: Int): Shop {
        return shops[position]
    }

    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }

}











