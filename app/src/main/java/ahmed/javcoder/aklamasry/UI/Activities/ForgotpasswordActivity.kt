package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.AuthModule.RestPassword
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.databinding.ActivityForgotpasswordBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class ForgotpasswordActivity : AppCompatActivity() {
    private lateinit var passBinding : ActivityForgotpasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        passBinding = DataBindingUtil.setContentView(this , R.layout.activity_forgotpassword)
        passBinding.sendId.setOnClickListener {
            RestPassword.rest(passBinding.forgetEmailETId , this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this , LoginActivity::class.java))
        finish()
    }
}