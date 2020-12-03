package com.example.mapsx

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.content.ActivityNotFoundException
import android.speech.RecognizerIntent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T








class MainActivity : AppCompatActivity() {


    private val RECOGNIZE_SPEECH_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, "")
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            return true

        }
        if (id == R.id.action_settings1) {
            val webpage = Uri.parse("http://www.google.com")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(webIntent)
            return true

        }

        return super.onOptionsItemSelected(item)
    }


    fun mapas(view: View) {
        val texto = etTexto.text
        if(!texto.isEmpty()){
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("geo:0,0?q=+" + texto!!)
            startActivity(i)
        }else{
            Toast.makeText(this,"Debes escribir una dirección",Toast.LENGTH_LONG).show()
        }

    }


    fun andando(view: View) {
        val texto = etTexto.text
        if(!texto.isEmpty()){
            val ruta = "w"
            val gmmIntentUri = Uri.parse("google.navigation:q=+$texto&mode=$ruta")
            val i = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(i)
        }else {
            Toast.makeText(this, "Debes escribir una dirección", Toast.LENGTH_LONG).show()
        }

    }

    fun bici(view: View) {
        val texto = etTexto.text
        if(!texto.isEmpty()){
            val ruta = "b"
            val gmmIntentUri = Uri.parse("google.navigation:q=+$texto&mode=$ruta")
            val i = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(i)
        }else {
            Toast.makeText(this, "Debes escribir una dirección", Toast.LENGTH_LONG).show()
        }
    }

    fun coche(view: View) {
        val texto = etTexto.text
        if(!texto.isEmpty()){
            val ruta = "c"
            val gmmIntentUri = Uri.parse("google.navigation:q=+$texto&mode=$ruta")
            val i = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(i)
        }else {
            Toast.makeText(this, "Debes escribir una dirección", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RECOGNIZE_SPEECH_ACTIVITY -> if (resultCode == Activity.RESULT_OK && null != data) {
                val speech = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val strSpeech2Text = speech!![0]
                etTexto.setText(strSpeech2Text)
            }
            else -> {
            }
        }
    }

    fun micro(v: View) {
        val intentActionRecognizeSpeech = Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        )
        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX"
        )
        try {
            startActivityForResult(
                intentActionRecognizeSpeech,
                RECOGNIZE_SPEECH_ACTIVITY
            )
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext,
                "Tú dispositivo no soporta el reconocimiento por voz",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}
