package com.example.myapplication.ui

import android.content.Context
import android.os.Build.VERSION_CODES.M
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ListaEESSPrecio
import com.example.myapplication.databinding.ListItemBinding


class GasStatusViewHolder (itemView: View, listener:GasStatusListAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

    val itemBinding = ListItemBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

   fun bind(gasolinera: ListaEESSPrecio,combustible :String) {
        itemBinding.rotulo.text = gasolinera.Rótulo.toString()
       if (combustible == null) {
       } else {
           //combropamos el tipo de combustible preferido del usuario y ordenamos la lissta en funcion de ese combustible
           when(combustible){
               "Biodiesel" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Biodiesel.toString()
               }
               "Bioetanol" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Biodiesel.toString()
               }
               "Bioethanol" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Biodiesel.toString()
               }
               "Gas natural comprimido" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido.toString()
               }
               "Compressed natural gas" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gas_x0020_Natural_x0020_Comprimido.toString()
               }
               "Gas natural licuado" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gas_x0020_Natural_x0020_Licuado.toString()
               }
               "Liquefied natural gas" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gas_x0020_Natural_x0020_Licuado.toString()
               }
               "gases licuados del petróleo" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo.toString()
               }
               "liquefied petroleum gases" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gases_x0020_licuados_x0020_del_x0020_petróleo.toString()
                   }
               "Gasoleo A" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasoleo_x0020_A.toString()
               }
               "Diesel oil A" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasoleo_x0020_A.toString()
               }
               "Gasoleo B" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasoleo_x0020_B.toString()
               }
               "Diesel oil B" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasoleo_x0020_B.toString()
               }
               "Gasoleo Premium" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasoleo_x0020_Premium.toString()
               }
               "Premium diesel oil" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasoleo_x0020_Premium.toString()
               }
               "Gasolina 95 E10" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E10.toString()
               }
               "Gasoline 95 E10" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E10.toString()
               }
               "Gasolina 95 E5" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E5.toString()
               }
               "Gasoline 95 E5" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E5.toString()
               }
               "Gasolina 95 E5 premium" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium.toString()
                   }
               "Premium gasoline 95 E5" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_95_x0020_E5_x0020_Premium.toString()
                   }
               "Gasolina 98 E10" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_98_x0020_E10.toString()
                   }
               "Gasoline 98 E10" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_98_x0020_E10.toString()

               } "Gasolina 98 E5" -> {
               itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_98_x0020_E5.toString()
           }
               "Gasoline 98 E5" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Gasolina_x0020_98_x0020_E5.toString()
               }
               "Hidrogeno" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Hidrogeno.toString()
               }
               "Hydrogen" -> {
                   itemBinding.precio.text = "El precio del " + combustible+" es: "+gasolinera.Precio_x0020_Hidrogeno.toString()
               }
           }
       }


    }
}