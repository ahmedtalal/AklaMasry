package ahmed.javcoder.aklamasry.AuthModule

import ahmed.javcoder.aklamasry.UI.Activities.LoginActivity
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.EditText
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class RestPassword {

    companion object {
        private val auth : FirebaseAuth = FirebaseAuth.getInstance()
        fun rest(Email:EditText , context: Context){
            val email:String = Email.text.toString()
            if (email.equals("")){
                Email.error = "يجب عليك ادخال الايميل الخاص بك"
                Email.requestFocus()
            }else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener {t:Task<Void> ->
                        if (t.isSuccessful){
                            context.startActivity(Intent(context , LoginActivity::class.java))
                            (context as Activity).finish()
                        }else {
                            ToastView.createToast(context , t.exception?.message.toString())
                        }

                    }
            }

        }
    }
}