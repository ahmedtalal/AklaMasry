package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.FirebaseOPerations.AddToFavorite
import ahmed.javcoder.aklamasry.FirebaseOPerations.AddingFactory
import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.SettingModel.Settings
import ahmed.javcoder.aklamasry.ViewModels.FavoriteViewModel
import ahmed.javcoder.aklamasry.ViewModels.ProductsViewModel
import ahmed.javcoder.aklamasry.databinding.ActivityShowProductBinding
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ShowProductActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var showBinding: ActivityShowProductBinding
    private val hashMap:HashMap<String , String> = HashMap()
    private var recieveID :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBinding = DataBindingUtil.setContentView(this , R.layout.activity_show_product)
        val intent = intent
        recieveID= intent.getStringExtra("id")

        getProducts(recieveID!!)
        setActions()
        getSpecificFav(recieveID!!)

    }

    private fun getSpecificFav(recieveID: String) {
        val vModel: FavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        vModel.init()
        vModel.getFavorites()?.observe(this , Observer { list:ArrayList<Products> ->
            for (item:Products in list){
                if (recieveID == item.getid()){
                    showBinding.favId.setImageResource(R.drawable.ic_fav)
                    showBinding.favId.tag = "fav"
                    break
                }else {
                    showBinding.favId.setImageResource(R.drawable.ic_unfav)
                    showBinding.favId.tag = "unfav"
                }
            }
        })
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
        showBinding.favId.setOnClickListener(this)
        showBinding.addFeebbackId.setOnClickListener(this)
        showBinding.shareFriendId.setOnClickListener(this)
        showBinding.allFeebbackId.setOnClickListener(this)
    }

    private fun getProducts(recieveID:String) {
        val model :ProductsViewModel  = ViewModelProvider(this).get(ProductsViewModel::class.java)
        model.init()
        model.getProducts()?.observe(this , Observer { list:ArrayList<Products> ->
            for (item:Products in list){
                if (recieveID == item.getid()){
                    setFields(item.getQauntity().toString() , item.getWay().toString() ,
                        item.getPhoto().toString() , item.getName().toString())

                    // set hasMap
                    hashMap.put("id" , item.getid().toString())
                    hashMap.put("name" , item.getName().toString())
                    hashMap.put("photo" , item.getPhoto().toString())
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
                    showBinding.progoBar.setVisibility(View.GONE)
                    showBinding.imageUriId.setAlpha("1".toFloat())
                }

                override fun onError(e: Exception) {
                    showBinding.progoBar.setVisibility(View.VISIBLE)
                }
            })
        showBinding.toolbar.title = name
    }

    override fun onClick(v: View) {
        val intent:Intent
        when(v.id){
            R.id.fav_id -> {
                AddingFactory.addFactory(AddToFavorite(showBinding.favId, this , hashMap))
            }
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