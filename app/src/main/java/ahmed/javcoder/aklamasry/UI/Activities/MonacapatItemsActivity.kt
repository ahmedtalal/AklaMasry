package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.SettingModel.Settings
import ahmed.javcoder.aklamasry.ViewModels.MonacapatViewModel
import ahmed.javcoder.aklamasry.databinding.ActivityMonacapatItemsBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MonacapatItemsActivity : AppCompatActivity()  , View.OnClickListener{
    private lateinit var showBinding: ActivityMonacapatItemsBinding
    private val hashMap:HashMap<String , String> = HashMap()
    private var recieveID :String? = null
    private var root :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBinding = DataBindingUtil.setContentView(this , R.layout.activity_monacapat_items)
        val intent = intent
        recieveID= intent.getStringExtra("id")
        root= intent.getStringExtra("root")

        getMonacapat(recieveID!! , root!!)
        setActions()
    }

    private fun getMonacapat(recieveID:String , root:String) {
        val model : MonacapatViewModel = ViewModelProvider(this).get(MonacapatViewModel::class.java)
        model.init(root)
        model.getMonacapat()?.observe(this , Observer { list:ArrayList<Products> ->
            for (item: Products in list){
                if (recieveID == item.getid()){
                    setFields(item.getQauntity().toString() , item.getWay().toString() ,
                        item.getPhoto().toString() , item.getName().toString())

                    // set hasMap
                    hashMap["id"] = item.getid().toString()
                    hashMap["name"] = item.getName().toString()
                    hashMap["photo"] = item.getPhoto().toString()
                    break
                }
            }
        })
    }

    private fun setFields(quantity:String , way:String , photo:String , name:String){
        showBinding.qProductId.text = quantity
        showBinding.wayProductId.text = way
        Picasso.get()
            .load(photo)
            .placeholder(R.drawable.ic_dinner)
            .error(R.drawable.ic_dinner)
            .into(showBinding.imageUriId, object : Callback {
                override fun onSuccess() {
                    showBinding.progoBar.visibility = View.GONE
                    showBinding.imageUriId.alpha = "1".toFloat()
                }

                override fun onError(e: Exception) {
                    showBinding.progoBar.visibility = View.VISIBLE
                }
            })
        showBinding.toolbar.title = name
    }

    private fun setActions() {
        // set toolbar settings
        setSupportActionBar(showBinding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // set Actions
        showBinding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }
        showBinding.addFeebbackId.setOnClickListener(this)
        showBinding.shareFriendId.setOnClickListener(this)
        showBinding.allFeebbackId.setOnClickListener(this)
    }

//    private fun getSpecificFav(recieveID: String) {
//        val vModel: FavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
//        vModel.init()
//        vModel.getFavorites()?.observe(this , Observer { list:ArrayList<Products> ->
//            for (item: Products in list){
//                if (recieveID == item.getid()){
//                    showBinding.favId.setImageResource(R.drawable.ic_fav)
//                    showBinding.favId.tag = "fav"
//                    break
//                }else {
//                    showBinding.favId.setImageResource(R.drawable.ic_unfav)
//                    showBinding.favId.tag = "unfav"
//                }
//            }
//        })
//    }



    override fun onClick(v: View) {
        val intent: Intent
        when(v.id){
            R.id.add_feebback_id -> {
                // your implementation here
                intent = Intent(this , AddfeedbackActivity::class.java)
                intent.putExtra("id" , recieveID)
                startActivity(intent)
                finish()
            }
            R.id.share_friend_id -> {
                // your code here
                Settings.shareApp(this)
            }
            R.id.all_feebback_id -> {
                // your code here
                intent = Intent(this , AllreviewsActivity::class.java)
                intent.putExtra("id" , recieveID)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this , MainActivity::class.java))
        finish()
    }
}