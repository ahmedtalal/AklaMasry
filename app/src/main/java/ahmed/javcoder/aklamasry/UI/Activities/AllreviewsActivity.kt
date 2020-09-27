package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.Adapters.ReviewsAdapter
import ahmed.javcoder.aklamasry.Pojo.reviews
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.ViewModels.ReviewsViewModel
import ahmed.javcoder.aklamasry.databinding.ActivityAllreviewsBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AllreviewsActivity : AppCompatActivity() {
    private lateinit var allBinding : ActivityAllreviewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         allBinding = DataBindingUtil.setContentView(this , R.layout.activity_allreviews)

        val intent = intent
        val id:String = intent.getStringExtra("id")

        setViews()
        fetchAllReviews(id)

    }

    private fun setViews() {
        setSupportActionBar(allBinding.allToolbarId)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        allBinding.allToolbarId.setNavigationOnClickListener {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }
        allBinding.progressReviewId.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = false
        allBinding.reviewRecyclerId.layoutManager = layoutManager

    }

    private fun fetchAllReviews(id: String) {
        val model:ReviewsViewModel = ViewModelProvider(this).get(ReviewsViewModel::class.java)
        model.init(id)
        model.getReview()?.observe(this , Observer { list:ArrayList<reviews> ->
            val adapter = ReviewsAdapter(list , this)
            allBinding.reviewRecyclerId.adapter = adapter
            adapter.notifyDataSetChanged()
            if (adapter.itemCount > 0){
                allBinding.progressReviewId.visibility = View.INVISIBLE
            }else {
                allBinding.progressReviewId.visibility = View.VISIBLE
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this , MainActivity::class.java))
        finish()
    }
}