package ahmed.javcoder.aklamasry.Repositories

import ahmed.javcoder.aklamasry.Pojo.UserModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class UserProfileRepository {
    private val mlData:MutableLiveData<HashMap<String , String>> = MutableLiveData()
    private val dbRef:DatabaseReference = FirebaseDatabase.getInstance().reference
    private val user:FirebaseUser? = FirebaseAuth.getInstance().currentUser

    companion object {
        private var allPro:UserProfileRepository? = null
        fun getInstance():UserProfileRepository?{
            if (allPro == null){
                allPro = UserProfileRepository()
            }
            return allPro
        }
    }

    fun getAllProducts():MutableLiveData<HashMap<String , String>>{
        dbRef.child("Users").child(user!!.uid).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.i("TAG", "onCancelled: " + error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val userModel:UserModel = snapshot.getValue(UserModel::class.java)!!
                val hashMap:HashMap<String , String> = HashMap()
                hashMap.put("email" , userModel.getEmail().toString())
                hashMap.put("name" , userModel.getName().toString())
                mlData.value = hashMap
            }
        })
        return mlData
    }
}