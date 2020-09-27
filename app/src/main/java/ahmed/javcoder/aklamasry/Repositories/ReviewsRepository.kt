package ahmed.javcoder.aklamasry.Repositories

import ahmed.javcoder.aklamasry.Pojo.reviews
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class ReviewsRepository {
    private val mlData: MutableLiveData<ArrayList<reviews>> = MutableLiveData()
    private val dbRef : DatabaseReference = FirebaseDatabase.getInstance().reference
    companion object {
        private var repository:ReviewsRepository? = null
        fun getInstance():ReviewsRepository?{
            if (repository == null){
                repository = ReviewsRepository()
            }
            return repository
        }
    }

    fun getAllReviews(productId:String): MutableLiveData<ArrayList<reviews>> {
        val list:ArrayList<reviews> = ArrayList()
        dbRef.child("Feedbacks").child(productId).addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.i("reviewoTego", "onCancelled: " + error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapShot: DataSnapshot in snapshot.children){
                    list.add(snapShot.getValue(reviews::class.java)!!)
                }
                mlData.value = list
            }
        })
        return mlData
    }
}