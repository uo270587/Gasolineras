package com.example.myapplication.model

import com.squareup.moshi.Json

data class gasolinera(
                      val Rótulo: String?,
                      val Horario: String?,
                      @Json(name ="C.P.")val CP: String?,
                      val Localidad: String?,
                      val Margen: String?,
                      val Municipio: String?,
                      val Provincia: String?,
                      @Json(name ="Precio Biodiesel")val Precio_x0020_Biodiesel: String?,
                      @Json(name ="Precio Bioetanol")val Precio_x0020_Bioetanol: String?,
                      @Json(name ="Precio Gas Natural Comprimido")val Precio_x0020_Gas_x0020_Natural_x0020_Comprimido: String?,
                      @Json(name ="Precio Gas Natural Licuado") val Precio_x0020_Gas_x0020_Natural_x0020_Licuado: String?,
                      @Json(name ="Precio Gases licuados del petróleo")val Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo: String?,
                      @Json(name ="Precio Gasoleo A")val Precio_x0020_Gasoleo_x0020_A: String?,
                      @Json(name ="Precio Gasoleo B")val Precio_x0020_Gasoleo_x0020_B: String?,
                      @Json(name ="Precio Gasoleo Premium")val Precio_x0020_Gasoleo_x0020_Premium: String?,
                      @Json(name ="Precio Gasolina 95 E5") val Precio_x0020_Gasolina_x0020_95_x0020_E5: String?,
                      @Json(name ="Precio Gasolina 95 E10")val Precio_x0020_Gasolina_x0020_95_x0020_E10: String?,
                      @Json(name ="Precio Gasolina 95 E5 Premium")val Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium: String?,
                      @Json(name ="Precio Gasolina 98 E10")val Precio_x0020_Gasolina_x0020_98_x0020_E10: String?,
                      @Json(name ="Precio Gasolina 98 E5")val Precio_x0020_Gasolina_x0020_98_x0020_E5: String?,
                      @Json(name ="Precio Hidrogeno")val Precio_x0020_Hidrogeno: String?,
                      val Remisión: String?,
                      @Json(name ="Tipo Venta") val Tipo_x0020_Venta: String?,
                      @Json(name ="% Éster metílico") val _x0025__x0020_Ester_x0020_metilico: String?,
                      @Json(name ="% BioEtanol")val _x0025__x0020_BioEtanol: String?,

                      val Dirección: String?,
                      ) {
}