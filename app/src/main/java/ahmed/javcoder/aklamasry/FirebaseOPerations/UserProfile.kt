package ahmed.javcoder.aklamasry.FirebaseOPerations

import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.UI.Activities.MainActivity
import ahmed.javcoder.aklamasry.ViewModels.UsersViewModel
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.gms.tasks.Task
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfile {

    companion object {
        private val firebaseDB : FirebaseDatabase = FirebaseDatabase.getInstance()
        private val databaseRef : DatabaseReference = firebaseDB.reference

        fun createProfile(name:String , email:String , context: Context , userId:String){
            val userMap :HashMap<String , String> = HashMap()
            userMap.put("name" , name)
            userMap.put("email" , email)

            databaseRef.child("Users").child(userId).setValue(userMap)
                .addOnCompleteListener { T:Task<Void>->
                    if (T.isSuccessful){
                        context.startActivity(Intent(context , MainActivity::class.java))
                        (context as Activity).finish()
                    }else {
                        Toast.makeText(context , "هناك خطأ في عمليه حفظ معلومات عن المستخدم" , Toast.LENGTH_LONG).show()
                    }
                }
        }

        fun getUserPofile(navigation:NavigationView , context: Context){
            val email:TextView = navigation.getHeaderView(0).findViewById(R.id.email_id)
            val name:TextView = navigation.getHeaderView(0).findViewById(R.id.userName_id)
            val model:UsersViewModel = ViewModelProvider(context as ViewModelStoreOwner).get(UsersViewModel::class.java)
            model.init()
            model.getProducts()?.observe(context as LifecycleOwner , Observer { hash:HashMap<String, String> ->
                email.text = hash["email"]
                name.text = hash["name"]
            })
        }
    }

}