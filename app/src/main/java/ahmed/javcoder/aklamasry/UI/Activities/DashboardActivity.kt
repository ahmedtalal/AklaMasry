package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.FirebaseOPerations.AddingFactory
import ahmed.javcoder.aklamasry.FirebaseOPerations.AddProduct
import ahmed.javcoder.aklamasry.Pojo.EditTextModel
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.ViewsFactory.ProgressDialogo
import ahmed.javcoder.aklamasry.databinding.ActivityDashboardBinding
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso

class DashboardActivity : AppCompatActivity() {
    private lateinit var dashBinding : ActivityDashboardBinding
    private val RN_PHOTO = 2
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBinding = DataBindingUtil.setContentView(this , R.layout.activity_dashboard)

        dashBinding.addBTNId.setOnClickListener {
            val dialogo = ProgressDialogo.createProgressDialog(this , "الرجاء الانتظار......" )
            val model = EditTextModel(dashBinding.nameProductId , dashBinding.quantityETId , dashBinding.wayETId)
            AddingFactory.addFactory(AddProduct(model , this , imageUri!! , dialogo))
        }

        dashBinding.cameraId.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "completed action"), RN_PHOTO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RN_PHOTO && resultCode == Activity.RESULT_OK) {
            imageUri = data!!.data
            Picasso.get()
                .load(imageUri)
                .into(dashBinding.aklaImageId)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this , LoginActivity::class.java))
        finish()
    }
}