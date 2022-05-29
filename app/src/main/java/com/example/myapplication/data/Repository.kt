package com.example.myapplication.data

import com.example.myapplication.model.Municipio
import com.example.myapplication.network.RestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object Repository {
    fun updateGasolineraData() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val gasStatus = RestApi.retrofitService.getStatusInfo()
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(gasStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
    fun updateGasolineraComunidadData( idCCAA: String) =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val gasStatus = RestApi.retrofitService.getStatusInfoComunidad(idCCAA)
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(gasStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
    fun updateGasolineraProvinciaData(idProvincia: String) =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val gasStatus = RestApi.retrofitService.getStatusInfoProvincia(idProvincia)
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(gasStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
    fun updateGasolineraMunicipioData(idMunicipio: String) =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val gasStatus = RestApi.retrofitService.getStatusInfoMunicipio(idMunicipio)
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(gasStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
    fun updateGProvinciaData(idCCAA:String) =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val provStatus = RestApi.retrofitService.getProvinciaInfo(idCCAA)
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(provStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)

    fun updateMunicipioData(idProvincia:String) =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val MunStatus = RestApi.retrofitService.getMunicipiosInfo(idProvincia)
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(MunStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
    fun updateComunidadData() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val comStatus = RestApi.retrofitService.getComunidadesInfo()
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(comStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
}