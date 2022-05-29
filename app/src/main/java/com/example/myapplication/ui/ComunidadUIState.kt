package com.example.myapplication.ui

import com.example.myapplication.model.Comunidad
import com.example.myapplication.model.ComunidadItem
import com.example.myapplication.state.AppStatus

sealed class ComunidadUIState (val state: AppStatus) {
    data class Success(val comunidades: ArrayList<ComunidadItem>): ComunidadUIState(AppStatus.SUCCESS)
    data class Error (val message:String): ComunidadUIState(AppStatus.ERROR)
    data class Cargando (val message:String): ComunidadUIState(AppStatus.LOADED)
}
