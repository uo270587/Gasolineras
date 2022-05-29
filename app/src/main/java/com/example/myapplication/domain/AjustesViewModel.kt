package com.example.myapplication.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.ApiResult
import com.example.myapplication.data.Repository
import com.example.myapplication.model.*
import com.example.myapplication.ui.ComunidadUIState
import com.example.myapplication.ui.MunicipioUIState
import com.example.myapplication.ui.ProvinciaUIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AjustesViewModel : ViewModel() {
    private val _ccaaUIStateObservable = MutableLiveData<ComunidadUIState>()
    val stopsUIStateObservable: LiveData<ComunidadUIState> get() = _ccaaUIStateObservable
    private val _provUIStateObservable = MutableLiveData<ProvinciaUIState>()
    val provUIStateObservable: LiveData<ProvinciaUIState> get() = _provUIStateObservable
    private val _munUIStateObservable = MutableLiveData<MunicipioUIState>()
    val munUIStateObservable: LiveData<MunicipioUIState> get() = _munUIStateObservable
    private val _ccaaListaObservable = MutableLiveData<List<ComunidadItem>>()
    val ccaaListaObservable: LiveData<List<ComunidadItem>> get() = _ccaaListaObservable
    private val _provListaObservable = MutableLiveData<List<ProvinciaItem>>()
    val provListaObservable: LiveData<List<ProvinciaItem>> get() = _provListaObservable
    private val _munListaObservable = MutableLiveData<List<MunicipioItem>>()
    val munListaObservable: LiveData<List<MunicipioItem>> get() = _munListaObservable



  init {
      getComunidadesList()
  }

    fun getComunidadesList() {

        viewModelScope.launch{
            Repository.updateComunidadData()
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {
                            _ccaaListaObservable.value=result.data!! as ArrayList<ComunidadItem>
                            ComunidadUIState.Success(result.data!! as ArrayList<ComunidadItem>)

                        }
                        is ApiResult.Error -> ComunidadUIState.Error("Error")
                        is ApiResult.Loaded-> ComunidadUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _ccaaUIStateObservable.value=data
                }
        }
    }
    fun getProvinciasList( idComunidad:String) {

        viewModelScope.launch{
            Repository.updateGProvinciaData(idComunidad)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {
                            _provListaObservable.value=result.data!! as ArrayList<ProvinciaItem>
                            ProvinciaUIState.Success(result.data!! as ArrayList<ProvinciaItem>)}
                        is ApiResult.Error -> ProvinciaUIState.Error("Error")
                        is ApiResult.Loaded-> ProvinciaUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _provUIStateObservable.value=data
                }
        }
    }
    fun getMunicipioList( idProvincia:String) {

        viewModelScope.launch{
            Repository.updateMunicipioData(idProvincia)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {
                            _munListaObservable.value=result.data!! as ArrayList<MunicipioItem>
                            MunicipioUIState.Success(result.data!! as ArrayList<MunicipioItem>)}
                        is ApiResult.Error -> ProvinciaUIState.Error("Error")
                        is ApiResult.Loaded-> ProvinciaUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _munUIStateObservable.value=data as MunicipioUIState
                }
        }
    }
}