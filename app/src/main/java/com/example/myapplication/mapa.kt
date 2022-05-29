package com.example.myapplication

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.database.getStringOrNull
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentMapaBinding
import com.example.myapplication.databinding.FragmentPrecioBinding
import com.example.myapplication.db.AdminSQLiteOpenHelper
import com.example.myapplication.domain.MapaViewModel
import com.example.myapplication.domain.PrecioViewModel
import com.example.myapplication.model.GasolineraX
import com.example.myapplication.model.ListaEESSPrecio
import com.example.myapplication.model.ajustesdb
import com.example.myapplication.ui.GasStatusListAdapter
import com.example.myapplication.ui.GasUIState

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.*

class mapa : Fragment() {
    private lateinit var binding: FragmentMapaBinding
    private lateinit var list :List<ListaEESSPrecio>
    val gasStopsViewModel: MapaViewModel by viewModels()
    private val callback = OnMapReadyCallback { googleMap ->
        gasStopsViewModel.listaObservable.observe(viewLifecycleOwner) { result ->
            //limpiamos el mapa por si tiene algun marcador
            //recorremos la lista y pintamos los objetos
            googleMap.clear()
            var i = 0
            while(i<result.size) {

                var gasolinera = result.get(i)
                var punto = LatLng(gasolinera.Latitud.replace(",",
                    ".").toDouble(),
                    gasolinera.Longitud_x0020__x0028_WGS84_x0029_.replace(",", ".").toDouble())
                googleMap.addMarker(MarkerOptions().position(punto)
                    .title(gasolinera.Rótulo))

                googleMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(punto, 18f),
                    4000,
                    null
                )
                i= i+1
            }



            Log.println(Log.INFO,null, "los puntos se han cargado correctamente")

        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var admin = AdminSQLiteOpenHelper(this.requireContext(),"articulos", null, 1)
        var bd = admin.writableDatabase
        val fila = bd.rawQuery("select * from busqueda", null)
        if (fila.moveToFirst()) {
            do{
                //obtenemos de base de datos las preferencias de usuario para saber a que metodo llamar
                val ajustes = ajustesdb(fila.getStringOrNull(1),
                    fila.getStringOrNull(2),
                    fila.getStringOrNull(3),
                    fila.getStringOrNull(4),
                    fila.getStringOrNull(5),
                    fila.getStringOrNull(6),fila.getStringOrNull(7))
                if (ajustes.idmunicipio==null){
                    if(ajustes.idprovincia==null){
                        if(ajustes.idccaa== null){
                            gasStopsViewModel.getBusStopsList()
                        }
                        else {
                            //llamamos al filtrado por la comunidad autonoma
                            gasStopsViewModel.getGasComunidadList(ajustes.idccaa)
                        }
                    }
                    else{
                        //llamamos al metodo de obtener segun el municipio
                        gasStopsViewModel.getGasProvinciaList(ajustes.idprovincia)
                    }
                }else{
                    //llamamos al metodo de obtener segun el municipio
                    gasStopsViewModel.getGasMinucipioList(ajustes.idmunicipio)
                }
            }while(fila.moveToNext())


        }
        else {
            gasStopsViewModel.getBusStopsList()
        }
        gasStopsViewModel.stopsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is GasUIState.Success -> {

                    gasStopsViewModel.lista(result.eessPrecio)
                }
                is GasUIState.Error -> {

                    Toast.makeText(this.requireContext(),"error", Toast.LENGTH_LONG).show()

                }
            }
        }
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    fun cargaDatos(map:GoogleMap,list: List<ListaEESSPrecio>){


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}