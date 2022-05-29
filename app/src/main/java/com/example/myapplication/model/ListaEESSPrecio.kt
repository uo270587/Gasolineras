package com.example.myapplication.model

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.Json

data class ListaEESSPrecio (
    @Json(name ="C.P.")val CP: String,
    val Dirección: String,
    val Horario: String,
    val IDCCAA: String,
    val IDEESS: String,
    val IDMunicipio: String,
    val IDProvincia: String,
    val Latitud: String,
    val Localidad: String,
    @Json(name ="Longitud (WGS84)")val Longitud_x0020__x0028_WGS84_x0029_: String,
    val Margen: String,
    val Municipio: String,
    @Json(name ="Precio Biodiesel")val Precio_x0020_Biodiesel: String,
    @Json(name ="Precio Bioetanol")val Precio_x0020_Bioetanol: String,
    @Json(name ="Precio Gas Natural Comprimido")val Precio_x0020_Gas_x0020_Natural_x0020_Comprimido: String,
    @Json(name ="Precio Gas Natural Licuado") val Precio_x0020_Gas_x0020_Natural_x0020_Licuado: String,
    @Json(name ="Precio Gases licuados del petróleo")val Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo: String,
    @Json(name ="Precio Gasoleo A")val Precio_x0020_Gasoleo_x0020_A: String,
    @Json(name ="Precio Gasoleo B")val Precio_x0020_Gasoleo_x0020_B: String,
    @Json(name ="Precio Gasoleo Premium")val Precio_x0020_Gasoleo_x0020_Premium: String,
    @Json(name ="Precio Gasolina 95 E10")val Precio_x0020_Gasolina_x0020_95_x0020_E10: String,
    @Json(name ="Precio Gasolina 95 E5") val Precio_x0020_Gasolina_x0020_95_x0020_E5: String,
    @Json(name ="Precio Gasolina 95 E5 Premium")val Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium: String,
    @Json(name ="Precio Gasolina 98 E10")val Precio_x0020_Gasolina_x0020_98_x0020_E10: String,
    @Json(name ="Precio Gasolina 98 E5")val Precio_x0020_Gasolina_x0020_98_x0020_E5: String,
    @Json(name ="Precio Hidrogeno")val Precio_x0020_Hidrogeno: String,
    val Provincia: String,
    val Remisión: String,
    val Rótulo: String,
    @Json(name ="Tipo Venta") val Tipo_x0020_Venta: String,
    @Json(name ="% BioEtanol")val _x0025__x0020_BioEtanol: String,
    @Json(name ="% Éster metílico") val _x0025__x0020_Ester_x0020_metilico: String
){
    companion object DIFF_CALLBACK: DiffUtil.ItemCallback<ListaEESSPrecio>() {
        override fun areItemsTheSame(oldItem: ListaEESSPrecio, newItem: ListaEESSPrecio): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: ListaEESSPrecio, newItem: ListaEESSPrecio): Boolean {
            return oldItem == newItem
        }
    }

}