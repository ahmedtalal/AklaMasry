package ahmed.javcoder.aklamasry.Repositories

import ahmed.javcoder.aklamasry.Pojo.Products
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FavoriteRepository {

    private val mlData:MutableLiveData<ArrayList<Products>> = MutableLiveData()
    private val dbRef :DatabaseReference = FirebaseDatabase.getInstance().reference
    private val user:FirebaseUser? = FirebaseAuth.getInstance().currentUser
    companion object {
        private var repository:FavoriteRepository? = null
        fun getInstance():FavoriteRepository?{
            if (repository == null){
                repository = FavoriteRepository()
            }
            return repository
        }
    }

    fun getAllFavorites():MutableLiveData<ArrayList<Products>>{
        val list:ArrayList<Products> = ArrayList()
        dbRef.child("Favorites").child(user!!.uid).addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.i("favTego", "onCancelled: " + error.message)
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