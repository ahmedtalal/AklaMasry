package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.AuthModule.Login
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.databinding.ActivityLoginBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = DataBindingUtil.setContentView(this , R.layout.activity_login)

        loginBinding.loginBTNId.setOnClickListener {
            val login = Login()
            login.signIn(loginBinding.loEmailETId , loginBinding.LoPasswordETId , this)
        }

        loginBinding.registerLinkId.setOnClickListener {
            startActivity(Intent(this , RegisterActivity::class.java))
            finish()
        }

        loginBinding.forgotPassId.setOnClickListener {
            startActivity(Intent(this , ForgotpasswordActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}