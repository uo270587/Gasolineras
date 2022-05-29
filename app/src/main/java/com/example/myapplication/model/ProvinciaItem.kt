package com.example.myapplication.model

data class ProvinciaItem(
    val CCAA: String,
    val IDCCAA: String,
    val IDPovincia: String,
    val Provincia: String
){
    override fun toString(): String {
        return Provincia
    }
}