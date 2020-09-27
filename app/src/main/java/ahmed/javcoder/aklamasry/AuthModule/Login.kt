package ahmed.javcoder.aklamasry.AuthModule

import ahmed.javcoder.aklamasry.UI.Activities.DashboardActivity
import ahmed.javcoder.aklamasry.UI.Activities.MainActivity
import ahmed.javcoder.aklamasry.ViewsFactory.ProgressDialogo
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.EditText
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class Login {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email:EditText , password:EditText , context: Context ) {
        val gemail = email.text.toString()
        val pass = password.text.toString()
        if (gemail.equals("")){
            email.error = "هناك خطأ في البريد الالكتروني"
            email.requestFocus()
            return
        }
        if (pass.equals("") || pass.length < 8){
            password.error = "الرقم السري غير صحيح او يجب ان يتجاوز 8 حروف"
            password.requestFocus()
            return
        }
        val dialogo = ProgressDialogo.createProgressDialog(context , "الرجاء الانتظار......" )

        if (gemail.equals("ahmedtalal@gmail.com") && pass.equals("closebook")){
            context.startActivity(Intent(context , DashboardActivity::class.java))
            (context as Activity).finish()
        }else {
            auth.signInWithEmailAndPassword(gemail , pass)
                .addOnCompleteListener { t:Task<AuthResult> ->
                    if (t.isSuccessful){
                        dialogo.dismiss()
                        context.startActivity(Intent(context , MainActivity::class.java))
                        (context as Activity).finish()
                    }else {
                        ToastView.createToast(context , "هناك خطأ في عمليه التسجبل اعد المحاوله")
                        dialogo.dismiss()
                    }
                }
        }
    }
}