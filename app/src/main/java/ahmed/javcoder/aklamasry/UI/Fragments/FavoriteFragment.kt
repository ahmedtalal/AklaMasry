package ahmed.javcoder.aklamasry.UI.Fragments

import ahmed.javcoder.aklamasry.Adapters.ProductAdapter
import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.ViewModels.FavoriteViewModel
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_favorite , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialize views
        init(view)

    }

    private fun init(view: View) {
        val recycler:RecyclerView = view.findViewById(R.id.fav_recycler_id)
        val progress:ProgressBar = view.findViewById(R.id.progressbarFav_ID)
        val image:CircleImageView = view.findViewById(R.id.noFav_id)

        progress.visibility = VISIBLE

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = false
        recycler.layoutManager = layoutManager

        fetchFavData(recycler , progress , image)
    }

    private fun fetchFavData(recycler: RecyclerView , progress: ProgressBar ,   imageView: CircleImageView) {
        val vModel:FavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        vModel.init()
        vModel.getFavorites()?.observe(this , Observer { list:ArrayList<Products> ->
            val adapter = ProductAdapter(list , activity!!)
            if (adapter.itemCount ==0){
                Log.i("home", "fetchFavData: ")
                progress.visibility = INVISIBLE
                imageView.visibility = VISIBLE
                ToastView.createToast(context!! , " لا يوجد اتصال بالانترنت او لا يوجد اكلات مفضله")
            }else {
                recycler.adapter = adapter
                adapter.notifyDataSetChanged()
                progress.visibility = INVISIBLE
            }
        })
    }
}