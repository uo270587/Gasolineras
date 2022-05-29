package com.example.myapplication.ui


import com.example.myapplication.model.Municipio
import com.example.myapplication.model.MunicipioItem
import com.example.myapplication.state.AppStatus

sealed class MunicipioUIState (val state: AppStatus) {
    data class Success(val municipios: ArrayList<MunicipioItem>): MunicipioUIState(AppStatus.SUCCESS)
    data class Error (val message:String): MunicipioUIState(AppStatus.ERROR)
    data class Cargando (val message:String): MunicipioUIState(AppStatus.LOADED)
}
