package ahmed.javcoder.aklamasry.FirebaseOPerations

import ahmed.javcoder.aklamasry.Pojo.reviews
import ahmed.javcoder.aklamasry.UI.Activities.AddfeedbackActivity
import ahmed.javcoder.aklamasry.UI.Activities.MainActivity
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.RatingBar
import com.google.firebase.database.*

class AddFeedback(private val comment:EditText , private val rate:RatingBar , private val id:String , private val username:EditText
                  ,private val context:Context) : AddingInterface {

    private val dbRef:DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun checkViews(): Boolean {
        val review:String = comment.text.toString()
        val rating:Int = rate.rating.toInt()
        val name:String = username.text.toString()
        if (review.equals("")){
            comment.error = "يجب عليك التعليق اولا"
            comment.requestFocus()
            return false
        }
        if (name.equals("")){
            username.error = "يجب عليك اضافه اسمك"
            username.requestFocus()
            return false
        }
        if (rating == 0){
            ToastView.createToast(context , "يجب عليك التقيم ايضا")
            return false
        }
        return true
    }

    override fun addOPeration() {
        if (checkViews() == true){
            val newId:String = dbRef.push().key.toString()
           val reviewModel :reviews=  getReviewModel()
            dbRef.child("Feedbacks").child(id).child(newId).setValue(reviewModel)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        ToastView.createToast(context , "تم اضافه تعليق")
                        context.startActivity(Intent(context , MainActivity::class.java))
                        (context as AddfeedbackActivity).finish()
                    }else {
                        ToastView.createToast(context , "هناك خطأ في العمليه")
                    }
                }
        }
    }

    private fun getReviewModel(): reviews {
        val userName:String = username.text.toString()
        val comment:String = comment.text.toString()
        val rating:Int = rate.rating.toInt()
        val reviewModel = reviews(userName , rating , comment)
        return reviewModel
    }

}