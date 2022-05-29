package com.example.myapplication

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.core.database.getStringOrNull
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentAjustesBinding
import com.example.myapplication.db.AdminSQLiteOpenHelper
import com.example.myapplication.domain.AjustesViewModel
import com.example.myapplication.model.*
import com.example.myapplication.ui.ComunidadUIState
import com.example.myapplication.ui.MunicipioUIState
import com.example.myapplication.ui.ProvinciaUIState
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AjustesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AjustesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentAjustesBinding? = null
    val AjustesViewModel: AjustesViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var  spinnerComunidad : Spinner

    private lateinit var botonComunidad :Button
    private lateinit var spinnerProvincia :Spinner
    private lateinit var botonProvincia :Button
    private lateinit var spinnerMunicipio : Spinner
    private lateinit var spinnerCombustible : Spinner
    private lateinit var botonGuardar :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        spinnerComunidad= binding.spinnerComunidad
        botonComunidad= binding.seleccionarComunidad
        spinnerProvincia= binding.spinnerProvincia
        botonProvincia= binding.seleccionarProvincia
        spinnerMunicipio= binding.spinnerMunicipio
        botonGuardar= binding.botonGuardar
        spinnerCombustible= binding.combustible
        //cargamos en los spinner los datos obtenidos de la peticion al servicio REST
        if(AjustesViewModel.ccaaListaObservable.value!= null) {
            var listaComunidad: Array<ComunidadItem> =
                AjustesViewModel.ccaaListaObservable.value!!.toTypedArray()
            val spinnerArrayAdapter: ArrayAdapter<ComunidadItem> =
                ArrayAdapter<ComunidadItem>(this.requireContext(),
                    android.R.layout.simple_spinner_item,
                    listaComunidad)
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
            //spinnerComunidad= binding.spinnerComunidad
            spinnerComunidad.adapter = spinnerArrayAdapter
        }
        if(AjustesViewModel.provListaObservable.value!= null) {
            var listaComunidad: Array<ProvinciaItem> =
                AjustesViewModel.provListaObservable.value!!.toTypedArray()
            val spinnerArrayAdapter: ArrayAdapter<ProvinciaItem> =
                ArrayAdapter<ProvinciaItem>(this.requireContext(),
                    android.R.layout.simple_spinner_item,
                    listaComunidad)
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
            spinnerProvincia.adapter = spinnerArrayAdapter
        }
        if(AjustesViewModel.munListaObservable.value!= null) {
            var listaComunidad: Array<MunicipioItem> =
                AjustesViewModel.munListaObservable.value!!.toTypedArray()
            val spinnerArrayAdapter: ArrayAdapter<MunicipioItem> =
                ArrayAdapter<MunicipioItem>(this.requireContext(),
                    android.R.layout.simple_spinner_item,
                    listaComunidad)
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
            spinnerMunicipio.adapter = spinnerArrayAdapter
        }



        AjustesViewModel.stopsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ComunidadUIState.Success -> {

                    spinnerComunidad.visibility=View.VISIBLE
                    botonComunidad.visibility= View.VISIBLE
                    spinnerProvincia.visibility=View.INVISIBLE
                    botonProvincia.visibility= View.INVISIBLE
                    spinnerMunicipio.visibility=View.INVISIBLE


                    var lista : Array<ComunidadItem> = result.comunidades.toTypedArray()
                    val spinnerArrayAdapter: ArrayAdapter<ComunidadItem> = ArrayAdapter<ComunidadItem>(this.requireContext(),android.R.layout.simple_spinner_item,lista)
                    spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
                    spinnerComunidad.adapter=spinnerArrayAdapter
                    botonComunidad.setOnClickListener {
                        //En el boton confirmar Comunidad obtenemos el id de la comunidad seleccionada y obtenermos las provincias de esa comunidad
                        val item = spinnerComunidad.selectedItem as ComunidadItem
                        GlobalScope.launch {
                            AjustesViewModel.getProvinciasList(item.IDCCAA)
                        }
                    }
                }

                is ComunidadUIState.Error -> {

                    Toast.makeText(this.requireContext(),"error",Toast.LENGTH_LONG).show()

                }
            }
        }

        AjustesViewModel.provUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ProvinciaUIState.Success -> {
                    //hacemos visibles el spinner y boton de las provincias
                    spinnerProvincia.visibility=View.VISIBLE
                    botonProvincia.visibility= View.VISIBLE
                    spinnerMunicipio.visibility= View.INVISIBLE
                    var listaProv : Array<ProvinciaItem> = result.provincias.toTypedArray()
                    val spinnerArrayAdapter: ArrayAdapter<ProvinciaItem> = ArrayAdapter<ProvinciaItem>(this.requireContext(),android.R.layout.simple_spinner_item,listaProv)
                    spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
                    spinnerProvincia.adapter=spinnerArrayAdapter

                    botonProvincia.setOnClickListener {
                        val item = spinnerProvincia.selectedItem as ProvinciaItem
                        GlobalScope.launch {
                            AjustesViewModel.getMunicipioList(item.IDPovincia)
                        }
                    }
                }
                is ProvinciaUIState.Error -> {

                    Toast.makeText(this.requireContext(),"error",Toast.LENGTH_LONG).show()

                }
            }
        }
        AjustesViewModel.munUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is MunicipioUIState.Success -> {
                    //hacemos visibles el spinner y boton de las provincias
                    spinnerMunicipio.visibility=View.VISIBLE
                    var listaMun : Array<MunicipioItem> = result.municipios.toTypedArray()
                    val spinnerArrayAdapter: ArrayAdapter<MunicipioItem> = ArrayAdapter<MunicipioItem>(this.requireContext(),android.R.layout.simple_spinner_item,listaMun)
                    spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
                    spinnerMunicipio.adapter=spinnerArrayAdapter
                }
                is MunicipioUIState.Error -> {

                    Toast.makeText(this.requireContext(),"error",Toast.LENGTH_LONG).show()

                }
            }
        }






        botonGuardar.setOnClickListener {
            //guardamos en base de datos la informacion de preferencias de los usuarios
            var admin = AdminSQLiteOpenHelper(this.requireContext(),"articulos", null, 1)
            var bd = admin.writableDatabase
            val fila = bd.rawQuery("select * from busqueda", null)
            if (fila.moveToFirst()) {
                val cant = bd.delete("busqueda", "codigo=1", null)

            }
            val registro = ContentValues()

            if(spinnerComunidad.isVisible){
                var select = spinnerComunidad.selectedItem as ComunidadItem
                var ccaa : String?= select.IDCCAA
                var provincia: String? = null
                var municipio: String? = null
                var nombreccaa : String?= select.CCAA
                var nombreprovincia: String? = null
                var nombremunicipio: String? = null
                if(spinnerProvincia.isVisible){
                    val selectProv = spinnerProvincia.selectedItem as ProvinciaItem
                    ccaa = selectProv.IDCCAA
                    provincia = selectProv.IDPovincia
                    municipio = null
                    nombreccaa = selectProv.CCAA
                    nombreprovincia = selectProv.Provincia
                    nombremunicipio = null
                    if(spinnerMunicipio.isVisible){
                        val selectMun = spinnerMunicipio.selectedItem as MunicipioItem
                        ccaa = selectMun.IDCCAA
                        provincia = selectMun.IDProvincia
                        municipio = selectMun.IDMunicipio
                        nombreccaa = selectMun.CCAA
                        nombreprovincia = selectMun.Provincia
                        nombremunicipio = selectMun.Municipio
                    }
                }
                registro.put("codigo","1")
                registro.put("idccaa",ccaa )
                registro.put("idProvincia", provincia)
                registro.put("idMUnicipio", municipio)
                registro.put("comunidad", nombreccaa)
                registro.put("provincia", nombreprovincia)
                registro.put("municipio", nombremunicipio)
                registro.put("combustible", spinnerCombustible.selectedItem as String)
                bd.insert("busqueda", null, registro)
                Toast.makeText(this.requireContext(),"Se han guardado los datos correctamente",Toast.LENGTH_LONG).show()
                imprimeOpcionSel()
            }
            else{
                Toast.makeText(this.requireContext(),"Seleccione por lo menos una Comunidad autonoma",Toast.LENGTH_LONG).show()
            }
            imprimeOpcionSel()
            bd.close()

        }
        this.imprimeOpcionSel()
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AjustesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AjustesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun imprimeOpcionSel(){
        var admin = AdminSQLiteOpenHelper(this.requireContext(),"articulos", null, 1)
        var bd = admin.writableDatabase
        val fila = bd.rawQuery("select * from busqueda", null)
        if (fila.moveToFirst()) {
            val ajustes = ajustesdb(fila.getStringOrNull(1),
                fila.getStringOrNull(2),
                fila.getStringOrNull(3),
                fila.getStringOrNull(4),
                fila.getStringOrNull(5),
                fila.getStringOrNull(6),
                fila.getStringOrNull(7))
            binding.opcion.text = "se ha seleccionado la ubicación: "+ajustes.comunidad+", "+ajustes.provincia+", "+ajustes.municipio+" y el combustible:"+ ajustes.combustible
        }
        bd.close()
    }

    override fun onResume() {
        super.onResume()

    }
}