package com.example.myapplication.ui

import com.example.myapplication.model.ListaEESSPrecio
import com.example.myapplication.state.AppStatus

sealed class GasUIState (val state: AppStatus) {
    data class Success(val eessPrecio: List<ListaEESSPrecio>): GasUIState(AppStatus.SUCCESS)
    data class Error (val message:String): GasUIState(AppStatus.ERROR)
    data class Cargando (val message:String): GasUIState(AppStatus.LOADED)
}
