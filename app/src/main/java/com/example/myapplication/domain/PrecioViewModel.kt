package com.example.myapplication.domain

import android.content.res.Resources
import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.ApiResult
import com.example.myapplication.data.Repository
import com.example.myapplication.model.ListaEESSPrecio
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.example.myapplication.ui.GasUIState

class PrecioViewModel: ViewModel(){
    private val _stopsUIStateObservable = MutableLiveData<GasUIState>()
    val stopsUIStateObservable: LiveData<GasUIState> get() = _stopsUIStateObservable
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    private val _gasolineraObservable = MutableLiveData<ListaEESSPrecio>()
    val gasolineraObservable: LiveData<ListaEESSPrecio> get() = _gasolineraObservable


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
    fun getGasComunidadList(idComunidad:String, combustible:String?){

        viewModelScope.launch{
            Repository.updateGasolineraComunidadData(idComunidad)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {
                            if (combustible == null) {
                            } else {
                                //combropamos el tipo de combustible preferido del usuario y ordenamos la lissta en funcion de ese combustible
                                when(combustible){
                                    "Biodiesel" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Biodiesel })}
                                    "Bioetanol" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Bioetanol })
                                    }
                                    "Bioethanol" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Bioetanol })
                                    }
                                    "Gas natural comprimido" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido })
                                    }
                                    "Compressed natural gas" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido })
                                    }
                                    "Gas natural licuado" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Licuado })
                                    }
                                    "Liquefied natural gas" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Licuado })
                                    }
                                    "gases licuados del petróleo" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo })
                                    }
                                    "gases licuados del petróleo" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo })
                                    }
                                    "Gasoleo A" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })
                                    }
                                    "Diesel oil A" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })
                                    }
                                    "Gasoleo B" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_B })
                                    }
                                    "Diesel oil B" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_B })
                                    }
                                    "Gasoleo Premium" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_Premium })
                                    }
                                    "Premium diesel oil" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_Premium })
                                    }
                                    "Gasolina 95 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E10})
                                    }
                                    "Gasoline 95 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E10 })
                                    }
                                    "Gasolina 95 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5 })
                                    }
                                    "Gasoline 95 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5 })
                                    }
                                    "Gasolina 95 E5 premium" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium })
                                    }
                                    "Premium gasoline 95 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium })
                                    }
                                    "Gasolina 98 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E10 })
                                    }
                                    "Gasoline 98 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E10 })
                                    } "Gasolina 98 E5" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E5 })
                                }
                                    "Gasoline 98 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E5 })
                                    }
                                    "Hidrogeno" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Hidrogeno })
                                    }
                                    "Hydrogen" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Hidrogeno })
                                    }
                                    else -> GasUIState.Success(result.data?.ListaEESSPrecio!!)
                                }
                            }
                            }
                        is ApiResult.Error -> GasUIState.Error("Error")
                        is ApiResult.Loaded-> GasUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _stopsUIStateObservable.value=data as GasUIState
                }
        }
    }
    fun getGasProvinciaList(idProvincia:String, combustible:String?){

        viewModelScope.launch{
            Repository.updateGasolineraProvinciaData(idProvincia)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {if (combustible == null) {
                        } else {
                            //combropamos el tipo de combustible preferido del usuario y ordenamos la lissta en funcion de ese combustible
                            when(combustible){
                                "Biodiesel" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Biodiesel })}
                                "Bioetanol" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Bioetanol })
                                }
                                "Bioethanol" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Bioetanol })
                                }
                                "Gas natural comprimido" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido })
                                }
                                "Compressed natural gas" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido })
                                }
                                "Gas natural licuado" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Licuado })
                                }
                                "Liquefied natural gas" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Licuado })
                                }
                                "gases licuados del petróleo" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo })
                                }
                                "gases licuados del petróleo" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo })
                                }
                                "Gasoleo A" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })
                                }
                                "Diesel oil A" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })
                                }
                                "Gasoleo B" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_B })
                                }
                                "Diesel oil B" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_B })
                                }
                                "Gasoleo Premium" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_Premium })
                                }
                                "Premium diesel oil" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_Premium })
                                }
                                "Gasolina 95 E10" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E10})
                                }
                                "Gasoline 95 E10" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E10 })
                                }
                                "Gasolina 95 E5" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5 })
                                }
                                "Gasoline 95 E5" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5 })
                                }
                                "Gasolina 95 E5 premium" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium })
                                }
                                "Premium gasoline 95 E5" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium })
                                }
                                "Gasolina 98 E10" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E10 })
                                }
                                "Gasoline 98 E10" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E10 })
                                } "Gasolina 98 E5" -> {
                                GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E5 })
                            }
                                "Gasoline 98 E5" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E5 })
                                }
                                "Hidrogeno" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Hidrogeno })
                                }
                                "Hydrogen" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Hidrogeno })
                                }
                                else -> GasUIState.Success(result.data?.ListaEESSPrecio!!)
                            }
                        }
                        }
                        is ApiResult.Error -> GasUIState.Error("Error")
                        is ApiResult.Loaded-> GasUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _stopsUIStateObservable.value=data as GasUIState
                }
        }
    }
    fun getGasMinucipioList(idMunicipio:String, combustible:String?){

        viewModelScope.launch{
            Repository.updateGasolineraMunicipioData(idMunicipio)
                .map { result ->
                    when(result){
                        is ApiResult.Success -> {
                            if (combustible == null) {
                            } else {
                                //combropamos el tipo de combustible preferido del usuario y ordenamos la lissta en funcion de ese combustible
                                when(combustible){
                                    "Biodiesel" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Biodiesel })}
                                    "Bioetanol" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Bioetanol })
                                    }
                                    "Bioethanol" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Bioetanol })
                                    }
                                    "Gas natural comprimido" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido })
                                    }
                                    "Compressed natural gas" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido })
                                    }
                                    "Gas natural licuado" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Licuado })
                                    }
                                    "Liquefied natural gas" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gas_x0020_Natural_x0020_Licuado })
                                    }
                                    "gases licuados del petróleo" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo })
                                    }
                                    "liquefied petroleum gases" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo })
                                    }
                                    "Gasoleo A" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })
                                    }
                                    "Diesel oil A" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_A })
                                    }
                                    "Gasoleo B" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_B })
                                    }
                                    "Diesel oil B" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_B })
                                    }
                                    "Gasoleo Premium" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_Premium })
                                    }
                                    "Premium diesel oil" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasoleo_x0020_Premium })
                                    }
                                    "Gasolina 95 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E10})
                                    }
                                    "Gasoline 95 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E10 })
                                    }
                                    "Gasolina 95 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5 })
                                    }
                                    "Gasoline 95 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5 })
                                    }
                                    "Gasolina 95 E5 premium" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium })
                                    }
                                    "Premium gasoline 95 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium })
                                    }
                                    "Gasolina 98 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E10 })
                                    }
                                    "Gasoline 98 E10" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E10 })
                                    } "Gasolina 98 E5" -> {
                                    GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E5 })
                                }
                                    "Gasoline 98 E5" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Gasolina_x0020_98_x0020_E5 })
                                    }
                                    "Hidrogeno" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Hidrogeno })
                                    }
                                    "Hydrogen" -> {
                                        GasUIState.Success(result.data?.ListaEESSPrecio!!.sortedBy { it.Precio_x0020_Hidrogeno })
                                    }
                                    else -> GasUIState.Success(result.data?.ListaEESSPrecio!!)
                                }
                            }
                        }
                        is ApiResult.Error -> GasUIState.Error("Error")
                        is ApiResult.Loaded-> GasUIState.Cargando("Cargando")
                    }
                }
                .collect { data->
                    _stopsUIStateObservable.value=data as GasUIState
                }
        }
    }
    fun elegirGasolinra(gasolinera:ListaEESSPrecio){
        Thread.sleep(1000);
        _gasolineraObservable.value=gasolinera
    }

}

