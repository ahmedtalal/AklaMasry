package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.Adapters.MonacapatAdapter
import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.ViewModels.MonacapatViewModel
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import ahmed.javcoder.aklamasry.databinding.ActivityShowMonacapatBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

class ShowMonacapatActivity : AppCompatActivity() {
    private lateinit var monacapatBinding: ActivityShowMonacapatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        monacapatBinding = DataBindingUtil.setContentView(this , R.layout.activity_show_monacapat)

        val intent:Intent = intent
        val rootType:String = intent.getStringExtra("rootType")

        getData(rootType)
    }

    private fun getData(rootType: String) {
        monacapatBinding.monacapatProgressbarID.visibility = View.VISIBLE
        //recyclerView adapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = false
        monacapatBinding.monacapatRecyclerId.layoutManager = layoutManager

        val model:MonacapatViewModel = ViewModelProvider(this).get(MonacapatViewModel::class.java)
        model.init(rootType)
        model.getMonacapat()?.observe(this , Observer { list:ArrayList<Products> ->
            val adapters = MonacapatAdapter(list , this , rootType)
            if (adapters.itemCount != 0){
                monacapatBinding.monacapatRecyclerId.adapter = adapters
                adapters.notifyDataSetChanged()
                monacapatBinding.monacapatProgressbarID.visibility = View.INVISIBLE
            }else {
                monacapatBinding.monacapatProgressbarID.visibility = View.INVISIBLE
                monacapatBinding.connectionErrorId.visibility = View.VISIBLE
                ToastView.createToast(this , "لا يوجد اتصال بالانترنت")
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this , MainActivity::class.java))
        finish()
    }
}