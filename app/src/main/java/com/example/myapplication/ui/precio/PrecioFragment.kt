package com.example.myapplication.ui.precio

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.database.getStringOrNull
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.GasolineraDetalles
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPrecioBinding
import com.example.myapplication.db.AdminSQLiteOpenHelper
import com.example.myapplication.domain.PrecioViewModel
import com.example.myapplication.model.ajustesdb
import com.example.myapplication.ui.GasStatusListAdapter
import com.example.myapplication.ui.GasUIState


class PrecioFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentPrecioBinding
    private lateinit var gasStatusListAdapter:GasStatusListAdapter
    val gasStopsViewModel: PrecioViewModel by viewModels()
    private val refreshListener= SwipeRefreshLayout.OnRefreshListener {  gasStopsViewModel.getBusStopsList()}
    private lateinit var appContext :Context
    private lateinit var combustible:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPrecioBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //Obtiene contexto.


        binding.recyclerview.layoutManager= LinearLayoutManager(this.requireContext())


        gasStopsViewModel.stopsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is GasUIState.Success -> {


                    gasStatusListAdapter= GasStatusListAdapter(result.eessPrecio,combustible)


                    binding.recyclerview.adapter=gasStatusListAdapter
                    gasStatusListAdapter.setOnItemClickListener(object: GasStatusListAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val gasolinera = result.eessPrecio.get(position)
                            var admin = AdminSQLiteOpenHelper(requireContext(),"articulos", null, 1)
                            var bd = admin.writableDatabase
                            val fila = bd.rawQuery("select * from gasolinera", null)
                            if (fila.moveToFirst()) {
                                val cant = bd.delete("gasolinera", "codigo=1", null)

                            }
                            //guardamos en base de datos la gasolinera seleccionada
                            val registro = ContentValues()
                            registro.put("codigo","1")
                            registro.put("titulo",gasolinera.Rótulo )
                            registro.put("horario", gasolinera.Horario)
                            registro.put("cp", gasolinera.CP)
                            registro.put("localidad", gasolinera.Localidad)
                            registro.put("margen", gasolinera.Margen)
                            registro.put("municipio", gasolinera.Municipio)
                            registro.put("provincia", gasolinera.Provincia)
                            //registro.put("comunidad", gasolinera.)
                            registro.put("biodiesa", gasolinera.Precio_x0020_Biodiesel)
                            registro.put("bioetanol", gasolinera.Precio_x0020_Bioetanol)
                            registro.put("gasnaturalc", gasolinera.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido)
                            registro.put("gasnaturall", gasolinera.Precio_x0020_Gas_x0020_Natural_x0020_Licuado)
                            registro.put("gasespetroleo", gasolinera.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo)
                            registro.put("gasoleoa", gasolinera.Precio_x0020_Gasoleo_x0020_A)
                            registro.put("gasoleob", gasolinera.Precio_x0020_Gasoleo_x0020_B)
                            registro.put("gasoleopremium", gasolinera.Precio_x0020_Gasoleo_x0020_Premium)
                            registro.put("gasolina95E5", gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E5)
                            registro.put("gasolina95E10", gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E10)
                            registro.put("gasolina95E5Premium", gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium)
                            registro.put("gasolina98E10", gasolinera.Precio_x0020_Gasolina_x0020_98_x0020_E10)
                            registro.put("gasolina98E5", gasolinera.Precio_x0020_Gasolina_x0020_98_x0020_E5)
                            registro.put("hidrogeno", gasolinera.Precio_x0020_Hidrogeno)
                            registro.put("remision", gasolinera.Remisión)
                            registro.put("tipo_venta", gasolinera.Tipo_x0020_Venta)
                            registro.put("ester", gasolinera._x0025__x0020_Ester_x0020_metilico)
                            registro.put("por_bioetanol", gasolinera._x0025__x0020_BioEtanol)
                            registro.put("direccion", gasolinera.Dirección)
                            bd.insert("gasolinera", null, registro)
                            bd.close()


                            findNavController().navigate(R.id.action_navigation_precio_to_gasolineraDetalles)
                        }

                    })
                //  gasStatusListAdapter.submitList(result.eessPrecio)

                }
                is GasUIState.Error -> {

                    Toast.makeText(this.requireContext(),"error",Toast.LENGTH_LONG).show()

                }
            }
        }

        var admin = AdminSQLiteOpenHelper(this.requireContext(),"articulos", null, 1)
        var bd = admin.writableDatabase
        val fila = bd.rawQuery("select * from busqueda", null)
        if (fila.moveToFirst()) {
            do{
                val ajustes = ajustesdb(
                    fila.getStringOrNull(1),
                    fila.getStringOrNull(2),
                    fila.getStringOrNull(3),
                    fila.getStringOrNull(4),
                    fila.getStringOrNull(5),
                    fila.getStringOrNull(6),
                    fila.getStringOrNull(7)
                )
                //guardamos el combustiblw para poder pasarlo como parametro
                combustible= ajustes.combustible!!
                if (ajustes.idmunicipio==null){
                    if(ajustes.idprovincia==null){
                        if(ajustes.idccaa== null){
                            gasStopsViewModel.getBusStopsList()
                        }
                        else {
                           //llamamos al filtrado por la comunidad autonoma
                            gasStopsViewModel.getGasComunidadList(ajustes.idccaa,ajustes.combustible)
                        }
                    }
                    else{
                        //llamamos al metodo de obtener segun el municipio
                        gasStopsViewModel.getGasProvinciaList(ajustes.idprovincia,ajustes.combustible)
                    }
                }else{
                    //llamamos al metodo de obtener segun el municipio
                    gasStopsViewModel.getGasMinucipioList(ajustes.idmunicipio,ajustes.combustible)
                }
            }while(fila.moveToNext())


        }
        else {
            gasStopsViewModel.getBusStopsList()
        }




        //Refresh Swipe

    }
    fun leeBase(){

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

}