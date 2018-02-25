package com.alancasasarevalo.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

//Se deberia hacer un parseo de ShopEntity para que dependiendo de que tipo de
//idioma tenga el cliente se parsee a su idioma

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopEntity (val id: Long,
                       val dataBaseId: Long,
                       val name: String,
                       val img: String,
                       @JsonProperty("logo_img") val logoImg: String,

                       val address: String,
                       val url: String,
                       @JsonProperty("description_en") val description:String,

                       @JsonProperty("gps_lat") val latitude: String,
                       @JsonProperty("gps_lon") val longitude: String,
                       @JsonProperty("opening_hours_en")val openingHours: String = ""
)






























