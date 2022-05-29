package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.database.getStringOrNull
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentGasolineraDetallesBinding
import com.example.myapplication.databinding.FragmentPrecioBinding
import com.example.myapplication.db.AdminSQLiteOpenHelper
import com.example.myapplication.domain.PrecioViewModel
import com.example.myapplication.model.ListaEESSPrecio
import com.example.myapplication.model.MunicipioItem
import com.example.myapplication.model.ajustesdb
import com.example.myapplication.model.gasolinera
import com.example.myapplication.ui.MunicipioUIState

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GasolineraDetalles.newInstance] factory method to
 * create an instance of this fragment.
 */
class GasolineraDetalles : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val gasStopsViewModel: PrecioViewModel by viewModels()
    private val titulo by lazy{binding.titulo}
    private val direccion by lazy{binding.direccion}
    private val cp by lazy{binding.cp}
    private val horario by lazy{binding.titulo}
    private val localidad by lazy{binding.localidad}
    private val margen by lazy{binding.margen}
    private val municipio by lazy{binding.municipio}
    private val provincia by lazy{binding.provincia}
    private val comunidad by lazy{binding.comunidad}
    private val biodiesel by lazy{binding.biodiesel}
    private val bioetanol by lazy{binding.bioetanol}
    private val gas_natural_c by lazy{binding.gasNaturalC}
    private val gas_natural_l by lazy{binding.gasNaturalL}
    private val gases_petroleo by lazy{binding.gasesPetroleo}
    private val gasoleo_a by lazy{binding.gasoleoA}
    private val gasoleo_b by lazy{binding.gasoleoB}
    private val gasoleo_premium by lazy{binding.gasoleoPremium}
    private val gasolina95E5 by lazy{binding.gasolina95E5}
    private val gasolina95E10 by lazy{binding.gasolina95e10}
    private val gasolina95E5Premium by lazy{binding.gasolina95E5Premium}
    private val gasolina98E10 by lazy{binding.gasolina98E10}
    private val gasolina98E5 by lazy{binding.gasolina98E5}
    private val hidrogeno by lazy{binding.hidrogeno}
    private val remision by lazy{binding.remision}
    private val tipo_venta by lazy{binding.tipoVenta}
    private val ester by lazy{binding.ester}
    private val por_bioetanol by lazy{binding.porBioetanol}
    private lateinit var result:ListaEESSPrecio
    private lateinit var binding: FragmentGasolineraDetallesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        binding= FragmentGasolineraDetallesBinding.inflate(inflater)
           // val result = gasStopsViewModel.gasolineraObservable.value
        var admin = AdminSQLiteOpenHelper(requireContext(),"articulos", null, 1)
        var bd = admin.writableDatabase
        lateinit var ajustes: gasolinera
        val fila = bd.rawQuery("select * from gasolinera", null)
        if (fila.moveToFirst()) {
            do{
                 ajustes = gasolinera(
                    fila.getStringOrNull(1),
                    fila.getStringOrNull(2),
                    fila.getStringOrNull(3),
                    fila.getStringOrNull(4),
                    fila.getStringOrNull(5),
                    fila.getStringOrNull(6),
                    fila.getStringOrNull(7),
                    fila.getStringOrNull(8),
                    fila.getStringOrNull(9),
                    fila.getStringOrNull(10),
                    fila.getStringOrNull(11),
                    fila.getStringOrNull(12),
                    fila.getStringOrNull(13),
                    fila.getStringOrNull(14),
                    fila.getStringOrNull(15),
                    fila.getStringOrNull(16),
                    fila.getStringOrNull(17),
                    fila.getStringOrNull(18),
                    fila.getStringOrNull(19),
                    fila.getStringOrNull(20),
                    fila.getStringOrNull(21),
                    fila.getStringOrNull(22),
                    fila.getStringOrNull(23),
                    fila.getStringOrNull(24),
                    fila.getStringOrNull(25),
                    fila.getStringOrNull(26)

                )

            }while(fila.moveToNext())


        }
        if (ajustes!=null) {
            var valor = cp.text.toString() + ajustes.CP
            cp.text = valor
            titulo.text=  titulo.text.toString() +" "+ ajustes.Rótulo
            direccion.text=direccion.text.toString()+" " + ajustes.Dirección
            horario.text = horario.text.toString() +" "+ ajustes.Horario
            localidad.text = localidad.text.toString()+" " + ajustes.Localidad
            margen.text = margen.text.toString() +" "+ ajustes.Margen
            municipio.text = municipio.text.toString()+" " + ajustes.Municipio
            provincia.text = provincia.text.toString() +" "+ ajustes.Provincia
            // comunidad.text = comunidad.text + result.
            biodiesel.text = biodiesel.text.toString() +" "+ ajustes.Precio_x0020_Biodiesel
            bioetanol.text = bioetanol.text.toString() +" "+ ajustes.Precio_x0020_Bioetanol
            gas_natural_c.text =
                gas_natural_c.text.toString() +" "+ ajustes.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido
            gas_natural_l.text =
                gas_natural_l.text.toString() +" "+ ajustes.Precio_x0020_Gas_x0020_Natural_x0020_Licuado
            gases_petroleo.text =
                gases_petroleo.text.toString() +" "+ ajustes.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo
            gasoleo_a.text = gasoleo_a.text.toString() +" "+ ajustes.Precio_x0020_Gasoleo_x0020_A
            gasoleo_b.text = gasoleo_b.text.toString() +" "+ ajustes.Precio_x0020_Gasoleo_x0020_B
            gasoleo_premium.text =
                gasoleo_premium.text.toString() +" "+" "+ ajustes.Precio_x0020_Gasoleo_x0020_Premium
            gasolina95E5.text =
                gasolina95E5.text.toString() +" "+ ajustes.Precio_x0020_Gasolina_x0020_95_x0020_E5
            gasolina95E10.text =
                gasolina95E10.text.toString() +" "+ ajustes.Precio_x0020_Gasolina_x0020_95_x0020_E10
            gasolina95E5Premium.text =
                gasolina95E5Premium.text.toString() +" "+ ajustes.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium
            gasolina98E10.text =
                gasolina98E10.text.toString() +" "+ ajustes.Precio_x0020_Gasolina_x0020_98_x0020_E5
            gasolina98E5.text =
                gasolina98E5.text.toString() +" "+ ajustes.Precio_x0020_Gasolina_x0020_98_x0020_E10
            hidrogeno.text = hidrogeno.text.toString() +" "+ ajustes.Precio_x0020_Hidrogeno
            remision.text = remision.text.toString()+" " + ajustes.Remisión
            tipo_venta.text = tipo_venta.text.toString()+" " + ajustes.Tipo_x0020_Venta
            ester.text = ester.text.toString()+" " + ajustes._x0025__x0020_Ester_x0020_metilico
            por_bioetanol.text = por_bioetanol.text.toString() +" "+ ajustes._x0025__x0020_BioEtanol
        }

        bd.close()


        return binding.root

        }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GasolineraDetalles.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GasolineraDetalles().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}