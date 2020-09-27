package ahmed.javcoder.aklamasry.FirebaseOPerations

import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.content.Context
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddToFavorite : AddingInterface{
    private var favImage:ImageView
    private var context:Context
    private val dbRef:DatabaseReference = FirebaseDatabase.getInstance().reference
    private val user:FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private var hashMap:HashMap<String , String>

    constructor(favImage: ImageView , context: Context , hashMap: HashMap<String , String>) {
        this.favImage = favImage
        this.context = context
        this.hashMap = hashMap
    }


    override fun checkViews(): Boolean {
        if (favImage.getTag().equals("unfav")){
            return true
        }
        return false
    }
    override fun addOPeration() {
        if (checkViews() == true){
            adding(hashMap , context)
        }else {
            deleting(hashMap["id"].toString())
        }
    }

    private fun deleting(id: String) {
        dbRef.child("Favorites").child(user!!.uid).child(id).setValue(null)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    ToastView.createToast(context , "تم الحذف من المفضله")
                }else {
                    ToastView.createToast(context , "يوجد خطأ في العمليه")
                }
            }
    }

    private fun adding(hashMap: HashMap<String, String>, context: Context) {
        dbRef.child("Favorites").child(user!!.uid).child(hashMap["id"].toString()).setValue(hashMap)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    ToastView.createToast(context , "تم الاضافه في المفضله")
                }else {
                    ToastView.createToast(context , "يوجد خطأ في العمليه")
                }
            }
    }


}