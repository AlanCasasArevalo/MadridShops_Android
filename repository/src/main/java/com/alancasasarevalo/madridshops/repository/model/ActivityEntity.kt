package com.alancasasarevalo.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ActivityEntity (val id : Long,
                       val dataBaseId: Long,
                       val name: String,
                       @JsonProperty("description_en") val description:String,
                       @JsonProperty("gps_lat") val latitude: String,
                       @JsonProperty("gps_lon") val longitude: String,
                       val img: String,
                       @JsonProperty("logo_img") val logo: String,
                       val opening_hours_es: String,
                       val address: String,
                       val description_es:String = "",
                       val description_jp:String = "",
                       val description_cn:String = "",
                       val description_cl:String = "",
                       val description_mx:String = "",
                       @JsonProperty("openingHours")val openingHours: String = "",
                       val opening_hours_cn: String = "",
                       val opening_hours_jp: String = "",
                       val opening_hours_cl: String = "",
                       val opening_hours_mx: String = ""
)