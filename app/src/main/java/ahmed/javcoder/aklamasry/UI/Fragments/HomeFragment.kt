package ahmed.javcoder.aklamasry.UI.Fragments

import ahmed.javcoder.aklamasry.Adapters.ProductAdapter
import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.ViewModels.ProductsViewModel
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize
        init(view)

    }

    private fun init(view: View) {
        // setup views
        val recyclerView:RecyclerView = view.findViewById(R.id.home_recycler_id)
        val progress:ProgressBar = view.findViewById(R.id.progressbar_ID)
        val image: CircleImageView = view.findViewById(R.id.noInternet_id)

        progress.visibility = View.VISIBLE

        //recyclerView adapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = false
        recyclerView.setLayoutManager(layoutManager)

        fetchProducts(recyclerView , progress , image)


    }

    private fun fetchProducts(recyclerView: RecyclerView, progress: ProgressBar  , imageView: CircleImageView) {
        // set viewModel
        val vmProduct:ProductsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        vmProduct.init()
        vmProduct.getProducts()?.observe(this , Observer { list:ArrayList<Products> ->
            val adapter = ProductAdapter(list , context!!)
            if (adapter.itemCount != 0){
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                progress.visibility = View.INVISIBLE
            }else {
                progress.visibility = View.INVISIBLE
                imageView.visibility = View.VISIBLE
                ToastView.createToast(context!! , "لا يوجد اتصال بالانترنت")
            }
        })
    }

}