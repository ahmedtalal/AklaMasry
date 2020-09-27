package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.R
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)

        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                startActivity(Intent(this@SplachScreen, RegisterActivity::class.java))
                finish()
            }
        }

        Timer().schedule(timerTask, 1300)
    }
}