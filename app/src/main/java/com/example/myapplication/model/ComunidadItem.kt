package com.example.myapplication.model

data class ComunidadItem(
    val CCAA: String,
    val IDCCAA: String
){
    override fun toString(): String {
        return this.CCAA
    }
}
