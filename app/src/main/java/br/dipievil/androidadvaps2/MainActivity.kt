package br.dipievil.androidadvaps2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore


private lateinit var TAG : String

class MainActivity : AppCompatActivity() {

    private lateinit var db : FirebaseFirestore
    private lateinit var dbHandler : DbHandler
    private lateinit var etCounter : TextView
    private lateinit var btnClear : Button
    private lateinit var btnPlus : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCounter = findViewById(R.id.etCounter)
        btnPlus = findViewById(R.id.btnPlus)
        btnClear = findViewById(R.id.btnClear)

        TAG = "MainActivity"

        db = Firebase.firestore
        dbHandler = DbHandler(db);

        etCounter.text = getFromDb()

        btnPlus.setOnClickListener{
            increaseCount()
        }

        btnClear.setOnClickListener{
            clearCount()
        }
    }

    private fun getFromDb() : String{
        return dbHandler.getCountValue().toString()
    }

    private fun saveToDb(count : String){
        dbHandler.saveCount(count)
    }

    private fun increaseCount(){
        var number = etCounter.text.toString().toLong()
        number++
        etCounter.text = number.toString()
        saveToDb(number.toString())
    }

    private fun clearCount(){
        etCounter.text = "0"
        saveToDb("0")
    }
}