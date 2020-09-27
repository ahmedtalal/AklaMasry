package ahmed.javcoder.aklamasry.Repositories

import ahmed.javcoder.aklamasry.Pojo.Products
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class MonacapatRepository {
    private val mlData:MutableLiveData<ArrayList<Products>> = MutableLiveData()
    private val dbRef:DatabaseReference = FirebaseDatabase.getInstance().reference
    companion object {
        private var allPro:MonacapatRepository? = null
        fun getInstance():MonacapatRepository?{
            if (allPro == null){
                allPro = MonacapatRepository()
            }
            return allPro
        }
    }

    fun getAllMonacapat(type:String):MutableLiveData<ArrayList<Products>>{
        val list:ArrayList<Products> = ArrayList()
        list.clear()
        dbRef.child("Monacapat").child(type).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.i("TAG", "onCancelled: " + error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapShot:DataSnapshot in snapshot.children){
                    list.add(snapShot.getValue(Products::class.java)!!)
                }
                mlData.value = list
            }
        })
        return mlData
    }
}