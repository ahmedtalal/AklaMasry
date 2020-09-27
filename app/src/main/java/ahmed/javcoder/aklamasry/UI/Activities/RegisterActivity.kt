package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.AuthModule.Registeration
import ahmed.javcoder.aklamasry.AuthModule.CheckCurrentUser
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.databinding.ActivityRegisterBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class RegisterActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        CheckCurrentUser.getCurrentUser(this)
    }

    private lateinit var registerBinding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = DataBindingUtil.setContentView(this , R.layout.activity_register)


        registerBinding.registerBTNId.setOnClickListener(){
            val register  = Registeration()
            register.register(registerBinding.nameETId , registerBinding.emailETId
                , registerBinding.passwordETId , this)
        }

        registerBinding.loginLinkId.setOnClickListener(){
            startActivity(Intent(this , LoginActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}