package com.example.myapplication.model

data class MunicipioItem(
    val CCAA: String,
    val IDCCAA: String,
    val IDMunicipio: String,
    val IDProvincia: String,
    val Municipio: String,
    val Provincia: String
){
    override fun toString(): String {
        return Municipio
    }
}