package br.dipievil.androidadvaps2

import android.util.Log
import br.dipievil.androidadvaps2.model.Count
import com.google.firebase.firestore.FirebaseFirestore

class DbHandler(db : FirebaseFirestore) {

    private var TAG = "DbHandler"
    private lateinit var _db : FirebaseFirestore

    init{
        _db = db
    }

    fun getCountValue() : Long{
        var count : Long = 0
        val doc = _db.collection(COLLECTION_NAME).document(DOCUMENT_PATH)
        doc.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    count = document.data?.getValue("count") as Long
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return count
    }

    fun saveCount(count : String){
        var countDoc = Count(count.toLong())

        _db.collection(COLLECTION_NAME).document(DOCUMENT_PATH)
            .set(countDoc)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    companion object {
        const val COLLECTION_NAME = "clicks"
        const val DOCUMENT_PATH = "FwnIyRAhfWUxHXbiaO2t"
    }
}