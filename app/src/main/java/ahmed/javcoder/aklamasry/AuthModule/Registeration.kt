package ahmed.javcoder.aklamasry.AuthModule

import ahmed.javcoder.aklamasry.FirebaseOPerations.UserProfile
import ahmed.javcoder.aklamasry.ViewsFactory.ProgressDialogo
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.content.Context
import android.widget.EditText
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class Registeration{
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    fun register(name:EditText , email:EditText , password:EditText , context: Context){
        val userName = name.text.toString()
        val gemail = email.text.toString()
        val pass = password.text.toString()
        if (userName.equals("")){
            name.error = "يجب عليك ادخال الاسم"
            name.requestFocus()
            return
        }
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

        auth.createUserWithEmailAndPassword(gemail , pass)
            .addOnCompleteListener { T:Task<AuthResult>->
                if (T.isSuccessful){
                    UserProfile.createProfile(userName , gemail , context , T.result!!.user!!.uid)
                }else {
                    ToastView.createToast(context , "هناك خطأ في عمليه التسجبل اعد المحاوله" )
                    dialogo.dismiss()
                }
            }
    }

}