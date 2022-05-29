package com.example.myapplication.ui

import com.example.myapplication.model.Provincia
import com.example.myapplication.model.ProvinciaItem

import com.example.myapplication.state.AppStatus

sealed class ProvinciaUIState (val state: AppStatus) {
    data class Success(val provincias: ArrayList<ProvinciaItem>): ProvinciaUIState(AppStatus.SUCCESS)
    data class Error (val message:String): ProvinciaUIState(AppStatus.ERROR)
    data class Cargando (val message:String): ProvinciaUIState(AppStatus.LOADED)
}
