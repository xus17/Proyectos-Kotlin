package com.example.rw3

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var num = ""
    var num2 = ""
    var resul = 0.0
    var resulmemoria = 0.0
    var mostrar = ""
    var suma = 0
    var resta = 0
    var multiplicacion = 0
    var division = 0
    var divis = 0
    var on =  0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(resources.configuration.orientation== Configuration.ORIENTATION_LANDSCAPE){
            webVista.webChromeClient = object : WebChromeClient(){}
            webVista.webViewClient = object: WebViewClient(){}
            val setting: WebSettings = webVista.settings
            setting.javaScriptEnabled=true
            webVista.loadUrl("https://www.google.com")

        }
    }
    fun iniciarcalc(view: View){
        num = ""
        num2 = ""
        mostrar = "0"
        suma = 0
        multiplicacion=0
        resta=0
        division=0
        resul = 0.0
        textView2.text = mostrar
    }
    fun cuentas(view: View){

        when(view.id) {
            R.id.b_sumar -> {
                mostrar = mostrar + " + "
                resul = resul + num2.toDouble()
                suma = 1
            }
            R.id.b_restar -> {
                mostrar = mostrar + " - "
                resul = resul - num2.toDouble()
                resta = 1
            }
            R.id.b_multiplicar -> {
                if(resul == 0.0){
                    resul = resul + num2.toDouble()
                    multiplicacion = 1
                }else{
                    multiplicacion = 1
                }
                mostrar = mostrar + " x "
            }
            R.id.b_dividir -> {
                mostrar = mostrar + " / "
                if (resul == 0.0) {
                    resul = resul + num2.toDouble()
                    division = 1
                }else{
                    division = 1
                }
            }
        }
        num2 = ""
    }
    fun resultado (view: View){
        if(suma ==1){
            resul = resul + num2.toDouble()
            textView2.text= ""+ resul
            suma = 0
        }
        if(resta ==1){
            resul = resul - num2.toDouble()
            textView2.text= ""+ resul
            resta = 0
        }
        if(multiplicacion ==1){
            resul = resul * (num2.toDouble())
            textView2.text= ""+ resul
            multiplicacion = 0
        }
        if(division ==1){
            resul = resul / (num2.toDouble())
            textView2.text= ""+ resul
            division = 0
        }
        mostrar = "" + resul
        num2 = "0"
    }

    fun valormemoria(view: View){
        if(resulmemoria == 0.0){
            resulmemoria = resulmemoria + resul
            textView2.text = ""+resulmemoria
        }else {
            resul = resul + resulmemoria
            textView2.text = ""+resulmemoria
        }
    }

    fun sumamemoria(view: View){
        resulmemoria = resulmemoria + resul
        textView2.text = ""+resulmemoria

    }

    fun memoria_cero(view: View){
        resulmemoria = resulmemoria - resulmemoria
        textView2.text = ""+resulmemoria
    }

    fun valores(view: View){
        when(view.id){
            R.id.b_one ->{
                num = num + "1"

            }
            R.id.b_two ->{
                num = num + "2"

            }
            R.id.b_three ->{
                num = num + "3"

            }
            R.id.b_four ->{
                num = num + "4"

            }
            R.id.b_five ->{
                num = num + "5"

            }
            R.id.b_six ->{
                num = num + "6"

            }
            R.id.b_seven ->{
                num = num + "7"

            }
            R.id.b_eight ->{
                num = num + "8"

            }
            R.id.b_nine ->{
                num = num + "9"

            }
            R.id.b_zero ->{
                num = num + "0"

            }

        }
        mostrar = mostrar + num
        num2 = num2 + num
        textView2.text = ""+mostrar
        num = ""
    }

    fun apagar_encender(view: View){
        finish()
    }
}
