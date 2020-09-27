package ahmed.javcoder.aklamasry.AuthModule


import ahmed.javcoder.aklamasry.UI.Activities.MainActivity
import ahmed.javcoder.aklamasry.UI.Activities.RegisterActivity
import ahmed.javcoder.aklamasry.UI.Activities.SplachScreen
import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class CheckCurrentUser {
    companion object {
        private val auth : FirebaseAuth = FirebaseAuth.getInstance()
        private val user =  auth.currentUser
        fun logOut(context: Context){
            auth.signOut()
            context.startActivity(Intent(context , SplachScreen::class.java))
            (context as MainActivity).finish()
        }
        fun getCurrentUser(context: Context){
            if (user != null){
                context.startActivity(Intent(context , MainActivity::class.java))
                (context as RegisterActivity).finish()
            }
        }
    }
}