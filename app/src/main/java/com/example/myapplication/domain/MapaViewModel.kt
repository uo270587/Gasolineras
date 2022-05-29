package com.example.myapplication.domain

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.ApiResult
import com.example.myapplication.data.Repository
import com.example.myapplication.model.ListaEESSPrecio
import com.example.myapplication.ui.GasUIState
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MapaViewModel: ViewModel() {
    private val _stopsUIStateObservable = MutableLiveData<GasUIState>()
    val stopsUIStateObservable: LiveData<GasUIState> get() = _stopsUIStateObservable
    private val _listaObservable = MutableLiveData<List<ListaEESSPrecio>>()
    val listaObservable: LiveData<List<ListaEESSPrecio>> get() = _listaObservable
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init {
    }
    var combustible ="Biodiesel"

    fun getBusStopsList(){

        viewModelScope.launch{
            Repository.updateGasolineraData()
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {

                            GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })}
                        is ApiResult.Error -> GasUIState.Error("Error")
                        is ApiResult.Loaded-> GasUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _stopsUIStateObservable.value=data
                }
        }
    }
    fun getGasComunidadList(idComunidad:String){

        viewModelScope.launch{
            Repository.updateGasolineraComunidadData(idComunidad)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {

                            GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })}
                        is ApiResult.Error -> GasUIState.Error("Error")
                        is ApiResult.Loaded-> GasUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _stopsUIStateObservable.value=data
                }
        }
    }
    fun getGasProvinciaList(idProvincia:String){

        viewModelScope.launch{
            Repository.updateGasolineraProvinciaData(idProvincia)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {

                            GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })}
                        is ApiResult.Error -> GasUIState.Error("Error")
                        is ApiResult.Loaded-> GasUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _stopsUIStateObservable.value=data
                }
        }
    }
    fun getGasMinucipioList(idMunicipio:String){

        viewModelScope.launch{
            Repository.updateGasolineraMunicipioData(idMunicipio)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {

                            GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })}
                        is ApiResult.Error -> GasUIState.Error("Error")
                        is ApiResult.Loaded-> GasUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _stopsUIStateObservable.value=data
                }
        }
    }
    fun lista(list:List<ListaEESSPrecio>){
        _listaObservable.value= list
    }
    fun rellenaMapa(map: GoogleMap, list: List<ListaEESSPrecio>){
        viewModelScope.launch{
            var  sydney = LatLng(0.0,0.0)

        }
    }
}