package com.example.recicledview

    import android.app.AlertDialog
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import android.widget.Button
    import android.widget.Toast
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.google.gson.GsonBuilder
    import kotlinx.android.synthetic.main.activity_main.*
    import okhttp3.*
    import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var tarjetaAdapter : AdaptadorPartidos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jsonAdaptador()

    }

    fun jsonAdaptador() {

        val url = "http://iesayala.ddns.net/xus17/php/partidos.php"
        val request = Request.Builder().url(url).build()
        val cliente = OkHttpClient()
        cliente.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val json = response?.body()?.string()
                println(json)

                val gson = GsonBuilder().create()

                var lista = gson.fromJson(json, PartidosArray::class.java)
                println(lista.partidos)

                runOnUiThread {
                    recycler.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        tarjetaAdapter = AdaptadorPartidos(lista.partidos!!)
                        adapter = tarjetaAdapter
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fallo")
            }


        })
    }

}
