package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.FirebaseOPerations.AddFeedback
import ahmed.javcoder.aklamasry.FirebaseOPerations.AddingFactory
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.databinding.ActivityAddfeedbackBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AddfeedbackActivity : AppCompatActivity() {
    private lateinit var reviewBinding: ActivityAddfeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reviewBinding = DataBindingUtil.setContentView(this , R.layout.activity_addfeedback)

        val intent = intent
        val id:String = intent.getStringExtra("id")

        reviewBinding.addingBTNId.setOnClickListener {
            AddingFactory.addFactory(AddFeedback(
                reviewBinding.feedbackHereId ,
                reviewBinding.rateFeedbackId ,
                id ,
                reviewBinding.nameId ,
                this
            ))
        }

        setSupportActionBar(reviewBinding.toolbarReviewId)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        reviewBinding.toolbarReviewId.setNavigationOnClickListener {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this , MainActivity::class.java))
        finish()
    }
}