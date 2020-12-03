package com.example.recicledview


import android.app.AlertDialog
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.coroutines.CoroutineContext


class AdaptadorPartidos(var lista: ArrayList<Partidos>): RecyclerView.Adapter<AdaptadorPartidos.MiViewHolder>(){


    class MiViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun enlazaItems(datos:Partidos){
            val nombre1:TextView=itemView.findViewById(R.id.titulo)
            val imagen1:ImageView=itemView.findViewById(R.id.imagen)
            val b1:Button=itemView.findViewById(R.id.bINFO)
            val b2:Button=itemView.findViewById(R.id.bVotar)

            nombre1.text=datos.Nombre

            Glide.with(itemView.context).load(datos.Imagenes).into(imagen1)

            b1.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(itemView.context)
                builder.setTitle(Html.fromHtml("<font color='#FF2D00'>Informacion de ${datos.Nombre}</font>"))
                builder.setMessage("Nombre del partido = ${datos.Nombre} \r\n Descripcion del partido = ${datos.Descripcion} \r\n Dinero Invertido =  ${datos.DineroInicial} \r\n Escaños = ${datos.Escanos}")
                builder.show()
                })
            b2.setOnClickListener(View.OnClickListener {
                if (datos.Escanos != 175) {
                        datos.Escanos = datos.Escanos + 1
                        Toast.makeText(itemView.context,"Has votado a ${datos.Nombre}  y ahora sus escaños han aumentado a = ${datos.Escanos}",Toast.LENGTH_LONG).show()
                }else{
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle(Html.fromHtml("<font color='#FF2D00'>Finalizar App</font>"))
                    builder.setMessage(Html.fromHtml("<font color='#FF0000'>¡ENHORABUENA ${datos.Nombre} has ganado las elecciones!</font>"))
                    builder.setCancelable(false)
                    //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))


                    builder.setNeutralButton("Resetear") { dialog, which ->
                        Toast.makeText(itemView.context,"Se reiniciarán todos los campos", Toast.LENGTH_SHORT).show()
                        System.exit(0)
                        Toast.makeText(itemView.context,"Vuelve a abrirla", Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
                }

            })
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.contenido_item,parent,false)
        return MiViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
    ////////////MiViewHolder.ViewHolder
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.enlazaItems(lista[position])
    }


}