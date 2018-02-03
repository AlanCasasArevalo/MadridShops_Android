package com.alancasasarevalo.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

//Se deberia hacer un parseo de ShopEntity para que dependiendo de que tipo de
//idioma tenga el cliente se parsee a su idioma

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopEntity (val id : Long,
                       val dataBaseId: Long,
                       val name: String,
                       val description_es:String,
                       val gps_lat: Float,
                       val gps_lon: Float,
                       val img: String,
                       val logo_img: String,
                       val opening_hours_es: String,
                       val address: String,
                       val description_en:String = "",
                       val description_jp:String = "",
                       val description_cn:String = "",
                       val description_cl:String = "",
                       val description_mx:String = "",
                       val opening_hours_en: String = "",
                       val opening_hours_cn: String = "",
                       val opening_hours_jp: String = "",
                       val opening_hours_cl: String = "",
                       val opening_hours_mx: String = ""
                        )

